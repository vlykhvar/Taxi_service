package mate.hwdao.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.hwdao.Main;
import mate.hwdao.lib.Injector;
import mate.hwdao.model.Driver;
import mate.hwdao.service.DriverService;

public class DisplayAllDriversController extends HttpServlet {

    private static final Injector injector =
            Injector.getInstance(Main.class.getPackageName());
    private final DriverService driverService =
            (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Driver> drivers = driverService.getAll();
        req.setAttribute("drivers", drivers);
        req.getRequestDispatcher("/WEB-INF/views/drivers/get.jsp").forward(req, resp);
    }
}
