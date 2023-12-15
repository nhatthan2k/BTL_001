package ra.bussinessIpm;

import ra.entity.Book;
import ra.entity.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static ra.presentation.BookPresentation.listBook;
import static ra.presentation.CategoryPresentation.listCategory;

public class CategoryIpm {
    public static void addCategory(Scanner scanner) {
        boolean isExist = true;
        int numAdd = 0;
        System.out.println("nhập số lượng thể loại bạn muốn thêm:");
        do {
            try {
                numAdd = Integer.parseInt(scanner.nextLine());
                isExist = false;
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } while (isExist);

        for (int i = 0; i < numAdd; i++) {
            Category category = new Category();
            category.input(scanner);
            listCategory.add(category);
        }
    }

    public static void sortCategoryByName() {
        Category.headerDisplayCategory();
        listCategory.stream().sorted(Comparator.comparing(Category::getName)).forEach(System.out::println);
    }

    public static void quantityBookofCatagory() {
        for (Category category : listCategory) {
            long cntCatagory = listBook.stream().filter(book -> book.getCategoryId() == category.getId()).count();
            System.out.printf("thể loại %s có %d sách\n", category.getName(), cntCatagory);
        }
    }

    public static void updateCategory(Scanner scanner) {
        System.out.println("nhập vào mã thể loại cần cập nhật:");
        int updateId = Integer.parseInt(scanner.nextLine());
        boolean isUpdate = false;

        for (Category category : listCategory) {
            if (category.getId() == updateId) {
                category.updateData(scanner);
                isUpdate = true;
                System.out.println("cập nhật thành công!");
                break;
            }
        }

        if (!isUpdate) {
            System.out.println("Không tồn tại mã thể loại!");
        }
    }

    public static void deleteCategory(Scanner scanner) {
        System.out.println("mã thể loại cần xóa:");
        int deleteId = Integer.parseInt(scanner.nextLine());
        boolean isDelete = false;
        int deleteIndex = -1;

        for (int i = 0; i < listCategory.size(); i++) {
            Category category = listCategory.get(i);
            if (category.getId() == deleteId) {
                deleteIndex = i;
                isDelete = true;
                break;
            }
        }

        if (!isDelete) {
            System.out.println("Không tồn tại mã thể loại!");
        } else {
            if (CategoryIpm.hasBook(deleteId)) {
                System.out.println("thể loại chứa sách! không thể xóa");
            } else {
                listCategory.remove(deleteIndex);
                System.out.println("xóa sách thành công!");
            }
        }
    }

    public static boolean hasBook(int catagoryId) {
        for (Book book : listBook) {
            if (book.getCategoryId() == catagoryId) {
                return true;
            }
        }
        return false;
    }

    public static List<Category> readCategoryFromFile() {
        List<Category> listCategoryRead = null;
        File file = new File("categories.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            listCategoryRead = (List<Category>) ois.readObject();
        } catch (Exception ex) {
            listCategoryRead = new ArrayList<>();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return listCategoryRead;
    }

    public static void writeCategorytoFile() {
        File file = new File("categories.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listCategory);
            oos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}

