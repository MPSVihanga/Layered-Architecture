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
import javafx.scene.layout.AnchorPane;
import lk.ijse.supermarket.dao.CrudDAO;
import lk.ijse.supermarket.dao.custom.imple.SupplierDAOImpl;
import lk.ijse.supermarket.dto.Supplier;
//import lk.ijse.supermarket.util.enm.TextFields;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SupplierController{

    public AnchorPane supplierMainPane;

    public JFXTextField txtId;
    public JFXTextField txtCName;
    public JFXTextField txtCTel;
    public JFXTextField txtSName;
    public JFXTextField txtSTel;
    public JFXDatePicker date;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;

    public TableView<Supplier> tblSupplier;

    public TableColumn colId;
    public TableColumn colCName;
    public TableColumn colCTel;
    public TableColumn colSName;
    public TableColumn colSTel;
    public TableColumn colDate;
    public TableColumn colAddress;
    public TableColumn colEmail;

    //Property Injection
    private final CrudDAO<Supplier,String> supplierDAOImpl = new SupplierDAOImpl();

    public void initialize() throws SQLException,NullPointerException{

        colId.setCellValueFactory(new  PropertyValueFactory<>("Id"));
        colCName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colCTel.setCellValueFactory(new PropertyValueFactory<>("companyTel"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colSTel.setCellValueFactory(new PropertyValueFactory<>("supplierTel"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("adderss"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("companyEmail"));

        loadAllCustomers();
    }

    public void loadAllCustomers() {

            ObservableList<Supplier> obSupplierList = FXCollections.observableArrayList();

        try {
                ArrayList<Supplier> allSupplier = supplierDAOImpl.getAll();
                for (Supplier s :allSupplier){
                    obSupplierList.add(s);
                }
                    tblSupplier.setItems(obSupplierList);
            } catch (SQLException | NullPointerException | ClassNotFoundException e) {
                e.printStackTrace();
            }


    }

    public void addSupplierOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


    //==================================================================================================

       /* boolean txtFCName = Regex.isTextFieldValid(TextFields.NAME, txtCName);
            if (!txtFCName){
                new Shake(txtCName).play();
                txtCName.setStyle("-fx-prompt-text-fill: red");
                return;
            }
        boolean txtFCTel = Regex.isTextFieldValid(TextFields.TEL, txtCTel);
            if (!txtFCTel){
                new Shake(txtCTel).play();
                txtCTel.setStyle("-fx-prompt-text-fill: red");
                return;
            }
        boolean txtFSName = Regex.isTextFieldValid(TextFields.NAME, txtSName);
            if (!txtFSName){
                new Shake(txtSName).play();
                txtSName.setStyle("-fx-prompt-text-fill: red");
                return;
            }
        boolean txtFSTel = Regex.isTextFieldValid(TextFields.TEL, txtSTel);
            if (!txtFSTel){
                new Shake(txtSTel).play();
                txtSTel.setStyle("-fx-prompt-text-fill: red");
                return;
            }
        boolean txtFAddress = Regex.isTextFieldValid(TextFields.ADDRESS, txtAddress);
            if(!txtFAddress){
                new Shake(txtAddress).play();
                txtAddress.setStyle("-fx-prompt-text-fill: red");
                return;
            }
        boolean txtFEmail = Regex.isTextFieldValid(TextFields.EMAIL, txtEmail);
            if (!txtFEmail){
                new Shake(txtEmail).play();
                txtEmail.setStyle("-fx-prompt-text-fill: red");
                return;
            }*/


    //====================================================================================================
        if(txtId.getText().isEmpty()){
            txtId.setText("SP-0001");
        }

        ResultSet rs= supplierDAOImpl.autoIncrementID();

            while (rs.next()){

            String check = rs.getString(1);
            System.out.println("check :" +check);
            if(!check.isEmpty() && txtId.getText().equals("SP-0001")) {
                int id = Integer.parseInt(rs.getString(1).substring(3));
                id++;
                txtId.setText(String.format("SP-%04d", id));
            }
        }

        //=====================================================================================================

        Supplier supplier=new Supplier(txtId.getText(),txtCName.getText() , txtCTel.getText(),
                txtSName.getText() , txtSTel.getText() , LocalDate.now(),
                txtAddress.getText(),txtEmail.getText());
        try {
            boolean context = supplierDAOImpl.save(supplier);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        loadAllCustomers();


        txtId.clear();
        txtCName.clear();
        txtCTel.clear();
        txtSName.clear();
        txtSTel.clear();
        txtAddress.clear();
        txtEmail.clear();


    }

    public void updateSupplierOnAction(ActionEvent actionEvent) throws SQLException {

        Supplier supplier = new Supplier(txtId.getText(),txtCName.getText(),txtCTel.getText(),
                txtSName.getText(),txtSTel.getText(),LocalDate.now(),txtAddress.getText(),
                txtEmail.getText());

        try {
            boolean isUpdated = supplierDAOImpl.update(supplier);
        }catch ( SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadAllCustomers();

        txtId.clear();
        txtCName.clear();
        txtCTel.clear();
        txtSName.clear();
        txtSTel.clear();
        txtAddress.clear();
        date.getEditor().clear();
        txtEmail.clear();

    }

    public void searchSupplierOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = supplierDAOImpl.searchByID(txtId.getText());
        if(resultSet.next()){
            txtCName.setText(resultSet.getString(2));
            txtCTel.setText(String.valueOf(resultSet.getInt(3)));
            txtSName.setText(resultSet.getString(4));
            txtSTel.setText(String.valueOf(resultSet.getInt(5)));
            date.setValue(LocalDate.parse(resultSet.getString(6)));
            txtAddress.setText(resultSet.getString(7));
            txtEmail.setText(resultSet.getString(8));
        }else {
            new Alert(Alert.AlertType.WARNING,"Worng ID !!").show();
        }

    }

   /* public void keyOnType(KeyEvent keyEvent) {
       Regex.isTextFieldValid(TextFields.NAME,txtCName);
        Regex.isTextFieldValid(TextFields.TEL,txtCTel);
        Regex.isTextFieldValid(TextFields.NAME,txtSName);
        Regex.isTextFieldValid(TextFields.TEL,txtSTel);
        Regex.isTextFieldValid(TextFields.ADDRESS,txtAddress);
        Regex.isTextFieldValid(TextFields.EMAIL,txtEmail);
    }*/
}
