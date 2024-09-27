package FinalExam;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        MobileManager mobileManager = new MobileManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Thêm mới");
            System.out.println("2. Xóa");
            System.out.println("3. Xem danh sách");
            System.out.println("4. Tìm kiếm");
            System.out.println("5. Lưu vào file CSV");
            System.out.println("6. Thoát");
            System.out.print("Chọn một tùy chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập tên điện thoại: ");
                    String name = scanner.nextLine();

                    double price = 0;
                    boolean validPrice = false;
                    while (!validPrice) {
                        try {
                            System.out.print("Nhập giá: ");
                            price = scanner.nextDouble();
                            if (price < 0) {
                                System.out.println("Giá không thể âm. Vui lòng nhập lại.");
                            } else {
                                validPrice = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Giá không hợp lệ. Vui lòng nhập một số.");
                            scanner.nextLine();
                        }
                    }

                    int quantity = 0;
                    boolean validQuantity = false;
                    while (!validQuantity) {
                        try {
                            System.out.print("Nhập số lượng: ");
                            quantity = scanner.nextInt();
                            if (quantity < 0) {
                                System.out.println("Số lượng không thể âm. Vui lòng nhập lại.");
                            } else {
                                validQuantity = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Số lượng không hợp lệ. Vui lòng nhập một số nguyên.");
                            scanner.nextLine();
                        }
                    }

                    scanner.nextLine();
                    System.out.print("Nhập nhà sản xuất: ");
                    String manufacturer = scanner.nextLine();

                    int type = 0;
                    boolean validType = false;
                    while (!validType) {
                        System.out.print("Nhập loại (1: Chính hãng, 2: Nhập khẩu): ");
                        type = scanner.nextInt();
                        if (type == 1 || type == 2) {
                            validType = true;
                        } else {
                            System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập 1 hoặc 2.");
                        }
                    }

                    if (type == 1) {
                        LocalDate warrantyDate = null;
                        boolean validDate = false;

                        while (!validDate) {
                            System.out.print("Nhập thời gian bảo hành (ngày, định dạng dd-MM-yyyy): ");
                            String warrantyInput = scanner.nextLine().trim();
                            if (warrantyInput.isEmpty()) {
                                System.out.println("Ngày không được để trống. Vui lòng nhập lại.");
                                continue;
                            }

                            try {
                                warrantyDate = LocalDate.parse(warrantyInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                validDate = true;
                            } catch (DateTimeParseException e) {
                                System.out.println("Định dạng ngày không hợp lệ. Vui lòng nhập lại.");
                            }
                        }

                        System.out.print("Nhập phạm vi bảo hành (Toan Quoc/Quoc Te): ");
                        String warrantyScope = scanner.nextLine();
                        mobileManager.addMobile(new PhoneChinhHang(0, name, price, quantity, manufacturer, warrantyDate, warrantyScope));
                    } else if (type == 2) {
                        scanner.nextLine();
                        System.out.print("Nhập quốc gia nhập khẩu: ");
                        String importCountry = scanner.nextLine();
                        System.out.print("Nhập trạng thái (Da sua chua/Dang sua chua): ");
                        String status = scanner.nextLine();
                        mobileManager.addMobile(new PhoneXachTay(0, name, price, quantity, manufacturer, importCountry, status));
                    } else {
                        System.out.println("Lựa chọn không hợp lệ.");
                    }
                    break;

                case 2:
                    System.out.print("Nhập ID điện thoại cần xóa: ");
                    int idToRemove = scanner.nextInt();
                    try {
                        mobileManager.removeMobile(idToRemove);
                        System.out.println("Đã xóa điện thoại với ID: " + idToRemove);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;


                case 3:
                    mobileManager.displayMobiles();
                    break;

                case 4:
                    System.out.print("Nhập ID điện thoại cần tìm: ");
                    int idToFind = scanner.nextInt();
                    Phone foundMobile = mobileManager.findMobile(idToFind);
                    if (foundMobile != null) {
                        System.out.println("Tìm thấy: " + foundMobile.getDetails());
                    } else {
                        System.out.println("Không tìm thấy điện thoại với ID " + idToFind);
                    }
                    break;

                case 5:
                    String savePath = "D:/Duy/codegym/baitap/module2/src/FinalExam/mobile.csv";
                    try {
                        mobileManager.saveToCSV(savePath);
                        System.out.println("Đã lưu danh sách vào file.");
                    } catch (Exception e) {
                        System.out.println("Lỗi khi lưu vào file: " + e.getMessage());
                    }
                    break;


                case 6:
                    System.out.println("Thoát chương trình.");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
