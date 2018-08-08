package demo.util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 编码过滤器
 */

// maven.org
//@WebFilter("/*")
public class EncodingFilter implements Filter {

    private static final String ENCODING = "UTF-8";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // JSP ISO-8859-1
        request.setCharacterEncoding(ENCODING);
        response.setCharacterEncoding(ENCODING);
        // chain 链子
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
