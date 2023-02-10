package lk.ijse.supermarket.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.supermarket.dao.*;
import lk.ijse.supermarket.dao.custom.POSDAO;
import lk.ijse.supermarket.dao.custom.ProductDAO;
import lk.ijse.supermarket.dao.custom.imple.OrderDAOImpl;
import lk.ijse.supermarket.dao.custom.imple.OrderDetailsDAOImpl;
import lk.ijse.supermarket.dao.custom.imple.POSDAOImpl;
import lk.ijse.supermarket.dao.custom.imple.ProductDAOImpl;
import lk.ijse.supermarket.db.DBConnection;
import lk.ijse.supermarket.dto.*;
//import lk.ijse.supermarket.util.enm.TextFields;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class POSController {
    public AnchorPane posAnchorPane;

    public JFXTextField txtQty;

    public TableView tblPOS;

    public TableColumn colProductId;
    public TableColumn colBrandName;
    public TableColumn colProductName;
    public TableColumn colQty;
    public TableColumn colPrice;
    public JFXTextField txtFinalAmount;
    public JFXTextField txtPaymentCash;
    public JFXTextField txtBalance;
    public JFXComboBox<Product> cmbPOSProduct;
    public JFXTextField txtOrderID;
    public Label lblBalanceRS;
    public Label lblBalanceWarrning;
    public Label lblQtyCount;
    public Label lblOutOfStocks;
    public Label lblDate;
    public Label lblTime;

    private final CrudDAO<Order,String> orderDetailsDAOImpl = new OrderDetailsDAOImpl();

    private final CrudDAO<Order,String> orderDAOImpl = new OrderDAOImpl();

    private final POSDAO posDAOImpl = new POSDAOImpl();

    private final ProductDAO productDAO = new ProductDAOImpl();

    public void initialize() throws SQLException, ClassNotFoundException {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colBrandName.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblPOS.setItems(obPOSDetailList);

        loadProductOnAction();
        autoIncrementId();
        initClock();

    }


    private void initClock() {

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                lblDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                lblTime.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
        };
        timer.start();

       /* Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        */
    }

    public void loadDashboardOnAction(MouseEvent mouseEvent) throws IOException {
        Stage window =(Stage)posAnchorPane.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Dashboard.fxml"))));

    }

    private void loadProductOnAction() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = posDAOImpl.loadProductOnAction();

        while (resultSet.next()) {
            Product product= new Product(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
            cmbPOSProduct.getItems().add(product);
        }

    }

    public void cmbLoadProductQtyCountOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        if (!cmbPOSProduct.getSelectionModel().isEmpty()) {
            String pid = cmbPOSProduct.getSelectionModel().getSelectedItem().getPid();
            ResultSet resultSet= posDAOImpl.cmbLoadProductQtyCountOnAction(pid);
            while (resultSet.next()) {
                lblQtyCount.setText("- "+resultSet.getInt(1)+" -");
                lblQtyCount.setVisible(true);
                if (resultSet.getInt(1)==0){
                    txtQty.clear();
                    lblOutOfStocks.setText("Out Of Stocks !");
                    lblOutOfStocks.setVisible(true);
                    txtQty.setEditable(false);
                }else {
                    txtQty.setEditable(true);
                    lblOutOfStocks.setText(" ");
                    lblOutOfStocks.setVisible(false);
                }
            }
        }



    }

    ObservableList<POSDetails> obPOSDetailList= FXCollections.observableArrayList();
    double finalAmount=0;
    public void btnAddProductOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ResultSet resultSet= posDAOImpl.btnAddProductOnAction(cmbPOSProduct.getValue().getPid());
        while (resultSet.next()) {
            if (txtQty.getText().isEmpty()){
               // System.out.println(txtQty.getText());
                new Alert(Alert.AlertType.INFORMATION,"Out of Stock ").show();
            }else {
                int substring = Integer.parseInt(lblQtyCount.getText().split(" ")[1]);
                //System.out.println(substring);
                if (substring<Integer.parseInt(txtQty.getText())){
                    lblOutOfStocks.setText("Qty Out of Range !");
                    lblOutOfStocks.setVisible(true);
                }else {
                    lblOutOfStocks.setText(" ");
                    lblOutOfStocks.setVisible(false);
                    double finalPrice = (resultSet.getDouble(2) * (100 - resultSet.getDouble(1)) / 100) * Integer.parseInt(txtQty.getText());

                    POSDetails posDetails = new POSDetails(cmbPOSProduct.getValue().getPid(), cmbPOSProduct.getValue().getPbName(),
                            cmbPOSProduct.getValue().getPname(), Integer.parseInt(txtQty.getText()), finalPrice);

                    obPOSDetailList.add(posDetails);

                    ObservableList<POSDetails> totalAmount = tblPOS.getItems();

                    for (int i = 0; i < totalAmount.size(); i++) {
                        finalAmount = finalAmount + totalAmount.get(i).getPrice();
                        txtFinalAmount.setText(String.valueOf(finalAmount));
                    }

                    finalAmount = 0;

                    lblQtyCount.setText(" ");
                    cmbPOSProduct.setValue(null);
                    txtQty.clear();
                }
            }

        }
    }

    public void EnterKeyPressedOnAction(KeyEvent keyEvent) {

        //Regex.isTextFieldValid(TextFields.INTEGER,txtPaymentCash);

        if ( keyEvent.getCode()==keyEvent.getCode().ENTER){
            if( !(txtPaymentCash.getText().isEmpty()) ) {
                double cashPayment = Double.parseDouble(txtPaymentCash.getText());
                double cashFinal = Double.parseDouble(txtFinalAmount.getText());
                    if(cashFinal>=cashPayment){
                        lblBalanceWarrning.setVisible(true);
                    }else {
                        lblBalanceWarrning.setVisible(false);
                        double balance = cashPayment - cashFinal;
                        txtBalance.setText(String.valueOf(balance));
                    }
            }
        }
    }

    private void autoIncrementId() throws SQLException, ClassNotFoundException {
        if(txtOrderID.getText().isEmpty()){
            txtOrderID.setText("O-0001");
        }
        ResultSet rs= posDAOImpl.autoIncrementID();
        if(rs.next()) {
            String check = rs.getString(1);

            if (!check.isEmpty() && txtOrderID.getText().equals("O-0001")) {
                int id = Integer.parseInt(rs.getString(1).substring(2));
                id++;
                txtOrderID.setText(String.format("O-%04d", id));
            }
        }
    }

    public void btnCompleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        DBConnection.getDbConnection().getConnection().setAutoCommit(false);
        try {
            ObservableList<POSDetails> items = tblPOS.getItems();
            boolean isOrderUpdated = orderDAOImpl.save(new Order(txtOrderID.getText(), LocalDate.now(),
                    LocalTime.now(), Double.parseDouble(txtFinalAmount.getText())));
            if (isOrderUpdated) {
                for (int i = 0; i < items.size(); i++) {
                    String produtId = items.get(i).getProductId();
                    int qty = items.get(i).getQty();

                    ResultSet resultSet =posDAOImpl.btnCompleteOnAction(produtId);
                    while (resultSet.next()) {

                        int getQty = resultSet.getInt(1);
                        int finalQty = getQty - qty;

                        boolean isAddePODetails = orderDetailsDAOImpl.save(new Order(txtOrderID.getText(), produtId));
                        if (isAddePODetails) {
                            productDAO.updatePOSQty(new Product(produtId, finalQty));
                            System.out.println("Qty Updated");
                            DBConnection.getDbConnection().getConnection().commit();

                            //============== Report ===============================
                            try {
                                showReport();
                            } catch (JRException e) {
                                e.printStackTrace();
                            }

                            autoIncrementId();
                        }else{
                            DBConnection.getDbConnection().getConnection().rollback();
                        }
                        if (items.size() - 1 == i) {
                            System.out.println("Added dto POS !");
                        }
                    }

                }
            } else {
                DBConnection.getDbConnection().getConnection().rollback();
            }
        }finally {
            DBConnection.getDbConnection().getConnection().setAutoCommit(true);
        }
        //initialize();
            txtFinalAmount.clear();
            txtPaymentCash.clear();
            txtBalance.clear();
            tblPOS.getItems().clear();


    }

    public void showReport() throws JRException, SQLException, ClassNotFoundException {
        JasperDesign jasperDesign = JRXmlLoader.load("/Users/mpsvihanga/Documents/FX/Supermarket/src/lk/ijse/supermarket/report/posReport.jrxml");
        String query = "SELECT `Supermarket`.orders.`totalPrice`, `Supermarket`.product.name, `Supermarket`.product.`brndName`, `Supermarket`.product.qty, `Supermarket`.product.`qtyType`, `Supermarket`.product.`sellingUnitPrice`, `Supermarket`.product.`sellingDiscount` FROM `Supermarket`.`orderDetails` INNER JOIN `Supermarket`.orders ON `Supermarket`.orders.`oId` = `Supermarket`.`orderDetails`.`ordrDOrderId` INNER JOIN `Supermarket`.product ON `Supermarket`.product.`prdctId` = `Supermarket`.`orderDetails`.`ordrDPrdctId` where ordrDOrderId = \""+txtOrderID.getText()+"\"";
        JRDesignQuery updateQuery = new JRDesignQuery();
        updateQuery.setText(query);
        jasperDesign.setQuery(updateQuery);
        JasperReport jasperReport= JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getDbConnection().getConnection());
        JasperViewer jasperViewer =new JasperViewer(jasperPrint,false);
        jasperViewer.setVisible(true);

        autoIncrementId();
    }


    /*public void KeyOnType(KeyEvent keyEvent) {
        Regex.isTextFieldValid(TextFields.INTEGER,txtQty);
    }*/
}
