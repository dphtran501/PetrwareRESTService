package edu.uci.inf124.petrwarerestservice.service;

import edu.uci.inf124.petrwarerestservice.db.Database;
import edu.uci.inf124.petrwarerestservice.db.DatabaseUtils;
import edu.uci.inf124.petrwarerestservice.model.Product;
import edu.uci.inf124.petrwarerestservice.model.ProductCPU;
import edu.uci.inf124.petrwarerestservice.model.ProductRAM;
import edu.uci.inf124.petrwarerestservice.model.ProductVC;
import edu.uci.inf124.petrwarerestservice.response.ProductListResponse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductService {

    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM product";
    private static final String JOIN_PRODUCT_CPU = " JOIN product_cpu ON id=product_id";
    private static final String JOIN_PRODUCT_RAM = " JOIN product_ram ON id=product_id";
    private static final String JOIN_PRODUCT_VIDEO_CARD = " JOIN product_video_card ON id=product_id";

    public static ProductListResponse getAllProducts() {
        ProductListResponse response = new ProductListResponse();

        try(Connection conn = Database.dbConnect();
            ResultSet rsProductCPU = DatabaseUtils.getQueryResults(conn, SELECT_ALL_PRODUCTS + JOIN_PRODUCT_CPU);
            ResultSet rsProductRAM = DatabaseUtils.getQueryResults(conn, SELECT_ALL_PRODUCTS + JOIN_PRODUCT_RAM);
            ResultSet rsProductVC = DatabaseUtils.getQueryResults(conn, SELECT_ALL_PRODUCTS + JOIN_PRODUCT_VIDEO_CARD);) {

            if (rsProductCPU != null && rsProductRAM != null && rsProductVC != null) {
                while (rsProductCPU.next()) {
                    response.addProductCPU(createProductCPU(rsProductCPU));
                }
                while (rsProductRAM.next()) {
                    response.addProductRAM(createProductRAM(rsProductRAM));
                }
                while (rsProductVC.next()) {
                    response.addProductVC(createProductVC(rsProductVC));
                }

                return response;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static ProductCPU createProductCPU(ResultSet rs) throws SQLException {
        ProductCPU productCPU = new ProductCPU(createProduct(rs));
        productCPU.setProcessorsType(rs.getString("processorsType"));
        productCPU.setSocketType(rs.getString("socketType"));
        productCPU.setCoreName(rs.getString("coreName"));
        productCPU.setNumOfCores(rs.getInt("numOfCores"));
        productCPU.setNumOfThreads(rs.getInt("numOfThreads"));
        productCPU.setOperatingFrequency(rs.getDouble("operatingFrequency"));
        productCPU.setMaxTurboFrequency(rs.getDouble("maxTurboFrequency"));
        return productCPU;
    }

    private static ProductRAM createProductRAM(ResultSet rs) throws SQLException {
        ProductRAM productRAM = new ProductRAM(createProduct(rs));
        productRAM.setCapacity(rs.getString("capacity"));
        productRAM.setSpeed(rs.getString("speed"));
        productRAM.setLatency(rs.getInt("latency"));
        productRAM.setTiming(rs.getString("timing"));
        productRAM.setColor(rs.getString("color"));
        productRAM.setColorLED(rs.getString("colorLED"));
        return productRAM;
    }

    private static ProductVC createProductVC(ResultSet rs) throws SQLException {
        ProductVC productVC = new ProductVC(createProduct(rs));
        productVC.setInterfaceVC(rs.getString("interface"));
        productVC.setChipset(rs.getString("chipset"));
        productVC.setGpu(rs.getString("gpu"));
        productVC.setMemorySize(rs.getInt("memorySize"));
        productVC.setMemoryType(rs.getString("memoryType"));
        productVC.setMaxResolution(rs.getString("maxResolution"));
        productVC.setCooler(rs.getString("cooler"));
        productVC.setMaxGPULength(rs.getInt("maxGPULength"));
        productVC.setCardDimensions(rs.getString("cardDimensions"));
        return productVC;
    }

    private static Product createProduct(ResultSet rs) throws SQLException {
        Product product = new Product(rs.getInt("id"));
        product.setModel(rs.getString("model"));
        product.setBrand(rs.getString("brand"));
        product.setName(rs.getString("name"));
        product.setSeries(rs.getString("series"));
        product.setDisplayName(rs.getString("displayName"));
        product.setCategory(rs.getString("category"));
        product.setPrice(rs.getDouble("price"));
        product.setDescription(rs.getString("description"));
        product.setImgSrc(rs.getString("imgSrc"));
        return product;
    }
}
