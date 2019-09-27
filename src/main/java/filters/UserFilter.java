package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter( "/user")
public class UserFilter implements Filter {
    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.filterConfig = fConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for(Cookie cookie : cookies){
                if (cookie.getValue().equals("user")){
                    filterChain.doFilter(servletRequest,servletResponse);
                    break;
                }
            }
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
