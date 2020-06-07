package edu.uci.inf124.petrwarerestservice.response;

public class ZipcodeResponse {
    String zipcode;
    String city;
    String state;
    double combinedRate;

    public ZipcodeResponse(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getCombinedRate() {
        return combinedRate;
    }

    public void setCombinedRate(double combinedRate) {
        this.combinedRate = combinedRate;
    }

    @Override
    public String toString() {
        return "ZipcodeResponse{" +
                "zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", combinedRate=" + combinedRate +
                '}';
    }
}
