package edu.uci.inf124.petrwarerestservice.service;

import edu.uci.inf124.petrwarerestservice.db.Database;
import edu.uci.inf124.petrwarerestservice.db.DatabaseUtils;
import edu.uci.inf124.petrwarerestservice.model.Product;
import edu.uci.inf124.petrwarerestservice.model.ProductCPU;
import edu.uci.inf124.petrwarerestservice.model.ProductRAM;
import edu.uci.inf124.petrwarerestservice.model.ProductVC;
import edu.uci.inf124.petrwarerestservice.response.ProductListResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductService {

    public static ProductListResponse getAllProducts() {
        ProductListResponse response = new ProductListResponse();

        try(Connection conn = Database.dbConnect();
            ResultSet rsProductCPU = DatabaseUtils.getQueryResults(conn, "SELECT * FROM product JOIN product_cpu ON id=product_id");
            ResultSet rsProductRAM = DatabaseUtils.getQueryResults(conn, "SELECT * FROM product JOIN product_ram ON id=product_id");
            ResultSet rsProductVC = DatabaseUtils.getQueryResults(conn, "SELECT * FROM product JOIN product_video_card ON id=product_id");) {

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

    public static ProductListResponse getProduct(int id) {
        ProductListResponse response = new ProductListResponse();

        try (Connection conn = Database.dbConnect();
             PreparedStatement stmtSelectCat = conn.prepareStatement("SELECT category FROM product WHERE id=?");) {

            // Get category of product to use correct category table
            stmtSelectCat.setInt(1, id);
            try(ResultSet rsCat = stmtSelectCat.executeQuery();) {
                String category = "";
                if (rsCat.next()) {
                    category = rsCat.getString("category");
                }
                String categoryTable = "";
                switch (category) {
                    case "cpu":
                        categoryTable = "product_cpu";
                        break;
                    case "ram":
                        categoryTable = "product_ram";
                        break;
                    case "videoCard":
                        categoryTable = "product_video_card";
                }

                // Get product if category exists
                if (!categoryTable.isEmpty()) {
                    String sql = String.format("SELECT * FROM product JOIN %s ON id=product_id WHERE product.id=?",
                            categoryTable);
                    try(PreparedStatement stmtSelectProduct = conn.prepareStatement(sql);) {
                        stmtSelectProduct.setInt(1, id);
                        try(ResultSet rs = stmtSelectProduct.executeQuery();) {
                            if (rs.next()) {
                                String rsCategory = rs.getString("category");
                                switch (rsCategory) {
                                    case "cpu":
                                        response.addProductCPU(createProductCPU(rs));
                                        break;
                                    case "ram":
                                        response.addProductRAM(createProductRAM(rs));
                                        break;
                                    case "videoCard":
                                        response.addProductVC(createProductVC(rs));
                                }

                                return response;
                            }
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ProductListResponse getSearchedProducts(String query) {
        ProductListResponse response = new ProductListResponse();

        String sqlCPU = "SELECT * FROM product JOIN product_cpu ON id=product_id " +
                "WHERE displayName LIKE ? " +
                "OR model LIKE ? ";
        String sqlRAM = "SELECT * FROM product JOIN product_ram ON id=product_id " +
                "WHERE displayName LIKE ? " +
                "OR model LIKE ? ";
        String sqlVC = "SELECT * FROM product JOIN product_video_card ON id=product_id " +
                "WHERE displayName LIKE ? " +
                "OR model LIKE ? ";
        String key = "%" + query + "%";

        try(Connection conn = Database.dbConnect();
            ResultSet rsCPU = DatabaseUtils.getQueryResultsPrepared(conn, sqlCPU, key, key);
            ResultSet rsRAM = DatabaseUtils.getQueryResultsPrepared(conn, sqlRAM, key, key);
            ResultSet rsVC = DatabaseUtils.getQueryResultsPrepared(conn, sqlVC, key, key)) {

            while (rsCPU.next()) {
                response.addProductCPU(createProductCPU(rsCPU));
            }
            while (rsRAM.next()) {
                response.addProductRAM(createProductRAM(rsRAM));
            }
            while (rsVC.next()) {
                response.addProductVC(createProductVC(rsVC));
            }

            return response;

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
