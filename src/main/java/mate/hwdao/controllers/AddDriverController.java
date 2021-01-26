package mate.hwdao.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.hwdao.Main;
import mate.hwdao.lib.Injector;
import mate.hwdao.model.Driver;
import mate.hwdao.service.DriverService;

public class AddDriverController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance(Main.class.getPackageName());
    private final DriverService driverService =
            (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/drivers/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String licenseNumber = request.getParameter("licenceNumber");
        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        driverService.create(new Driver(name, licenseNumber, login, password));
        request.setAttribute("message", "Driver was added");
        request.getRequestDispatcher("/WEB-INF/views/drivers/add.jsp").forward(request, response);
    }
}
