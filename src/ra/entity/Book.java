package ra.entity;

import ra.bussiness.IEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

import static ra.entity.Category.headerDisplayCategory;
import static ra.presentation.BookPresentation.listBook;
import static ra.presentation.CategoryPresentation.listCategory;

public class Book implements IEntity, Serializable {
    private String id;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private String description;
    private int categoryId;

    public Book() {
    }

    public Book(String id, String title, String author, String publisher, int year, String description, int categoryId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.description = description;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public void input(Scanner scanner) {
        this.id = inputIdBook(scanner);
        this.title = inputTitle(scanner);
        this.author = inputAuthor(scanner);
        this.publisher = inputPublisher(scanner);
        this.year = inputYear(scanner);
        this.description = inputDescription(scanner);
        this.categoryId = inputCategoryId(scanner);
    }

    public String displayCategoryName(int categoryId) {
        for (Category category : listCategory) {
            if (category.getId() == categoryId) {
                return category.getName();
            }
        }
        return "";
    }

    @Override
    public void output() {
        System.out.printf("| %-15s | %-15s | %-15s | %-5d | %-15s | %-15s | %-15s |\n",
                this.id, this.title, this.author, this.year, this.publisher, this.description, displayCategoryName(this.categoryId));
        System.out.println("+-----------------+-----------------+-----------------+-------+-----------------+-----------------+-----------------+");
    }

    public static void headerDisplayBook() {
        String separator = "+-----------------+-----------------+-----------------+-------+-----------------+-----------------+-----------------+";
        String header = "|      ID         |      Title      |      Author     | Year  |    Publisher    |   Description   |   CategoryName  |";
        System.out.printf(separator + "\n"
                + header + "\n"
                + separator + "\n"
        );
    }

    @Override
    public String toString() {
        return String.format("| %-15s | %-15s | %-15s | %-5d | %-15s | %-15s | %-15s |",
                this.id, this.title, this.author, this.year, this.publisher, this.description, displayCategoryName(this.categoryId)) + "\n" +
                "+-----------------+-----------------+-----------------+-------+-----------------+-----------------+-----------------+";
    }

    public String inputIdBook(Scanner scanner) {
        do {
            System.out.println("mã sách (bắt đầu bằng “B”, độ dài 4 kí tự):");
            String id = scanner.nextLine();

            if (id.length() == 4) {
                if (id.startsWith("B")) {
                    boolean isIdBook = false;
                    for (Book book : listBook) {
                        if (book.getId().equals(id)) {
                            isIdBook = true;
                            break;
                        }
                    }

                    if (isIdBook) {
                        System.err.println("mã sách đã tồn tại! vui lòng nhập lại");
                    } else {
                        return id;
                    }
                } else {
                    System.err.println("mã sách bắt đầu là B! vui lòng nhập lại");
                }
            } else {
                System.err.println("mã sách có 4 kí tự! vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputTitle(Scanner scanner) {
        do {
            System.out.println("Tiêu đề sách (từ 6-50 kí tự):");
            String title = scanner.nextLine();

            if (title.trim().length() >= 6 && title.trim().length() <= 50) {
                boolean isTitle = false;
                for (Book book : listBook) {
                    if (book.getTitle().equals(title)) {
                        isTitle = true;
                        break;
                    }
                }

                if (isTitle) {
                    System.err.println("tiêu đề sách đã tồn tại! vui lòng nhập lại");
                } else {
                    return title;
                }
            } else {
                System.err.println("tiêu đề sách có từ 6-50 kí tự! vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputAuthor(Scanner scanner) {
        do {
            System.out.println("Tên tác giả:");
            String author = scanner.nextLine();

            if (author.trim().isEmpty()) {
                System.err.println("không được bỏ trống tên tác giả! vui lòng nhâp lại");
            } else {
                return author;
            }
        } while (true);
    }

    public String inputPublisher(Scanner scanner) {
        do {
            System.out.println("Nhà xuất bản:");
            String publisher = scanner.nextLine();

            if (publisher.trim().isEmpty()) {
                System.err.println("không được bỏ trống nhà xuất bản! vui lòng nhâp lại");
            } else {
                return publisher;
            }
        } while (true);
    }

    public int inputYear(Scanner scanner) {
        do {
            System.out.println("Năm xuất bản:");
            try {
                int year = Integer.parseInt(scanner.nextLine());

                if (year >= 1970 && year <= LocalDate.now().getYear()) {
                    return year;
                } else {
                    System.err.println("Năm xuất bản chỉ nhận từ năm 1970 đến hiện tại");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } while (true);
    }

    public String inputDescription(Scanner scanner) {
        do {
            System.out.println("mô tả sách:");
            String description = scanner.nextLine();

            if (description.trim().isEmpty()) {
                System.err.println("không được bỏ trống mô tả! vui lòng nhâp lại");
            } else {
                return description;
            }
        } while (true);
    }

    public int inputCategoryId(Scanner scanner) {
        do {
            System.out.println("mã thể loại đã lưu:");
            for (Category category : listCategory) {
                headerDisplayCategory();
                category.output();
            }

            System.out.println("mã thể loại:");
            int categoryId = 0;
            try {
                categoryId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

            for (Category category : listCategory) {
                if (category.getId() == categoryId) {
                    return categoryId;
                }
            }

            System.err.println("không tồn tại mã thể loại trên");
        } while (true);
    }

    public void updateData(Scanner scanner) {
        boolean isExitUpdate = true;
        do {
            System.out.println("************Câp nhât thông tin************");
            System.out.println("1. cập nhât id");
            System.out.println("2. cập nhât title");
            System.out.println("3. cập nhât author");
            System.out.println("4. cập nhât publisher");
            System.out.println("5. cập nhật year");
            System.out.println("6. cập nhật description");
            System.out.println("7. cập nhật categoryId");
            System.out.println("8. thoát");
            System.out.println("lựa chon của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        this.id = inputIdBook(scanner);
                        break;
                    case 2:
                        this.title = inputTitle(scanner);
                        break;
                    case 3:
                        this.author = inputAuthor(scanner);
                        break;
                    case 4:
                        this.publisher = inputPublisher(scanner);
                        break;
                    case 5:
                        this.year = inputYear(scanner);
                        break;
                    case 6:
                        this.description = inputDescription(scanner);
                        break;
                    case 7:
                        this.categoryId = inputCategoryId(scanner);
                        break;
                    case 8:
                        isExitUpdate = false;
                        break;
                    default:
                        System.out.println("nhập lựa chọn từ 1-8");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhâp số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        } while (isExitUpdate);
    }
}
