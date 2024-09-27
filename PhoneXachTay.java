package FinalExam;

public class PhoneXachTay extends Phone {
    private String quocGiaNhapKhau;
    private String trangThai;

    public PhoneXachTay(int id, String ten, double gia, int soLuong, String nhaSanXuat, String quocGiaNhapKhau, String trangThai) {
        super(id, ten, gia, soLuong, nhaSanXuat);
        this.quocGiaNhapKhau = quocGiaNhapKhau;
        this.trangThai = trangThai;
    }

    @Override
    public String getDetails() {
        return super.toString() + ", Quốc gia nhập khẩu: " + quocGiaNhapKhau + ", Trạng thái: " + trangThai;
    }
}
