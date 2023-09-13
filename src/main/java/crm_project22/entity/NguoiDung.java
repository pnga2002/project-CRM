package crm_project22.entity;

public class NguoiDung {
	private int id;
	private String email;
	private String matKhau;
	private String fullname;
	private String diaChi;
	private String soDienThoai;
	private LoaiThanhVien loaiThanhVien;
	public LoaiThanhVien getLoaiThanhVien() {
		return loaiThanhVien;
	}
	public void setLoaiThanhVien(LoaiThanhVien loaiThanhVien) {
		this.loaiThanhVien = loaiThanhVien;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	
	
}
