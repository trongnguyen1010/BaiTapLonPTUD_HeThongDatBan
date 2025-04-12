package Entity;

public class Ban {
    private String maBan;
    private String trangThai;
    private String maKhuVuc; // Có thể null nếu bàn không thuộc khu vực nào

    public Ban(String maBan, String trangThai, String maKhuVuc) {
        this.maBan = maBan;
        this.trangThai = trangThai;
        this.maKhuVuc = maKhuVuc;
    }

    // Getters và Setters
    public String getMaBan() {
        return maBan;
    }
    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public String getTrangThai() {
        return trangThai;
    }
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaKhuVuc() {
        return maKhuVuc;
    }
    public void setMaKhuVuc(String maKhuVuc) {
        this.maKhuVuc = maKhuVuc;
    }
}
