package edu.uci.inf124.petrwarerestservice.service;

import edu.uci.inf124.petrwarerestservice.db.Database;
import edu.uci.inf124.petrwarerestservice.db.DatabaseUtils;
import edu.uci.inf124.petrwarerestservice.model.CreditCard;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCardService {

    public static CreditCard getCreditCard(int id) {
        try(Connection conn = Database.dbConnect();
            ResultSet rs = DatabaseUtils.getQueryResults(conn, "SELECT * FROM creditcards WHERE id=" + id);) {

            if (rs.next()) {
                CreditCard card = new CreditCard(id);
                card.setCardNumber(rs.getString("cardNumber"));
                card.setExpiration(rs.getString("expiration"));
                card.setSecurityCode(rs.getString("securityCode"));

                return card;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean addCreditCard(CreditCard card) {
        try(Connection conn = Database.dbConnect()) {
            String sql = "INSERT INTO creditcards VALUES(?, ?, ?, ?)";
            int rowsAffected = DatabaseUtils.updateRecordsPrepared(conn, sql,
                    String.valueOf(card.getId()),
                    card.getCardNumber(),
                    card.getExpiration(),
                    card.getSecurityCode());

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
