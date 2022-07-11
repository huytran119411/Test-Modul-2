import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManagement productManagement = new ProductManagement();
        productManagement.readFile();
        int choice;
        do {
            System.out.println("================================MENU================================");
            System.out.println("*           1. Hiển Thị Tất Cả Sản Phẩm                            *");
            System.out.println("*           2. Thêm Sản Phẩm                                       *");
            System.out.println("*           3. Sửa Sản Phẩm                                        *");
            System.out.println("*           4. Xoá Sản Phẩm                                        *");
            System.out.println("*           5. Sắp Xếp Tăng Dần                                    *");
            System.out.println("*           6. Tìm Kiếm SP Đắt Nhất                                *");
            System.out.println("*           7. Đọc Từ File                                         *");
            System.out.println("*           8. Ghi Vào File                                        *");
            System.out.println("*           9. Đăng Xuất                                           *");
            System.out.println("====================================================================");
            System.out.println("Nhập Lựa Chọn: ");
           choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> productManagement.displayAllProduct();
                case 2 -> productManagement.addProduct(scanner);
                case 3 -> productManagement.editProduct(scanner);
                case 4 -> productManagement.deleteProduct(scanner);
                case 5 -> productManagement.displayUp();
                case 6 -> productManagement.searchMaxPrice();
//                case 7 ->
//                case 8 ->
            }
        }
        while (choice!=9);
    }
    }
