package project.tarena.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import project.tarena.action.MyRequest;

/**
 * Servlet Filter implementation class TranscodFilter
 */
@WebFilter("/*")
public class TranscodFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		//拿到HttpServletRequest对象
		HttpServletRequest request=(HttpServletRequest)arg0;
		String method=request.getMethod();
		if("post".equalsIgnoreCase(method)){
			request.setCharacterEncoding("UTF-8");
			arg2.doFilter(request, arg1);
		}else{
			//字符集转换
			MyRequest myReqeust=new MyRequest(request);
			arg2.doFilter(myReqeust, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
