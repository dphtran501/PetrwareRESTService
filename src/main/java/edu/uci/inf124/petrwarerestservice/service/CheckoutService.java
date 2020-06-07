package edu.uci.inf124.petrwarerestservice.service;

import edu.uci.inf124.petrwarerestservice.db.Database;
import edu.uci.inf124.petrwarerestservice.response.ZipcodeResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckoutService {
    public static ZipcodeResponse getZipcodeData(String zipcode) {
        ZipcodeResponse response = new ZipcodeResponse(zipcode);

        try(Connection conn = Database.dbConnect();
            PreparedStatement stmt = conn.prepareStatement("SELECT z.state as state, city, combinedRate FROM zip_codes z"
                    + " JOIN tax_rates tr ON zip=zipcode WHERE zip=?");) {

            stmt.setString(1, zipcode);
            try(ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    response.setCity(rs.getString("city"));
                    response.setState(rs.getString("state"));
                    response.setCombinedRate(rs.getDouble("combinedRate"));

                    return response;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
