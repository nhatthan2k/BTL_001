import ra.presentation.BookPresentation;
import ra.presentation.CategoryPresentation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("➢ ===== QUẢN LÝ THƯ VIỆN =====\n" +
                    "1. Quản lý Thể loại\n" +
                    "2. Quản lý Sách\n" +
                    "3. Thoát");
            System.out.println("lựa chọn của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        CategoryPresentation.categoryMenu(scanner);
                        break;
                    case 2:
                        BookPresentation.bookMenu(scanner);
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.out.println("nhập lựa chọn từ 1 - 3!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }while (true);
    }
}