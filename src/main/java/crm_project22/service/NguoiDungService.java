package crm_project22.service;

import java.util.List;

import crm_project22.entity.NguoiDung;
import crm_project22.repository.NguoiDungRepository;

public class NguoiDungService {
	private NguoiDungRepository nguoiDungRepository = new NguoiDungRepository();
	
	public List<NguoiDung> getAllNguoiDung(){
		return nguoiDungRepository.getAllNguoiDung();
	}
	public List<NguoiDung> getAllLead(){
		return nguoiDungRepository.getAllLead();
	}
	public boolean insert(String email, String matKhau, String fullname, String diaChi, String soDienThoai, int id_loaiThanhVien) {
		int count = nguoiDungRepository.insert(email, matKhau, fullname, diaChi, soDienThoai, id_loaiThanhVien);
		return count>0;
	}
	public boolean deleteById(int id) {
		int count = nguoiDungRepository.deleteById(id);
		return count > 0;
	}
	public NguoiDung findById(int id) {
		return nguoiDungRepository.findById(id);
	}
	public boolean update(int id, String email, String matKhau, String fullname, String diaChi, String soDienThoai, int id_loaiThanhVien) {
		int count = nguoiDungRepository.update(id, email, matKhau, fullname, diaChi, soDienThoai, id_loaiThanhVien);
		return count > 0;
	}
}
