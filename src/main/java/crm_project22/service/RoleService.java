package crm_project22.service;

import java.util.List;

import crm_project22.entity.LoaiThanhVien;
import crm_project22.repository.LoaiNguoiDungRepository;

public class RoleService {
	private LoaiNguoiDungRepository loaiNguoiDungRepository = new LoaiNguoiDungRepository();
	
	public boolean insert(String ten, String mota) {
		int count = loaiNguoiDungRepository.insert(ten, mota);
		return count > 0;
	}
	public List<LoaiThanhVien> getAllLoaiThanhVien(){
		return  loaiNguoiDungRepository.getAllLoaiThanhVien();
	}
	public boolean deleteById(int id) {
		int count = loaiNguoiDungRepository.deleteById(id);
		return count>0;
	}
	public LoaiThanhVien findById(int id) {
		return loaiNguoiDungRepository.findById(id);
	}
	public boolean update(int id, String ten, String mota) {
		int count = loaiNguoiDungRepository.update(id, ten, mota);
		return count > 0;
	}
}
