 package project.tarena.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter("/*")
public class SessionFilter implements Filter {

   
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		//声明一个请求对象
		HttpServletRequest request=(HttpServletRequest) arg0;
		//得到相应的请求地址?
		String uri=request.getRequestURI();
		if(validate(uri)){
			//判断session是否存在
			//创建session
			HttpSession session=request.getSession();
			Object flag=session.getAttribute("managerUser");
			if(flag!=null){
				//登录成功
				arg2.doFilter(arg0, arg1);
			}else{
				//没有登录
				HttpServletResponse response=(HttpServletResponse) arg1;
				response.sendRedirect("/tarenaNetCTOSS/login.jsp");
			}
			
		}else{
			//将login.jsp和LoginServlet直接放行
			arg2.doFilter(arg0, arg1);
		}
	}
	/**
	 * 判断是否需要session验证
	 * @param uri	请求地址
	 * @return true 需要  false 不需要
	 */
	public boolean validate(String uri){
		boolean flag=true;
		List<String> list=new ArrayList<String>();
		list.add("login.jsp");
		list.add("LoginServlet");
		list.add("images");
		list.add("styles");
		list.add("ImageServlet");
		list.add("index.jsp");
		for(String msg:list){
			if(flag&&uri.indexOf(msg)!=-1){
				flag=false;
				break;
			}
		}
		return flag;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
