package crm_project22.entity;

import java.sql.Date;

public class Task {
	private int idCV;
	private String ten;
	private String mota;
	private Date ngaybatdau;
	private Date ngayketthuc;
	private DuAn duan;
	private TrangThai trangthai;
	private NguoiDung nguoidung;
	public int getIdCV() {
		return idCV;
	}
	public void setIdCV(int idCV) {
		this.idCV = idCV;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public Date getNgaybatdau() {
		return ngaybatdau;
	}
	public void setNgaybatdau(Date ngaybatdau) {
		this.ngaybatdau = ngaybatdau;
	}
	public Date getNgayketthuc() {
		return ngayketthuc;
	}
	public void setNgayketthuc(Date ngayketthuc) {
		this.ngayketthuc = ngayketthuc;
	}
	public DuAn getDuan() {
		return duan;
	}
	public void setDuan(DuAn duan) {
		this.duan = duan;
	}
	public TrangThai getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(TrangThai trangthai) {
		this.trangthai = trangthai;
	}
	public NguoiDung getNguoidung() {
		return nguoidung;
	}
	public void setNguoidung(NguoiDung nguoidung) {
		this.nguoidung = nguoidung;
	}
	
}
