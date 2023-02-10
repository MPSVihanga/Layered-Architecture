package lk.ijse.supermarket.controller;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.supermarket.util.SetUI;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class dashboardController implements SetUI {

    public AnchorPane mainContext;
    public AnchorPane orderContextPane;
    public AnchorPane InventoryContextPane;
    public AnchorPane userContextPane;
    public AnchorPane reportContextPane;
    public Label lblDate;
    public Label lblTime;

    public void initialize(){
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
    // ======================= Dashboard load Button AnchorPane Section ===============================================================================

    public void loadInventoryBtnOnAction(ActionEvent actionEvent) throws IOException {
        InventoryContextPane.setVisible(true);
        orderContextPane.setVisible(false);
        userContextPane.setVisible(false);
        reportContextPane.setVisible(false);
    }
    public void loadOrderOnAction(ActionEvent actionEvent) throws IOException {
        InventoryContextPane.setVisible(false);
        orderContextPane.setVisible(true);
        userContextPane.setVisible(false);
        reportContextPane.setVisible(false);
    }
    public void loadDashboardBtnOnAction(ActionEvent actionEvent) throws IOException {
        InventoryContextPane.setVisible(false);
        orderContextPane.setVisible(false);
        userContextPane.setVisible(false);
        reportContextPane.setVisible(false);

        Stage window =(Stage)mainContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Dashboard.fxml"))));

    }
    public void loadUserOnAction(ActionEvent actionEvent) throws IOException {
        InventoryContextPane.setVisible(false);
        orderContextPane.setVisible(false);
        userContextPane.setVisible(true);
        reportContextPane.setVisible(false);

    }
    public void loadReportOnAction(ActionEvent actionEvent) {
        reportContextPane.setVisible(true);
        InventoryContextPane.setVisible(false);
        orderContextPane.setVisible(false);
        userContextPane.setVisible(false);
    }

    // ======================= End Dashboard load Button AnchorPane Section ===============================================================================

    // ======================= Set UI Section ===============================================================================

    @Override
    public void setUi(String location) throws IOException {
        mainContext.getChildren().clear();
        mainContext.getChildren().add(FXMLLoader.load(getClass().getResource(location+".fxml")));
    }

    public void loadStockOnAction(ActionEvent actionEvent) throws IOException {
        setUi("../view/stock");
    }

    public void loadSupplierOnAction(ActionEvent actionEvent) throws IOException {
        setUi("../view/addSupplier");
    }

    public void loadEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("../view/addEmployee");
    }

    public void loadPOOnAction(ActionEvent actionEvent) throws IOException {
        setUi("../view/PO");
    }
    public void loadProductOnAction(ActionEvent actionEvent) throws IOException {
        setUi("../view/Product");
    }
    public void loadWastageOnAction(ActionEvent actionEvent) throws IOException {
        setUi("../view/wastage");
    }

    public void loadOrderRecordOnAction(ActionEvent actionEvent) throws IOException {
        setUi("../view/OrderRecord");
    }

//===========================Start================================================================================

    public void loadPOSOnAction(ActionEvent actionEvent) throws IOException {
        Stage window =(Stage)mainContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/POS.fxml"))));
    }

    public void loadMainLoginOnAction(MouseEvent mouseEvent) throws IOException {
        Stage window =(Stage)mainContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainLogInForm.fxml"))));
    }

    //========================End===================================================================================

    // =======================End Set UI Section =============================================================================== */

    public void btnDailyReportOnAction(ActionEvent actionEvent) {

    }
}
