package lk.ijse.supermarket.util;

import javafx.fxml.LoadException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface SetUI {
     void setUi(String location) throws IOException, NullPointerException,ClassNotFoundException, InvocationTargetException, LoadException;
}
