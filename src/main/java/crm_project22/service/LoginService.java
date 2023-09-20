package crm_project22.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import crm_project22.entity.NguoiDung;
import crm_project22.repository.NguoiDungRepository;

/**
 * 
 * @author ASUS
 *  Tên class của service phải thể hiện nó xử lý code cho controller nào
 *  Ví dụ: LoginService xử lý code cho LoginController
 *  
 *  - Tên hàm trong service phải dặt gợi nhớ tới tính năng đang xử lý
 */
public class LoginService {
	private NguoiDungRepository nguoiDungRepository = new NguoiDungRepository();
	public boolean checkLogin(HttpServletRequest request,String email, String password) {
		List<NguoiDung> listNguoiDung = nguoiDungRepository.findByEmailAndPassword(email, password);
		if(listNguoiDung.size()>0) {
			HttpSession session = request.getSession();
			session.setAttribute("roleName", listNguoiDung.get(0).getLoaiThanhVien().getTen());
			session.setAttribute("userID", listNguoiDung.get(0).getId());
		}
	
		return listNguoiDung.size()>0;
	}
	
}
