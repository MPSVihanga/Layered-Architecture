package lk.ijse.supermarket.util;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Paint;
import lk.ijse.supermarket.util.emun.TextFields;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static boolean isTextFieldValid(TextFields textField, JFXTextField text){
        String field="";
        switch (textField){
            case ID:
                field="^([A-Z0-9])$";
                break;
            case NAME:
                field="^[A-z.|\\s]{1,}$";
                break;
            case EMAIL:
                field="^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;
            case ADDRESS:
                field="^([A-z0-9]|[/,.@]|\\s){1,}$";
                break;
            case TEL:
                field="^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
                break;
            case DOUBLE:
                field="^([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case INTEGER:
                field="^([0-9]){1,}$";
                break;
            case NONE_CHARACTER:
                field="^[\\W]{1,}$";
                break;
        }

        Pattern pattern=Pattern.compile(field);

        Matcher matcher= pattern.matcher(text.getText());

        if(text.getText().isEmpty()){

            return false;
        }

        if(matcher.matches()){
            text.setFocusColor(Paint.valueOf("Green"));
            return true;
        }else {
            text.setFocusColor(Paint.valueOf("Red"));
            return false;
        }
    }




}
