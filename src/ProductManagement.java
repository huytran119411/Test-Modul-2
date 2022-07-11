import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ProductManagement {
    public ArrayList<Product> productArrayList = new ArrayList<>();

    public Product creatProduct(Scanner scanner) {
        String codeString;
        do {
            System.out.println("Nhập Mã Sản Phẩm");
            codeString = scanner.nextLine();
        }
        while(checkNumber(codeString));
        int code = Integer.parseInt(codeString);
        System.out.println("Nhập Tên Sản Phẩm");
        String name = scanner.nextLine();
        String priceString;
        do {
            System.out.println("Nhập Giá Sản Phẩm");
            priceString = scanner.nextLine();
        }
        while(checkNumber(priceString));
        double price = Double.parseDouble(priceString);
        String quantityString;
        do {
            System.out.println("Nhập Số Lượng Sản Phẩm");
            quantityString = scanner.nextLine();
        }
        while(checkNumber(quantityString));
        int quantity = Integer.parseInt(quantityString);
        System.out.println("Nhập Mô Tả Sản Phẩm");
        String description = scanner.nextLine();
        return new Product(code, name, price, quantity, description);
    }

    public void addProduct(Scanner scanner) {
        Product product = creatProduct(scanner);
        productArrayList.add(product);
        System.out.println("Đã Thêm Sản Phẩm Thành Công!!!");
        writeFile(productArrayList);
    }

    public void displayAllProduct() {
        for (Product product : productArrayList) {
            System.out.println(product);
        }
    }

    public void editProduct(Scanner scanner) {
        System.out.println("Vui Lòng Nhập Mã SP Cần Thay Đổi Thông Tin: ");
        int code = Integer.parseInt(scanner.nextLine());
        int check = 0;
        for (Product product : productArrayList) {
            if (product.getCode() == code) {
                System.out.println(product);
                String codeString;
                do {
                    System.out.println("Nhập Mã Sản Phẩm Cần Đổi:");
                    codeString = scanner.nextLine();
                }
                while(checkNumber(codeString));
                int codeChange = Integer.parseInt(codeString);
                System.out.println("Nhập Tên Sản Phẩm Cần Đổi:");
                String name = scanner.nextLine();
                String priceString;
                do {
                    System.out.println("Nhập Giá Sản Phẩm Cần Đổi:");
                    priceString = scanner.nextLine();
                }
                while(checkNumber(priceString));
                double price = Double.parseDouble(priceString);
                String quantityString;
                do {
                    System.out.println("Nhập Số Lượng Sản Phẩm Cần Đổi:");
                    quantityString = scanner.nextLine();
                }
                while(checkNumber(quantityString));
                int quantity = Integer.parseInt(quantityString);
                System.out.println("Nhập Mô Tả Sản Phẩm Cần Đổi:");
                String description = scanner.nextLine();
                product.setCode(codeChange);
                product.setName(name);
                product.setPrice(price);
                product.setQuatity(quantity);
                product.setDescription(description);
                check++;
                writeFile(productArrayList);
            }
        }
        if (check == 0) {
            System.out.println("Không Tìm Thấy Mã SP Trên!!!");
        }
    }

    public void deleteProduct(Scanner scanner) {
        System.out.println("Vui Lòng Nhập Mã SP Cần Thay Đổi Thông Tin: ");
        int code = Integer.parseInt(scanner.nextLine());
        int check = 0;
        for (Product product : productArrayList) {
            if (product.getCode() == code) {
                System.out.println("Nhập Y nếu muốn xoá!!!");
                String checkDelete = scanner.nextLine();
                checkDelete.toUpperCase();
                if (checkDelete.equals("Y")) {
                    productArrayList.remove(product);
                    System.out.println("Xoá SP Thành Công!!!");
                }
            }
            check++;
            writeFile(productArrayList);
        }
        if (check == 0) {
            System.out.println("Không Tìm Thấy Mã SP Trên!!!");
        }
    }

    public boolean checkNumber(String string) {
        if (Pattern.matches("\\d+", string)) {
            return false;
        }
        System.out.println("Dữ Liệu Nhập Vào Bị Sai Vui Lòng Nhập Lại");
        return true;
    }

    public void writeFile(ArrayList<Product> array) {
        File file = new File("products.csv");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Product product : array) {
                bufferedWriter.write(product.getCode()+", "+product.getName()+", "+product.getPrice()+", "+product.getQuantity()+", "+product.getDescription()+"\n");
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Không tạo được file!");
        }
    }

    public void readFile() {
        File file = new File("products.csv");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                String[] strings = value.split(", ");
                Product product = new Product(Integer.parseInt(strings[0]),strings[1],Double.parseDouble(strings[2]),Integer.parseInt(strings[3]),strings[4]);
                productArrayList.add(product);
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Không tìm thấy file!");
        }
    }

    public void searchMaxPrice() {
        Product max = null;
        for (int i = 0; i < productArrayList.size(); i++) {
            max = productArrayList.get(i);
            if (max.getPrice() < productArrayList.get(i + 1).getPrice()) {
                max = productArrayList.get(i + 1);
            }
        }
        System.out.println(max);
    }

    Comparator<Product> compareUp = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return(int)(o1.getPrice() - o2.getPrice());
        }
    } ;

    Comparator<Product> compareDown = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return (int)(o2.getPrice() - o1.getPrice());
        }
    } ;


    // Sắp xếp tăng dần
    public void displayUp(){
        System.out.println("Sản phẩm được sắp xếp tăng dần ...");
        productArrayList.sort(compareUp);
        displayAllProduct();
    }

    public void displayDown(){
        System.out.println("Sản phẩm được sắp xếp giảm dần ...");
        productArrayList.sort(compareUp);
        displayAllProduct();
    }

}
