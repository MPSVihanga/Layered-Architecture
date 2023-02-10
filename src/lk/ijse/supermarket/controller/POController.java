package lk.ijse.supermarket.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.supermarket.dao.CrudDAO;
import lk.ijse.supermarket.dao.custom.PODAO;
import lk.ijse.supermarket.dao.custom.PODetailsDAO;
import lk.ijse.supermarket.dao.custom.ProductDAO;
import lk.ijse.supermarket.dto.PO;
import lk.ijse.supermarket.dao.custom.imple.PODetailsDAOImpl;
import lk.ijse.supermarket.dao.custom.imple.PODAOImpl;
import lk.ijse.supermarket.dao.custom.imple.ProductDAOImpl;
import lk.ijse.supermarket.dto.*;
//import lk.ijse.supermarket.util.enm.TextFields;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class POController {
    public AnchorPane poContextPane;

    public JFXComboBox<Product> cmbProduct;
    public JFXComboBox<Supplier> cmbSupplier;

    public JFXTextField txtPOId;
    public JFXTextField txtQty;
    public JFXTextField txtQtyType;

    public TableView<PoAndDetails> tblPO;

    public TableColumn colPOId;
    public TableColumn colSupId;
    public TableColumn colPId;
    public TableColumn colPName;
    public TableColumn colBName;
    public TableColumn colQty;
    public TableColumn colQtyType;
    public Label lblQtyType;

    private final PODAO poDAOImpl = new PODAOImpl();
    private final PODetailsDAO poDetailDAOImpl = new PODetailsDAOImpl();
    private final ProductDAO productDAO = new ProductDAOImpl();

    public void initialize() throws SQLException, ClassNotFoundException {

        colPOId.setCellValueFactory(new PropertyValueFactory<>("poId"));
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colPId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colBName.setCellValueFactory(new PropertyValueFactory<>("productBName"));
        colPName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colQtyType.setCellValueFactory(new PropertyValueFactory<>("qtyType"));
        tblPO.setItems(obPoAndDetailList);
        tblPO.setEditable(false);

            loadSupplierIDOnAction();
            loadProductOnAction();
            autoIncrementId();

    }
//=============================================================================================================

    private void autoIncrementId() throws SQLException, ClassNotFoundException {

        if(txtPOId.getText().isEmpty()){
            txtPOId.setText("PO-0001");
        }
        ResultSet rs= poDAOImpl.autoIncrementID();
        while (rs.next()) {
            String check = rs.getString(1);

            if (!check.isEmpty() && txtPOId.getText().equals("PO-0001")) {
                int id = Integer.parseInt(rs.getString(1).substring(3));
                id++;
                txtPOId.setText(String.format("PO-%04d", id));
            }
        }
    }

    public void loadProductOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/Product.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void loadProductOnAction() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = poDAOImpl.loadProductOnAction();

        while (resultSet.next()) {
           Product product= new Product(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));

            cmbProduct.getItems().add(product);
        }

    }

    private void loadSupplierIDOnAction() throws SQLException, ClassNotFoundException {
        ResultSet rst= poDAOImpl.loadSupplierOnAction();

        while (rst.next()) {
            Supplier supplier = new Supplier(rst.getString(1), rst.getString(4));
            cmbSupplier.getItems().add(supplier);
        }

    }

    public void cmbloadProductQtyType(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!cmbProduct.getSelectionModel().isEmpty()) {
            ResultSet resultSet = poDAOImpl.cmbloadProductQtyType(cmbProduct.getValue().getPid());
            while (resultSet.next()) {
                txtQtyType.setText(resultSet.getString(1));

                if (txtQtyType.getText().equals("empty")) {
                    lblQtyType.setVisible(true);
                    txtQtyType.clear();
                } else {
                    lblQtyType.setVisible(false);
                }
            }
        }
    }

    //=============================================================================================================

    ObservableList<PoAndDetails> obPoAndDetailList=FXCollections.observableArrayList();

    public void btnSaveOnAcion(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        PoAndDetails poAndD =new PoAndDetails(txtPOId.getText(), cmbSupplier.getValue().getId(), cmbProduct.getValue().getPid(),
                cmbProduct.getValue().getPbName(), cmbProduct.getValue().getPname(), Integer.parseInt(txtQty.getText()), txtQtyType.getText() );

                    obPoAndDetailList.add(poAndD);

                    cmbProduct.getSelectionModel().clearSelection();
                    txtQty.clear();
                    txtQtyType.clear();
                    lblQtyType.setVisible(false);

    }

    public void btnCompleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ObservableList<PoAndDetails> items = tblPO.getItems();

        String poID= items.get(0).getPoId();
        String supID=items.get(0).getSupId();


        boolean isAddedPO = poDAOImpl.save(new PO(poID, supID,LocalDate.now()));
        if(isAddedPO){
            for (int i = 0; i < items.size(); i++) {

                int qty=items.get(i).getQty();


                String qtyType=items.get(i).getQtyType();

                String productID=items.get(i).getProductId();
                ResultSet resultSet= poDAOImpl.btnCompleteOnAction(productID);
                while (resultSet.next()) {
                    int getQty = resultSet.getInt(1);
                    int finalQty = getQty + qty;

                    boolean isAddePODetails = poDetailDAOImpl.save(new PODetails(poID, productID));
                    if (isAddePODetails) {
                        productDAO.updateQty(new Product(productID, finalQty, qtyType));
                        System.out.println("Qty Updated");
                    }
                    if (items.size() - 1 == i) {
                        System.out.println("Added dto POnPOD !");
                    }
                }

            }
        }

        txtPOId.clear();
        cmbSupplier.getSelectionModel().clearSelection();
        cmbProduct.setValue(null);
        txtQty.clear();
        txtQtyType.clear();
        tblPO.getItems().clear();
        autoIncrementId();

    }

    /*public void KeyOnType(KeyEvent keyEvent) {
        Regex.isTextFieldValid(TextFields.INTEGER,txtQty);
        Regex.isTextFieldValid(TextFields.NAME,txtQtyType);
    }*/
}
