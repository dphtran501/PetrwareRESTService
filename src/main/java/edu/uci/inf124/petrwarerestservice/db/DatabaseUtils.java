package edu.uci.inf124.petrwarerestservice.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {

    public static ResultSet getQueryResults(Connection conn, final String sql) {
        try(Statement stmt = conn.createStatement();) {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
