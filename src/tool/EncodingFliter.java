package tool;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = { "/*" })
public class EncodingFliter implements Filter {

@Override
public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
		throws IOException, ServletException {
	arg0.setCharacterEncoding("UTF-8");
	arg1.setContentType("text/html;charset=UTF-8");
	System.out.println("フィルタの前処理");

	arg2.doFilter(arg0, arg1);
	System.out.println("フィルタの後処理");
}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

}
