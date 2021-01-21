package mate.hwdao.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.hwdao.Main;
import mate.hwdao.lib.Injector;
import mate.hwdao.service.DriverService;

public class DeleteDriverController extends HttpServlet {

    private static final Injector injector =
            Injector.getInstance(Main.class.getPackageName());
    private final DriverService driverService =
            (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String driverId = req.getParameter("id");
        Long id = Long.valueOf(driverId);
        driverService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/drivers/");
    }
}
