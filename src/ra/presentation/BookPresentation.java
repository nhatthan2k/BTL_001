package ra.presentation;

import ra.bussinessIpm.BookIpm;
import ra.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookPresentation {
    public static List<Book> listBook = new ArrayList<>();
    public static void bookMenu(Scanner scanner) {
        listBook = BookIpm.readBookFromFile();
        boolean isExitBook = true;
        do {
            System.out.println("➢ ===== QUẢN LÝ SÁCH =====\n" +
                    "1. Thêm mới sách\n" +
                    "2. Cập nhật thông tin sách\n" +
                    "3. Xóa sách\n" +
                    "4. Tìm kiếm sách \n" +
                    "5. Hiển thị danh sách sách theo nhóm thể loại\n" +
                    "6. Quay lại");
            System.out.println("Lựa chọn của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        BookIpm.addBook(scanner);
                        BookIpm.writeBookToFile();
                        break;
                    case 2:
                        BookIpm.updateBook(scanner);
                        BookIpm.writeBookToFile();
                        break;
                    case 3:
                        BookIpm.deleteBook(scanner);
                        BookIpm.writeBookToFile();
                        break;
                    case 4:
                        BookIpm.searchBook(scanner);
                        break;
                    case 5:
                        BookIpm.booksOfCategory();
                        break;
                    case 6:
                        isExitBook = false;
                        break;
                    default:
                        System.out.println("vui lòng lựa chon 1 - 6!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        }while (isExitBook);
    }
}
