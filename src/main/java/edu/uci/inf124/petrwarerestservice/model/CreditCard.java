package edu.uci.inf124.petrwarerestservice.model;

public class CreditCard {
    int id;
    String cardNumber;
    String expiration;
    String securityCode;

    public CreditCard() {
    }

    public CreditCard(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiration='" + expiration + '\'' +
                ", securityCode=" + securityCode +
                '}';
    }
}
