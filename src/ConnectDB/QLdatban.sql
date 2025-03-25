CREATE DATABASE QuanLyNhaHang;
GO
USE QuanLyNhaHang;
go
CREATE TABLE NhanVien (
    MaNhanVien VARCHAR(255) PRIMARY KEY,
    Ten VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,
    ChucVu VARCHAR(255) NOT NULL,
    GioiTinh BIT NOT NULL,
    SDT VARCHAR(255) NOT NULL
);

CREATE TABLE TaiKhoan (
    TenDangNhap VARCHAR(255) PRIMARY KEY,
    MatKhau VARCHAR(255) NOT NULL,
    VaiTro VARCHAR(255) NOT NULL,
    MaNhanVien VARCHAR(255),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien)
);

CREATE TABLE KhachHang (
    MaKhachHang NVARCHAR(10) PRIMARY KEY,
    TenKhachHang VARCHAR(255) NOT NULL,
    GioiTinh NVARCHAR(10) NOT NULL,
    Email VARCHAR(255) NOT NULL
);

CREATE TABLE HoaDon (
    MaHoaDon VARCHAR(255) PRIMARY KEY,
    NgayLap DATE NOT NULL,
    PhuongThuc VARCHAR(255) NOT NULL,
    TenKhachHang VARCHAR(255),
    SdtKhachHang VARCHAR(255),
    TongTien FLOAT(10) NOT NULL,
    MaNhanVien VARCHAR(255),
    MaKhachHang NVARCHAR(10),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang)
);

CREATE TABLE MonAn (
    MaMonAn VARCHAR(255) PRIMARY KEY,
    TenMonAn VARCHAR(255) NOT NULL,
    DonGia FLOAT(10) NOT NULL,
    SoLuong INT NOT NULL,
    MaLoai INT NOT NULL
);

CREATE TABLE Ban (
    MaBan VARCHAR(255) PRIMARY KEY,
    TrangThai VARCHAR(255) NOT NULL,
    MaKhuVuc VARCHAR(255)
);

CREATE TABLE KhuVuc (
    MaKhuVuc VARCHAR(255) PRIMARY KEY,
    TenKhuVuc VARCHAR(255) NOT NULL
);

CREATE TABLE PhieuDatBan (
    MaPhieu VARCHAR(255) PRIMARY KEY,
    ThoiGianDat INT NOT NULL,
    MaBan VARCHAR(255) NOT NULL,
    MaKhachHang NVARCHAR(10),
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang),
    FOREIGN KEY (MaBan) REFERENCES Ban(MaBan)
);

CREATE TABLE ChiTietPhieuDatBan (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    MaBan VARCHAR(255) NOT NULL,
    GhiChu VARCHAR(255),
    ThongTinDatBan VARCHAR(255),
    MaPhieu VARCHAR(255),
    FOREIGN KEY (MaPhieu) REFERENCES PhieuDatBan(MaPhieu)
);

INSERT INTO NhanVien (MaNhanVien, Ten, Email, ChucVu, GioiTinh, SDT)
VALUES ('NV001', 'Nguyen Phuoc HAU', 'nguyenhau51496@gmail.com', 'Manager', 1, '0123456789');

INSERT INTO TaiKhoan (TenDangNhap, MatKhau, VaiTro, MaNhanVien)
VALUES ('phuochau', '1234', 'Admin', 'NV001');

INSERT INTO KhachHang (MaKhachHang, TenKhachHang, GioiTinh, Email)
VALUES ('KH001', 'Nguyen Van A', 'Nam', '123@gmail.com');

INSERT INTO MonAn (MaMonAn, TenMonAn, DonGia, SoLuong, MaLoai)
VALUES ('MA001', 'Bánh mì', 25000, 100, 1);