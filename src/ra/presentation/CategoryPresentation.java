package ra.presentation;

import ra.bussinessIpm.CategoryIpm;
import ra.entity.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryPresentation {
    public static List<Category> listCategory = new ArrayList<>();

    public static void categoryMenu(Scanner scanner) {
        listCategory = CategoryIpm.readCategoryFromFile();
        boolean isExitCategory = true;
        do {
            System.out.println("➢ ===== QUẢN LÝ THỂ LOẠI =====\n" +
                    "1. Thêm mới thể loại\n" +
                    "2. Hiển thị danh sách theo tên A – Z\n" +
                    "3. Thống kê thể loại và số sách có trong mỗi thể loại\n" +
                    "4. Cập nhật thể loại\n" +
                    "5. Xóa thể loại\n" +
                    "6. Quay lại");
            System.out.println("Lựa chọn của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        CategoryIpm.addCategory(scanner);
                        CategoryIpm.writeCategorytoFile();
                        break;
                    case 2:
                        CategoryIpm.sortCategoryByName();
                        break;
                    case 3:
                        CategoryIpm.quantityBookofCatagory();
                        break;
                    case 4:
                        CategoryIpm.updateCategory(scanner);
                        CategoryIpm.writeCategorytoFile();
                        break;
                    case 5:
                        CategoryIpm.deleteCategory(scanner);
                        CategoryIpm.writeCategorytoFile();
                        break;
                    case 6:
                        isExitCategory = false;
                        break;
                    default:
                        System.out.println("vui lòng lựa chon 1 - 6!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        } while (isExitCategory);
    }
}
