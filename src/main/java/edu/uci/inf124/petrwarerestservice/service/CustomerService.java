package edu.uci.inf124.petrwarerestservice.service;

import edu.uci.inf124.petrwarerestservice.db.Database;
import edu.uci.inf124.petrwarerestservice.db.DatabaseUtils;
import edu.uci.inf124.petrwarerestservice.model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerService {

    public static Customer getCustomer(int id) {
        try(Connection conn = Database.dbConnect();
            ResultSet rs = DatabaseUtils.getQueryResults(conn, "SELECT * FROM customers WHERE id=" + id);) {

            if (rs.next()) {
                Customer customer = new Customer(id);
                customer.setFirstName(rs.getString("firstName"));
                customer.setLastName(rs.getString("lastName"));
                customer.setPhone(rs.getString("phone"));
                customer.setCountry(rs.getString("country"));
                customer.setStreetAddress(rs.getString("streetAddress"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setZipcode(rs.getString("zipcode"));
                customer.setShipping(rs.getString("shipping"));
                customer.setEmail(rs.getString("email"));

                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int addCustomer() {
        int id = -1;    // -1 means no customer record created
        try(Connection conn = Database.dbConnect()) {
            String sql = "INSERT into customers (firstname, lastname) VALUES ('UNSAVED', 'CUSTOMER')";
            int rowsAffected = DatabaseUtils.updateRecords(conn, sql);
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

    public static boolean updateCustomer(Customer customer) {
        try(Connection conn = Database.dbConnect()) {
            String sql = "UPDATE customers SET firstName=?, lastName=?, phone=?, country=?, streetAddress=?," +
                    " city=?, state=?, zipcode=?, shipping=?, email=? WHERE id=?";
            int rowsAffected = DatabaseUtils.updateRecordsPrepared(conn, sql,
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getPhone(),
                    customer.getCountry(),
                    customer.getStreetAddress(),
                    customer.getCity(),
                    customer.getState(),
                    customer.getZipcode(),
                    customer.getShipping(),
                    customer.getEmail(),
                    String.valueOf(customer.getId()));

            if (rowsAffected > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
