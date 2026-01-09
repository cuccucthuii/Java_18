package business;

import entity.Product;
import entity.StatiticsProduct;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductBusiness {

    // Display product
    public static List<Product> findAllProducts() {
        Connection conn = null; // Connect DB
        CallableStatement stmt = null; // Call Proc / Func
        List<Product> list = null; // List product current null

        try {
            conn = ConnectionDB.openConnection(); // Open connection
            // Call Proc/ Func
            stmt = conn.prepareCall("{call get_all_product()}");
            boolean hasData = stmt.execute();
            if (hasData) {
                list = new ArrayList<>();
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    Product product = new Product();
                    product.setProduct_id(rs.getInt("product_id"));
                    product.setProduct_name(rs.getString("product_name"));
                    product.setProduct_price(rs.getDouble("product_price"));
                    product.setProduct_title(rs.getString("product_title"));
                    product.setProduct_created(LocalDate.parse(rs.getString("product_created")));
                    product.setProduct_catalog(rs.getString("product_catalog"));
                    product.setProduct_status(Boolean.parseBoolean(rs.getString("product_status")));
                    list.add(product);
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // print error
        } finally {
            ConnectionDB.closeConnection(conn, stmt);
        }
        return list;
    }

    public static boolean createProduct(Product product) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("call create_product(?,?,?,?,?,?)");
            stmt.setString(1, product.getProduct_name());
            stmt.setDouble(2, product.getProduct_price());
            stmt.setString(3, product.getProduct_title());
            stmt.setDate(4, Date.valueOf(product.getProduct_created()));
            stmt.setString(5, product.getProduct_catalog());
            stmt.setBoolean(6, product.isProduct_status());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, stmt);
        }
        return false;
    }

    public static boolean updateProduct(Product product) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("call update_product(?,?,?,?,?,?,?)");
            stmt.setInt(1, product.getProduct_id());
            stmt.setString(2, product.getProduct_name());
            stmt.setDouble(3, product.getProduct_price());
            stmt.setString(4, product.getProduct_title());
            stmt.setDate(5, Date.valueOf(product.getProduct_created()));
            stmt.setString(6, product.getProduct_catalog());
            stmt.setBoolean(7, product.isProduct_status());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, stmt);
        }
        return false;
    }

    public static boolean deleteProduct(int product_id) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("call delete_product_by_id(?)");
            stmt.setInt(1, product_id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, stmt);
        }
        return false;
    }


    public static Product existsProductById(int product_id) {
        Connection conn = null;
        CallableStatement stmt = null;
        Product product = null;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{call find_by_product_id(?)}");
            stmt.setInt(1, product_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setProduct_id(rs.getInt("product_id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(rs.getDouble("product_price"));
                product.setProduct_title(rs.getString("product_title"));
                product.setProduct_created(LocalDate.parse(rs.getString("product_created")));
                product.setProduct_catalog(rs.getString("product_catalog"));
                product.setProduct_status(Boolean.parseBoolean(rs.getString("product_status")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, stmt);
        }
        return product;
    }

    public static List<Product> getProductByName(String name) {
        Connection conn = null;
        CallableStatement stmt = null;
        List<Product> list = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{call find_product_by_name(?)}");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getInt("product_id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(rs.getDouble("product_price"));
                product.setProduct_title(rs.getString("product_title"));
                product.setProduct_created(LocalDate.parse(rs.getString("product_created")));
                product.setProduct_catalog(rs.getString("product_catalog"));
                product.setProduct_status(Boolean.parseBoolean(rs.getString("product_status")));
                list.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, stmt);
        }
        return list;
    }

    public static List<Product> getProductByPriceAsc() {
        Connection conn = null;
        CallableStatement stmt = null;
        List<Product> list = null;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{call soft_product_by_price_asc()}");
            boolean hasData = stmt.execute();
            if (hasData) {
                ResultSet rs = stmt.getResultSet();
                list = new ArrayList<>();
                while (rs.next()) {
                    Product product = new Product();
                    product.setProduct_id(rs.getInt("product_id"));
                    product.setProduct_name(rs.getString("product_name"));
                    product.setProduct_price(rs.getDouble("product_price"));
                    product.setProduct_title(rs.getString("product_title"));
                    product.setProduct_created(LocalDate.parse(rs.getString("product_created")));
                    product.setProduct_catalog(rs.getString("product_catalog"));
                    product.setProduct_status(Boolean.parseBoolean(rs.getString("product_status")));
                    list.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, stmt);
        }
        return list;
    }

    public static List<StatiticsProduct> getStaticProductByCatalog(){
        Connection conn = null;
        CallableStatement stmt = null;
        List<StatiticsProduct> list = null;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{call statitics_by_catalog()}");
            boolean hasData = stmt.execute();
            if (hasData) {
                ResultSet rs = stmt.getResultSet();
                list = new ArrayList<>();
                while (rs.next()) {
                    StatiticsProduct statitics = new StatiticsProduct();
                    statitics.setCatalog(rs.getString("product_catalog"));
                    statitics.setTotal_products(rs.getInt("total_product"));
                    list.add(statitics);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, stmt);
        }
        return list;
    }
}
