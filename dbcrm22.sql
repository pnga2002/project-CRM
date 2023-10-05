CREATE DATABASE crm;
USE crm;

CREATE TABLE LoaiThanhVien(
	id int auto_increment,
	ten varchar(255),
	mota varchar(255),
	primary key(id)
);

CREATE TABLE NguoiDung(
	id int auto_increment,
	email varchar(255),
	matkhau varchar(255),
	fullname varchar(255),
	diachi varchar (255),
	soDienThoai varchar(11),
	id_loaithanhvien int,
	primary key(id)
);

CREATE TABLE TrangThai(
	idTrangThai int auto_increment,
	ten varchar(255),
	mota varchar(255),
	
	primary key(idTrangThai)
)

CREATE TABLE DuAn(
	id int auto_increment,
	tenduan varchar(255),
	mota varchar(255),
	ngaybatdau timestamp,
	ngayketthuc timestamp,
	maquanly int,
	matrangthai int,
	
	primary key(id)
);

CREATE TABLE CongViec(
	idCV int auto_increment,
	ten varchar(255),
	mota varchar(255),
	ngaybatdau timestamp,
	ngayketthuc timestamp,
	maduan int,
	matrangthai int,
	manguoidung int;
	primary key(idCV)
)

CREATE TABLE DuAn_NguoiDung(
	maDuAn int,
	maNguoiDung int,
	primary key(maDuAn,maNguoiDung),
	constraint FK_DAND_duan foreign key(maDuAn) references DuAn(id),
	constraint FK_DAND_nguoidung foreign key(maNguoiDung) references NguoiDung(id)
)


//FK NGUOIDUNG_LOAINGUOIDUNG
ALTER TABLE NguoiDung ADD CONSTRAINT FK_id_loaithanhvien_NguoiDung 
FOREIGN KEY(id_loaithanhvien) REFERENCES LoaiThanhVien(id);

//FK DUAN_TRANGTHAI && DUAN_NGUOIDUNG
ALTER TABLE DuAn ADD CONSTRAINT FK_id_duan_trangthai 
FOREIGN KEY(matrangthai) REFERENCES TrangThai(idTrangThai);

//FK CONGVIEC_DUAN  &&  CONGVIEC_TRANGTHAI
ALTER TABLE CongViec ADD CONSTRAINT FK_id_congviec_duan
FOREIGN KEY(maduan) REFERENCES DuAn(id);
ALTER TABLE CongViec ADD CONSTRAINT FK_id_congviec_nguoidung
FOREIGN KEY(manguoidung) REFERENCES NguoiDung(id);
ALTER TABLE CongViec ADD CONSTRAINT FK_id_congviec_trangthai
FOREIGN KEY(matrangthai) REFERENCES TrangThai(idTrangThai);





