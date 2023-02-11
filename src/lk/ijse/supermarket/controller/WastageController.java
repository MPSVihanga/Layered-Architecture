package lk.ijse.supermarket.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.supermarket.dao.custom.ProductDAO;
import lk.ijse.supermarket.dao.custom.WastageDAO;
import lk.ijse.supermarket.dao.custom.imple.ProductDAOImpl;
import lk.ijse.supermarket.dao.custom.imple.WastageDAOImpl;
import lk.ijse.supermarket.dto.Product;
import lk.ijse.supermarket.dto.Wastage;
import lk.ijse.supermarket.util.Regex;
import lk.ijse.supermarket.util.emun.TextFields;
//import lk.ijse.supermarket.util.enm.TextFields;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class WastageController {
    public TableView<Wastage> tblWastage;
    public TableColumn colWastageID;
    public TableColumn colProductId;
    public TableColumn colDate;
    public TableColumn colReason;
    public TableColumn colQty;
    public JFXTextField txtWastageId;
    public JFXComboBox<Product> cmbProduct;
    public JFXTextField txtQty;
    public JFXTextField txtReason;

    private final WastageDAO wastageDAOImpl = new WastageDAOImpl();

    private final ProductDAO productDAO = new ProductDAOImpl();

    public void initialize() throws SQLException, ClassNotFoundException {
        colWastageID.setCellValueFactory(new PropertyValueFactory<>("wastageId"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        loadProductOnAction();
        autoIncrementId();
        loadAllWastage();
    }

    public void loadAllWastage() {

        ObservableList<Wastage> obWastageList = FXCollections.observableArrayList();

        try {
            ArrayList<Wastage> allWastage = wastageDAOImpl.getAll();
            for (Wastage wastage :allWastage){
                obWastageList.add(wastage);
            }
            tblWastage.setItems(obWastageList);
        } catch (SQLException | NullPointerException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void autoIncrementId() throws SQLException, ClassNotFoundException {
        if(txtWastageId.getText().isEmpty()){
            txtWastageId.setText("W-0001");
        }
        ResultSet rs= wastageDAOImpl.autoIncrementID();
        while (rs.next()) {
            String check = rs.getString(1);

            if (!check.isEmpty() && txtWastageId.getText().equals("W-0001")) {
                int id = Integer.parseInt(rs.getString(1).substring(2));
                id++;
                txtWastageId.setText(String.format("W-%04d", id));
            }
        }
    }

    private void loadProductOnAction() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = wastageDAOImpl.loadProductOnAction();

        while (resultSet.next()) {
            Product product= new Product(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));

            cmbProduct.getItems().add(product);
        }

    }

    //=========================================================================================================
    public void btnAddOnAction(ActionEvent actionEvent) {
            Wastage wastage = new Wastage(txtWastageId.getText(), cmbProduct.getValue().getPid(), LocalDate.now(),
                    Integer.parseInt( txtQty.getText() ),  txtReason.getText() );

        try {
            boolean save = wastageDAOImpl.save(wastage);
            if (save){

                ResultSet resultSet = wastageDAOImpl.btnAddOnAction(cmbProduct.getValue().getPid());


                while (resultSet.next()){
                    int qtyStock = resultSet.getInt(1);
                    int qtywastage = Integer.parseInt(txtQty.getText());
                    int finalWQty =  qtyStock - qtywastage;

                    Product product= new Product(cmbProduct.getValue().getPid() , finalWQty);
                    productDAO.updatePOSQty(product);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadAllWastage();

        cmbProduct.setValue(null);
        txtQty.clear();
        txtReason.clear();
    }

    public void KeyOnType(KeyEvent keyEvent) {
        Regex.isTextFieldValid(TextFields.INTEGER,txtQty);
        Regex.isTextFieldValid(TextFields.NAME,txtReason);
    }
}
