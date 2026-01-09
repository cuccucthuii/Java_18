package presentation;

import business.ProductBusiness;
import entity.Product;

import java.util.List;
import java.util.Scanner;

public class ProductManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductBusiness pb = new ProductBusiness();
        int choice = 0;
        do {
            System.out.println("********************PRODUCT MANAGEMENT****************");
            System.out.println("1. Danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm theo tên sản phẩm");
            System.out.println("6. Sắp xếp sản phẩm theo giá tăng dần");
            System.out.println("7. Thống kê số lượng sản phẩm theo danh mục");
            System.out.println("8. Thoát");
            System.out.println("Lua chon cua ban: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.err.println("Valid choice");
            }
            switch (choice) {
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    createProduct(sc);
                    break;
                case 3:
                    updateProductById(sc);
                    break;
                case 4:
                    deleteProductById(sc);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Choice 1-8");

            }
        } while (true);
    }

    public static void displayAllProducts() {
        List<Product> list = ProductBusiness.findAllProducts();
        list.forEach(System.out::println);
    }

    public static void createProduct(Scanner scanner) {
        Product product = new Product();
        product.inputData(scanner);

        boolean result = ProductBusiness.createProduct(product);
        if (result) {
            System.out.println("Create successfully!");
        } else {
            System.out.println("Create failed!");
        }
    }

    public static void updateProductById(Scanner scanner) {
        System.out.println("Enter product ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        int choice = 0;
        boolean isExit = true;
        Product pro = ProductBusiness.existsProductById(id);
        if (pro == null) {
            System.err.println("Product ID " + id + " not found!");
        } else {
           do {
               System.out.println("MENU DATA UPDATE");
               System.out.println("1. Name");
               System.out.println("2. Price");
               System.out.println("3. Title");
               System.out.println("4. created");
               System.out.println("5. catalog");
               System.out.println("6. status");
               System.out.println("7. Exit");
               System.out.println("Enter your choice: ");
               try {
                   choice = Integer.parseInt(scanner.nextLine());
               } catch (Exception e) {
                   System.err.println("Invalid choice");
               }
               switch (choice) {
                   case 1:
                       pro.nameInput(scanner);
                       break;
                   case 2:
                       pro.priceInput(scanner);
                       break;
                   case 3:
                       pro.titleInput(scanner);
                       break;
                   case 4:
                       pro.createdInput(scanner);
                       break;
                   case 5:
                       pro.catalogInput(scanner);
                       break;
                   case 6:
                       pro.statusInput(scanner);
                       break;
                   case 7:
                       System.out.println("Exit successfully!");
                       isExit = false;
                       break;
                   default:
                       System.out.println("Choice 1-7");
               }
           }while (isExit);
        }
        boolean result = ProductBusiness.updateProduct(pro);
        if (result) {
            System.out.println("Update successfully!");
        }else  {
            System.out.println("Update failed!");
        }
    }

    public static void deleteProductById(Scanner scanner) {
        System.out.println("Enter product ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Product product = ProductBusiness.existsProductById(id);
        if (product == null) {
            System.err.println("Product ID " + id + " not found!");
            return;
        }else{
            boolean result = ProductBusiness.deleteProduct(id);
            if (result) {
                System.out.println("Delete successfully!");
            }else{
                System.out.println("Delete failed!");
            }
        }
    }

}
