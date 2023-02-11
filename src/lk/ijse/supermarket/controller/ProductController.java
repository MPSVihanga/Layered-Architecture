package lk.ijse.supermarket.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.supermarket.dao.CrudDAO;
import lk.ijse.supermarket.dao.custom.ProductDAO;
import lk.ijse.supermarket.dao.custom.imple.ProductDAOImpl;
import lk.ijse.supermarket.dto.ProductDTO;
import lk.ijse.supermarket.util.Regex;
import lk.ijse.supermarket.util.emun.TextFields;
import lk.ijse.supermarket.view.tm.ProductTM;
import org.apache.commons.math.stat.descriptive.summary.Product;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProductController {

    public JFXTextField txtPId;
    public JFXTextField txtBName;
    public JFXTextField txtName;
    public JFXTextField txtCategory;
    public JFXDatePicker mfdDate;
    public JFXDatePicker expDate;

    public TableView tblProduct;
    
    public TableColumn colPId;
    public TableColumn colBName;
    public TableColumn colPName;
    public TableColumn colCategory;
    public TableColumn colMfd;
    public TableColumn colExp;

    static double sellPrice = 0.0;
    static int qty= 0;
    static String qtyType= "empty";
    static double discount= 0.0;

    private final ProductDAO productDAOImpl = new ProductDAOImpl();

    public void initialize(){
        colPId.setCellValueFactory(new PropertyValueFactory<>("pid"));
        colBName.setCellValueFactory(new PropertyValueFactory<>("pbName"));
        colPName.setCellValueFactory(new PropertyValueFactory<>("pname"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("pcategory"));
        colMfd.setCellValueFactory(new PropertyValueFactory<>("mfdDate"));
        colExp.setCellValueFactory(new PropertyValueFactory("expDate"));


        loadAllCustomers();
    }

    public void loadAllCustomers() {

        ObservableList<ProductDTO> obProductList = FXCollections.observableArrayList();

        try {
            ArrayList<ProductDTO> allProduts = productDAOImpl.getAll();
            for (ProductDTO p: allProduts){
                obProductList.add(p);
            }
            tblProduct.setItems(obProductList);

        } catch (SQLException | NullPointerException e) {
            System.out.println("load");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if(txtPId.getText().isEmpty()){
            txtPId.setText("P-0001");
        }
        ResultSet rs= productDAOImpl.autoIncrementID();

        while (rs.next()){

            String check = rs.getString(1);
            System.out.println("check :" +check);
            if(!check.isEmpty() ) {
                int id = Integer.parseInt(rs.getString(1).substring(2));
                id++;
                txtPId.setText(String.format("P-%04d", id));
            }
        }

        //===========================================================================================================

        ProductDTO product =new ProductDTO(txtPId.getText(),txtBName.getText() , txtName.getText(),txtCategory.getText(),
                mfdDate.getValue(),expDate.getValue(),qty,qtyType,discount,sellPrice );
        try {
            boolean context = productDAOImpl.save(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadAllCustomers();

        txtPId.clear();
        txtBName.clear();
        txtName.clear();
        txtCategory.clear();
        mfdDate.getEditor().clear();
        expDate.getEditor().clear();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        ProductDTO product = new ProductDTO(txtPId.getText(),txtBName.getText(),txtName.getText(),txtCategory.getText(),
                LocalDate.now(),LocalDate.now(),qty,qtyType,discount,sellPrice);
        try {
            boolean isUpdated = productDAOImpl.update(product);
        }catch ( SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadAllCustomers();

        txtPId.clear();
        txtBName.clear();
        txtName.clear();
        txtCategory.clear();
        mfdDate.getEditor().clear();
        expDate.getEditor().clear();
    }

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = productDAOImpl.searchByID(txtPId.getText());
        if(resultSet.next()){
            txtBName.setText(resultSet.getString(2));
            txtName.setText(resultSet.getString(3));
            txtCategory.setText(resultSet.getString(4));
            mfdDate.setValue(LocalDate.parse(resultSet.getString(5)));
            expDate.setValue(LocalDate.parse(resultSet.getString(6)));


        }else {
            new Alert(Alert.AlertType.WARNING,"Worng ID !!").show();
        }
    }

    public void keyOnType(KeyEvent keyEvent) {
        Regex.isTextFieldValid(TextFields.ID,txtPId);
        Regex.isTextFieldValid(TextFields.NAME,txtBName);
        Regex.isTextFieldValid(TextFields.NAME,txtName);
        Regex.isTextFieldValid(TextFields.NAME,txtCategory);
    }
}
