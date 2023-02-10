package lk.ijse.supermarket.dao;



import lk.ijse.supermarket.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CRUD {

    /*
   private static PreparedStatement execute(String sql, Object... params) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pStm = connection.prepareStatement(sql);

        for(int i=0;i<params.length; i++){
            pStm.setObject(i+1,params[i]);
        }
        return pStm;
    }


    public static ResultSet executeQuery(String sql , Object... prams) throws SQLException {
        return execute(sql, prams).executeQuery();
    }

    public static boolean executeUpdate(String sql , Object... prams) throws SQLException {
        return execute(sql, prams).executeUpdate()>0;
    }

     */


   public static <T> T execute(String sql, Object... params) throws SQLException, NullPointerException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pStm = connection.prepareStatement(sql);

        for(int i=0;i<params.length; i++){
            pStm.setObject(i+1,params[i]);
        }
        if (sql.startsWith("SELECT")){
            return (T)pStm.executeQuery();
        }else {
            return ((T)(Boolean)(pStm.executeUpdate()>0));
        }
    }


}
