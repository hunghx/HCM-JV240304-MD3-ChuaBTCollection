package run;

import business.CategoryBusiness;
import business.ICategoryDesign;
import business.IProductDesign;
import business.ProductBusiness;
import entity.Category;
import entity.Product;
import util.InputMethods;

import java.util.List;

public class ProductManagement {
    private static final ICategoryDesign categoryBusiness = new CategoryBusiness();
    private static final IProductDesign productBusiness = new ProductBusiness();

    public static void main(String[] args) {
        while (true) {
            System.out.println("------------------Product-Management----------------");
            System.out.println("1. Quản lí danh mục");
            System.out.println("2. Quản lí sản phẩm");
            System.out.println("3. Thoát");
            System.out.println("Lựa chọn của bạn : ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    // menu danh mục
                    menuCategory();
                    break;
                case 2:
                    menuProduct();
                    break;
                case 3:
                    System.out.println("Tạm biệt");
                    break;
                default:
                    System.err.println("Lựa chọn ko chính xác , vui lòng nhập lại");
            }
            if (choice == 3) {
                break;
            }
        }
    }

    private static void menuCategory() {
        while (true) {
            System.out.println("------------------Category-Menu----------------");
            System.out.println("1. Thêm mơi");
            System.out.println("2. Hiển thị ");
            System.out.println("3. Sửa tên");
            System.out.println("4. Xóa ");
            System.out.println("5. Quay lại ");
            System.out.println("Lựa chọn của bạn : ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    addNewCategory();
                    break;
                case 2:
                    showCategoryList();
                    break;
                case 3:
                    editCategory();
                    break;
                case 4:
                    deleteCategory();
                    break;
                case 5:

                    break;
                default:
                    System.err.println("Lựa chọn ko chính xác , vui lòng nhập lại");
            }
            if (choice == 5) {
                break;
            }
        }
    }

    private static void editCategory() {
        System.out.println("Nhập mã danh mục  cần sưa ");
        int catId = InputMethods.getInteger();
        Category catEdit =categoryBusiness.findById(catId);
        if (catEdit == null) {
            System.err.println("id không tồn tại");
        } else {
            catEdit.inputData();
            categoryBusiness.update(catEdit);
            System.out.println("Cập nhật thành công");
        }
    }
    private static void addNewCategory() {
        System.out.println("Nhập số lượng cần thêm mới");
        byte n = InputMethods.getByte();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin cho danh mục thứ :" + (i + 1));
            Category newCategory = new Category(); // chứa logic tự tăng
            newCategory.inputData(); // cho nhập thông tin
            categoryBusiness.create(newCategory); // luu lại
        }
        // thông báo thành công
        System.out.println("Đã thêm mới thành cong " + n + " dnah mục !");
    }


    private static void showCategoryList() {
        // lấy ra danh sách
        List<Category> categories = categoryBusiness.findAll();
        if (categories.isEmpty()) {
            System.err.println("Danh sách trống !");
        } else {
            System.out.println("-------- Danh sac danh mục --------");
            for (Category cat : categories) {
                cat.displayData();
            }
        }
    }

    private static void deleteCategory() {
        System.out.println("Nhập mã danh mục  cần xóa ");
        int catId = InputMethods.getInteger();
        // kiểm tra tồn tại
        // kiểm tra xem có sách thuộc danh mục này không
//        if (productBusiness.existByCategoryId(catId)){
//            System.err.println("Danh mục này có chưa sách nên ko thể xóa");
//            return;
//        }
        if (categoryBusiness.findById(catId) == null) {
            System.err.println("id không tồn tại");
        } else {
            categoryBusiness.deleteById(catId);
            System.out.println(" đã xóa thành công");
        }
    }

    private static void menuProduct() {
        while (true) {
            System.out.println("------------------Product-Menu----------------");
            System.out.println("1. Thêm mơi");
            System.out.println("2. Hiển thị ");
            System.out.println("3. Sửa tên");
            System.out.println("4. Xóa ");
            System.out.println("5. Tìm kiếm ");
            System.out.println("6. Quay lại ");
            System.out.println("Lựa chọn của bạn : ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    addNewProduct();
                    break;
                case 2:
                    showProductList();
                    break;
                case 3:

                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    break;
                case 6:

                    break;
                default:
                    System.err.println("Lựa chọn ko chính xác , vui lòng nhập lại");
            }
            if (choice == 6) {
                break;
            }
        }
    }

    private static void addNewProduct() {
        if (categoryBusiness.findAll().isEmpty()) {
            System.err.println("Chuwa cos danh muc , hay quay laij them danh muc truoc khi them sp");
            return;
        }
        System.out.println("Nhập số lượng cần thêm mới");
        byte n = InputMethods.getByte();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin cho sanr phaamr thứ :" + (i + 1));
            Product newProduct = new Product(); // chứa logic tự tăng
            newProduct.inputData(); // cho nhập thông tin
            productBusiness.create(newProduct); // luu lại
        }
        // thông báo thành công
        System.out.println("Đã thêm mới thành cong " + n + " sản phẩm !");
    }

    private static void showProductList() {
        // lấy ra danh sách
        List<Product> products = productBusiness.findAll();
        if (products.isEmpty()) {
            System.err.println("Danh sách trống !");
        } else {
            System.out.println("-------- Danh sac sản phẩm --------");
            for (Product pro : products) {
                pro.displayData();
            }
        }
    }

    private static void deleteProduct() {
        System.out.println("Nhập mã sản phẩm  cần xóa ");
        int proId = InputMethods.getInteger();
        // kiểm tra tồn tại
        if (productBusiness.findById(proId) == null) {
            System.err.println("id không tồn tại");
        } else {
            productBusiness.deleteById(proId);
            System.out.println(" đã xóa thành công");
        }
    }
}
