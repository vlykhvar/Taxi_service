package mate.hwdao.web.filters;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {
    private static final String DRIVER_ID = "driver_id";
    private static Set<String> availableUrls;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        availableUrls = new HashSet<>();
        availableUrls.add("/drivers/login");
        availableUrls.add("/drivers/add");
        availableUrls.add("/");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = req.getServletPath();
        if (availableUrls.contains(url)) {
            filterChain.doFilter(req, response);
            return;
        }
        Long driverId = (Long) req.getSession().getAttribute(DRIVER_ID);
        if (driverId == null) {
            response.sendRedirect("/");
            return;
        }
        filterChain.doFilter(req, response);
    }

    @Override
    public void destroy() {

    }
}
