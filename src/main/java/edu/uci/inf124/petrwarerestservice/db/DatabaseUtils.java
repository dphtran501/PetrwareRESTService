package edu.uci.inf124.petrwarerestservice.db;

import java.sql.*;

public class DatabaseUtils {

    public static ResultSet getQueryResults(Connection conn, final String sql) {
        try(Statement stmt = conn.createStatement();) {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ResultSet getQueryResultsPrepared(Connection conn, final String sql, String... params) {
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            int i = 1;
            for (String param : params) {
                stmt.setString(i++, param);
            }

            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int updateRecords(Connection conn, final String sql) {
        try (Statement stmt = conn.createStatement();) {
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // -1 means issue with running method
    }

    public static int updateRecordsPrepared(Connection conn, final String sql, String... params) {
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            int i = 1;
            for (String param : params) {
                stmt.setString(i++, param);
            }

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // -1 means issue with running method
    }
}
