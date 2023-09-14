package crm_project22.entity;

import java.util.Date;

//id int auto_increment,
//tenduan varchar(255),
//mota varchar(255),
//ngaybatdau timestamp,
//ngayketthuc timestamp,
//maquanly int,
//matrangthai int,
public class DuAn {
	private int id;
	private String tenduan;
	private String mota;
	private Date ngaybatdau;
	private Date ngayketthuc;
	private NguoiDung quanly;
	private TrangThai trangthai;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTenduan() {
		return tenduan;
	}
	public void setTenduan(String tenduan) {
		this.tenduan = tenduan;
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
	public NguoiDung getQuanly() {
		return quanly;
	}
	public void setQuanly(NguoiDung quanly) {
		this.quanly = quanly;
	}
	public TrangThai getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(TrangThai trangthai) {
		this.trangthai = trangthai;
	}
	
}
