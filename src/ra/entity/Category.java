package ra.entity;

import ra.bussiness.IEntity;

import java.io.Serializable;
import java.util.Scanner;

import static ra.presentation.CategoryPresentation.listCategory;

public class Category implements IEntity, Serializable {
    private int id;
    private String name;
    private boolean status;

    public Category() {
    }

    public Category(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void input(Scanner scanner) {
        this.id = inputId(scanner);
        this.name = inputName(scanner);
        this.status = inputStatus(scanner);
    }

    @Override
    public void output() {
        System.out.printf("| %-9d | %-14s | %-8b |\n", this.id, this.name, this.status);
        System.out.println("+-----------+----------------+----------+");
    }

    public static void headerDisplayCategory() {
        String separator = "+-----------+----------------+----------+";
        String header = "|    ID     |      Name      |  Status  |";
        System.out.printf(separator + "\n"
                + header + "\n"
                + separator + "\n"
        );
    }

    @Override
    public String toString() {
        return String.format("| %-9d | %-14s | %-8b |", this.id, this.name, this.status) + "\n" +
                "+-----------+----------------+----------+";
    }

    public int inputId(Scanner scanner) {
        do {
            System.out.println("mã thể loại (số nguyên lớn hơn 0):");
            try {
                int id = Integer.parseInt(scanner.nextLine());

                if (id > 0) {
                    boolean isId = false;
                    for (Category category : listCategory) {
                        if (category.getId() == id) {
                            isId = true;
                            break;
                        }
                    }

                    if (isId) {
                        System.err.println("mã thể loại đã tồn tại! vui lòng nhập lại");
                    } else {
                        return id;
                    }
                } else {
                    System.err.println("mã thể loại có giá trị lớn hơn 0");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập lại kiểu số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } while (true);
    }

    public String inputName(Scanner scanner) {
        do {
            System.out.println("Tên thể loại (từ 6-30 kí tự):");
            String name = scanner.nextLine();

            if (name.trim().length() >= 6 && name.trim().length() <= 30) {
                boolean isName = false;
                for (Category category : listCategory) {
                    if (category.getName().equals(name)) {
                        isName = true;
                        break;
                    }
                }

                if (isName) {
                    System.err.println("tên thể loại đã tồn tại! vui lòng nhập lại");
                } else {
                    return name;
                }
            } else {
                System.err.println("tên thể loại có từ 6-30 kí tự");
            }
        } while (true);
    }

    public boolean inputStatus(Scanner scanner) {
        do {
            System.out.println("trạng thái thể loại:");
            String status = scanner.nextLine();

            if (status.equalsIgnoreCase("true") || status.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(status);
            } else {
                System.err.println("trạng thái thể loại chỉ nhận true/false!");
            }
        } while (true);
    }

    public void updateData(Scanner scanner) {
        boolean isExitUpdate = true;
        do {
            System.out.println("************Câp nhât thông tin************");
            System.out.println("1. cập nhât id");
            System.out.println("2. cập nhât name");
            System.out.println("3. cập nhât status");
            System.out.println("4. thoát");
            System.out.println("lựa chon của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        this.id = inputId(scanner);
                        break;
                    case 2:
                        this.name = inputName(scanner);
                        break;
                    case 3:
                        this.status = inputStatus(scanner);
                        break;
                    case 4:
                        isExitUpdate = false;
                        break;
                    default:
                        System.out.println("nhập lựa chọn từ 1-4!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhâp số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        } while (isExitUpdate);
    }
}
