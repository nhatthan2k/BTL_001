package ra.bussinessIpm;

import ra.entity.Book;
import ra.entity.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ra.presentation.BookPresentation.listBook;
import static ra.presentation.CategoryPresentation.listCategory;

public class BookIpm {
    public static void addBook(Scanner scanner) {
        boolean isExist = true;
        int numAdd = 0;
        System.out.println("nhập số lượng sách bạn muốn thêm:");
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
            Book book = new Book();
            book.input(scanner);
            listBook.add(book);
        }
    }
    public static void updateBook(Scanner scanner) {
        System.out.println("nhập vào mã sách cần cập nhật:");
        String updateId = scanner.nextLine();
        boolean isUpdate = false;

        for (Book book : listBook) {
            if (book.getId().equals(updateId)) {
                book.updateData(scanner);
                isUpdate = true;
                break;
            }
        }

        if (!isUpdate) {
            System.out.println("Không tồn tại mã sản phẩm!");
        }
    }
    public static void deleteBook(Scanner scanner) {
        System.out.println("mã sản phẩm cần xóa:");
        String deleteId = scanner.nextLine();
        boolean isDelete = false;

        for (Book book: listBook) {
            if (book.getId().equals(deleteId)) {
                listBook.remove(book);
                isDelete = true;
                System.out.println("xóa sản phẩm thành công!");
                break;
            }
        }

        if (!isDelete) {
            System.out.println("Không tồn tại mã sản phẩm!");
        }
    }
    public static void searchBook(Scanner scanner) {
        System.out.println("tên sản phẩm tìm kiếm:");
        String searchName = scanner.nextLine();

        listBook.stream().filter(book -> book.getTitle().contains(searchName)).forEach(System.out::println);
    }
    public static void booksOfCategory() {
        for (Category category: listCategory) {
            System.out.printf("thể loại %s\n", category.getName());
            listBook.stream().filter(book -> book.getCategoryId() == category.getId()).forEach(System.out::println);
        }
    }
    public static List<Book> readBookFromFile() {
        List<Book> listBookRead = null;
        File file = new File("books.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            listBookRead = (List<Book>) ois.readObject();
        } catch (Exception ex) {
            listBookRead = new ArrayList<>();
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
        return listBookRead;
    }
    public static void writeBookToFile() {
        File file = new File("books.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listBook);
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
