package FinalExam;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MobileManager {
    private List<Phone> mobiles = new ArrayList<>();
    private int nextId = 1;

    public void addMobile(Phone mobile) {
        mobile.id = nextId++;
        mobiles.add(mobile);
    }

    public void removeMobile(int id) throws Exception {
        for (Phone mobile : mobiles) {
            if (mobile.getId() == id) {
                mobiles.remove(mobile);
                return;
            }
        }
        throw new Exception("Không tìm thấy điện thoại với ID " + id);
    }

    public void displayMobiles() {
        if (mobiles.isEmpty()) {
            System.out.println("Danh sách điện thoại trống.");
            return;
        }
        for (Phone mobile : mobiles) {
            System.out.println(mobile.getDetails());
        }
    }

    public Phone findMobile(int id) {
        for (Phone mobile : mobiles) {
            if (mobile.getId() == id) {
                return mobile;
            }
        }
        return null;
    }

    public void saveToCSV(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Phone mobile : mobiles) {
                writer.write(mobile.getDetails().replace(", ", ","));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu vào file: " + e.getMessage());
        }
    }
}