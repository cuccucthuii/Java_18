package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Product {
    private int product_id;
    private String product_name;
    private double product_price;
    private String product_title;
    private LocalDate product_created;
    private String product_catalog;
    private boolean product_status;

    public Product() {
    }

    public Product(int product_id, String product_name, double product_price, String product_title, LocalDate product_created, String product_catalog, boolean product_status) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_title = product_title;
        this.product_created = product_created;
        this.product_catalog = product_catalog;
        this.product_status = product_status;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public LocalDate getProduct_created() {
        return product_created;
    }

    public void setProduct_created(LocalDate product_created) {
        this.product_created = product_created;
    }

    public String getProduct_catalog() {
        return product_catalog;
    }

    public void setProduct_catalog(String product_catalog) {
        this.product_catalog = product_catalog;
    }

    public boolean isProduct_status() {
        return product_status;
    }

    public void setProduct_status(boolean product_status) {
        this.product_status = product_status;
    }

    public void inputData(Scanner scanner) {
        nameInput(scanner);
        priceInput(scanner);
        titleInput(scanner);
        createdInput(scanner);
        catalogInput(scanner);
        statusInput(scanner);
    }

    public void nameInput(Scanner scanner) {
        System.out.println("Enter product name: ");
        do {
            String product_name = scanner.nextLine().trim();
            if (!product_name.isEmpty()) {
                this.product_name = product_name;
                break;
            } else {
                System.err.println("Product name is empty");
            }
        } while (true);
    }

    public void priceInput(Scanner scanner) {
        System.out.println("Enter product price: ");
        do {
            try {
                String product_price = scanner.nextLine().trim();
                if (!product_price.isEmpty()) {
                    this.product_price = Double.parseDouble(product_price);
                    break;
                } else {
                    System.err.println("Product price is empty");
                }
            } catch (NumberFormatException e) {
                System.err.println("Product is type Double");
            }
        } while (true);
    }

    public void titleInput(Scanner scanner) {
        System.out.println("Enter product title: ");
        do {
            try {
                String product_title = scanner.nextLine().trim();
                if (!product_title.isEmpty()) {
                    this.product_title = product_title;
                    break;
                } else {
                    System.err.println("Product title is empty");
                }
            } catch (Exception e) {
                System.err.println("Error Input Title");
            }
        } while (true);
    }

    public void createdInput(Scanner scanner) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Enter product created (dd/MM/yyyy): ");
        do {
            String product_created = scanner.nextLine().trim();
            try {
                this.product_created = LocalDate.parse(product_created,dtf);
                break;
            } catch (DateTimeParseException e) {
                System.err.println("Invalid Date Format");
            }
        } while (true);
    }

    public void catalogInput(Scanner scanner) {
        System.out.println("Enter product catalog: ");
        do {
            try {
                String product_catalog = scanner.nextLine().trim();
                if (!product_catalog.isEmpty()) {
                    this.product_catalog = product_catalog;
                    break;
                } else {
                    System.err.println("Product catalog is empty");
                }
            } catch (Exception e) {
                System.err.println("Invalid Product Catalog");
            }
        } while (true);
    }

    public void statusInput(Scanner scanner) {
        System.out.println("Enter product status(true/false): ");
        do {
            try {
                String product_status = scanner.nextLine();
                if (product_status.equalsIgnoreCase("true") || product_status.equalsIgnoreCase("false")) {
                    this.product_status = Boolean.parseBoolean(product_status);
                    break;
                } else {
                    System.err.println("Product status is empty or different true/false");
                }
            } catch (Exception e) {
                System.err.println("Invalid Product Status");
            }
        } while (true);
    }


    @Override
    public String toString() {
        return """
                Product {
                    id = %d
                    name = '%s'
                    price = %.2f
                    title = '%s'
                    created = %s
                    catalog = '%s'
                    status = %s
                }
                """.formatted(
                product_id,
                product_name,
                product_price,
                product_title,
                product_created,
                product_catalog,
                product_status ? "Còn hàng" : "Hết hàng"
        );
    }

}
