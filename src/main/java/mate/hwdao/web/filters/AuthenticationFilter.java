package mate.hwdao.web.filters;

import mate.hwdao.Main;
import mate.hwdao.lib.Injector;
import mate.hwdao.service.DriverService;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AuthenticationFilter implements Filter {
    private static final String DRIVER_ID = "driver_id";
    private static final Injector injector =
            Injector.getInstance(Main.class.getPackageName());
    private final DriverService driverService =
            (DriverService) injector.getInstance(DriverService.class);
    private static Set<String> initPage;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    initPage = new HashSet<>();
    initPage.add("/drivers/login");
    initPage.add("/drivers/add");
    initPage.add("/");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = req.getServletPath();
        if (initPage.contains(url)) {
            filterChain.doFilter(req, response);
            return;
        }
        Long driverId = (Long) req.getSession().getAttribute(DRIVER_ID);
        if(driverId == null || driverService.get(driverId) == null) {
            response.sendRedirect("/");
            return;
        }
        filterChain.doFilter(req, response);
    }

    @Override
    public void destroy() {

    }
}
