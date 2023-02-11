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
import lk.ijse.supermarket.dao.CrudDAO;
import lk.ijse.supermarket.dao.custom.StockDAO;
import lk.ijse.supermarket.dao.custom.imple.ProductDAOImpl;
import lk.ijse.supermarket.dao.custom.imple.StockDAOImpl;
import lk.ijse.supermarket.dto.ProductDTO;
import lk.ijse.supermarket.dto.StockDTO;
import lk.ijse.supermarket.util.Regex;
import lk.ijse.supermarket.util.emun.TextFields;
//import lk.ijse.supermarket.util.enm.TextFields;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockController {

    public TableView<ProductDTO> tblStock;

    public TableColumn colBName;
    public TableColumn colPName;
    public TableColumn colCategory;
    public TableColumn colQty;
    public TableColumn colMFDDate;
    public TableColumn colEXPDate;
    public TableColumn colDiscount;
    public TableColumn colSellUnitPrice;
    public TableColumn colQtyType;

    public JFXComboBox cmbProduct;

    public JFXTextField txtDiscount;
    public JFXTextField txtPrice;

    private final CrudDAO<ProductDTO,String> productDAOImpl = new ProductDAOImpl();
    private final StockDAO stockDAO = new StockDAOImpl();

    public void initialize() throws SQLException, ClassNotFoundException {

        colBName.setCellValueFactory(new PropertyValueFactory<>("pbName"));
        colPName.setCellValueFactory(new PropertyValueFactory<>("pname"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("pcategory"));
        colMFDDate.setCellValueFactory(new PropertyValueFactory<>("mfdDate"));
        colEXPDate.setCellValueFactory(new PropertyValueFactory<>("expDate"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colQtyType.setCellValueFactory(new PropertyValueFactory<>("qtyType"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colSellUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        loadProducts();

        tblStock.setItems(loadProducttoStockTable);

        comboLoadProductNames();
    }

    private void comboLoadProductNames() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = stockDAO.comboLoadProductNames();

        ObservableList obList = FXCollections.observableArrayList();

        while (resultSet.next()) {
            obList.add(resultSet.getString(1));
            cmbProduct.setItems(obList);
        }
    }

    ObservableList<ProductDTO> loadProducttoStockTable = FXCollections.observableArrayList();

    private void loadProducts() throws SQLException, ClassNotFoundException {

        try {
            ArrayList<ProductDTO> allProduts = productDAOImpl.getAll();
            for (ProductDTO p:allProduts){
                loadProducttoStockTable.add(p);
            }
        } catch (SQLException | NullPointerException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String value = String.valueOf(cmbProduct.getValue());
        StockDTO updateStock = new StockDTO(
                value,
                Double.parseDouble(txtDiscount.getText()),
                Double.parseDouble(txtPrice.getText())
        );
        try {
            boolean isUpdated = stockDAO.update(updateStock);
        }catch ( SQLException | ClassNotFoundException  e){
            e.printStackTrace();
        }

        if(loadProducttoStockTable.size()>0){
            loadProducttoStockTable.clear();
        }

        loadProducts();

        cmbProduct.setValue(null);
        txtPrice.clear();
        txtDiscount.clear();
    }

    public void KeyOnType(KeyEvent keyEvent) {
        Regex.isTextFieldValid(TextFields.DOUBLE,txtPrice);
        Regex.isTextFieldValid(TextFields.DOUBLE,txtDiscount);
    }
}
