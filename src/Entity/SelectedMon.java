package Entity;

public class SelectedMon {
    private MonAn monAn;
    private int soLuong;

    public SelectedMon(MonAn monAn, int soLuong) {
        this.monAn = monAn;
        this.soLuong = soLuong;
    }

    public MonAn getMonAn() {
        return monAn;
    }

    public int getSoLuong() {
        return soLuong;
    }
}
