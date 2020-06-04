package edu.uci.inf124.petrwarerestservice.response;

import edu.uci.inf124.petrwarerestservice.model.ProductCPU;
import edu.uci.inf124.petrwarerestservice.model.ProductRAM;
import edu.uci.inf124.petrwarerestservice.model.ProductVC;

public class ProductResponse {
    String message;

    ProductCPU productCPU;
    ProductRAM productRAM;
    ProductVC productVC;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProductCPU getProductCPU() {
        return productCPU;
    }

    public void setProductCPU(ProductCPU productCPU) {
        this.productCPU = productCPU;
    }

    public ProductRAM getProductRAM() {
        return productRAM;
    }

    public void setProductRAM(ProductRAM productRAM) {
        this.productRAM = productRAM;
    }

    public ProductVC getProductVC() {
        return productVC;
    }

    public void setProductVC(ProductVC productVC) {
        this.productVC = productVC;
    }
}
