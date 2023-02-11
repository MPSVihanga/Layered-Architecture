package lk.ijse.supermarket.dao.custom.imple;

import lk.ijse.supermarket.dao.custom.QueryDAO;

import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {
    public String reportJoinQuery(String id) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT `Supermarket`.orders.`totalPrice`, `Supermarket`.product.name, `Supermarket`.product.`brndName`, `Supermarket`.product.qty, `Supermarket`.product.`qtyType`, `Supermarket`.product.`sellingUnitPrice`, `Supermarket`.product.`sellingDiscount` FROM `Supermarket`.`orderDetails` INNER JOIN `Supermarket`.orders ON `Supermarket`.orders.`oId` = `Supermarket`.`orderDetails`.`ordrDOrderId` INNER JOIN `Supermarket`.product ON `Supermarket`.product.`prdctId` = `Supermarket`.`orderDetails`.`ordrDPrdctId` where ordrDOrderId = \"" + id + "\"";
        return sqlQuery;
    }
}
