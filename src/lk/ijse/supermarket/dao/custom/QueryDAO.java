package lk.ijse.supermarket.dao.custom;

import java.sql.SQLException;

public interface QueryDAO {

    public String reportJoinQuery(String text) throws SQLException, ClassNotFoundException;
}
