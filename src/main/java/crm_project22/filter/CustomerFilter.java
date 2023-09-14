package crm_project22.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebFilter(filterName="customerFilter",urlPatterns = {"/role-add","/role-edit"})
public class CustomerFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest)request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		String path = servletRequest.getServletPath();
		HttpSession session = servletRequest.getSession();
		String roleName = (String) session.getAttribute("roleName");
		String contextPath = servletRequest.getContextPath();
		switch (path) {
		case "/role-add":{
			if(roleName!=null &&roleName.toUpperCase().equals("ADMIN")) {
				filter.doFilter(servletRequest, response);
			}
			else {
				servletResponse.sendRedirect(contextPath+"/");
			}
			break;
		}
		case "/role-edit":{
			if(roleName!=null &&roleName.toUpperCase().equals("ADMIN")) {
				filter.doFilter(servletRequest, response);
			}
			else {
				servletResponse.sendRedirect(contextPath+"/");
			}
			break;
		}
		default:{
			break;
		}
		}
		
	}
	
}
