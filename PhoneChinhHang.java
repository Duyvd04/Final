package FinalExam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PhoneChinhHang extends Phone {
    private LocalDate thoiGianBaoHanh;
    private String phamViBaoHanh;

    public PhoneChinhHang(int id, String ten, double gia, int soLuong, String nhaSanXuat, LocalDate thoiGianBaoHanh, String phamViBaoHanh) {
        super(id, ten, gia, soLuong, nhaSanXuat);
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.phamViBaoHanh = phamViBaoHanh;
    }

    @Override
    public String getDetails() {
        return super.toString() + ", Thời gian bảo hành: " + thoiGianBaoHanh.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ", Phạm vi bảo hành: " + phamViBaoHanh;
    }
}
