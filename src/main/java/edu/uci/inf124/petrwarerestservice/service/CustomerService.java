package edu.uci.inf124.petrwarerestservice.service;

import edu.uci.inf124.petrwarerestservice.db.Database;
import edu.uci.inf124.petrwarerestservice.db.DatabaseUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerService {

    public static int addCustomer() {
        int id = -1;    // -1 means no customer record created
        try(Connection conn = Database.dbConnect()) {
            int rowsAffected = DatabaseUtils.updateRecords(conn, "INSERT into customers (firstname, lastname) VALUES ('UNSAVED', 'CUSTOMER')");
            if (rowsAffected > 0) {
                try(ResultSet rs = DatabaseUtils.getQueryResults(conn, "SELECT LAST_INSERT_ID()");) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
}
