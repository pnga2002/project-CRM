package crm_project22.service;

import java.sql.Date;
import java.util.List;

import crm_project22.entity.DuAn;
import crm_project22.entity.Task;
import crm_project22.repository.ProjectRepository;

public class ProjectService {
	private ProjectRepository projectRepositoty = new ProjectRepository();
	
	public List<DuAn> getAllProject(){
		return projectRepositoty.getAllProject();
	}
	public boolean insert(String tenduan, String mota, Date ngaybatdau, Date ngayketthuc, int maquanly) {
		int count = projectRepositoty.insert(tenduan, mota, ngaybatdau, ngayketthuc, maquanly);
		return count > 0;
	}
	public DuAn findById(int id) {
		return projectRepositoty.findById(id);
	}
	public boolean update(int id, String tenduan, String mota, Date ngaybatdau, Date ngayketthuc, int maquanly) {
		int count = projectRepositoty.update(id, tenduan, mota, ngaybatdau, ngayketthuc, maquanly);
		return count > 0;
	}
	public boolean deleteById(int id) {
		int count = projectRepositoty.deleteById(id);
		return count > 0;
	}
	public List<Task> getTaskNewInProjectByID(int id, int idtt){
		return projectRepositoty.getTaskNewInProjectByID(id, idtt);
	}
}
