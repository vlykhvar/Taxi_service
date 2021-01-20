package mate.hwdao.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.hwdao.Main;
import mate.hwdao.lib.Injector;
import mate.hwdao.model.Car;
import mate.hwdao.model.Driver;
import mate.hwdao.service.CarService;
import mate.hwdao.service.DriverService;

public class AddDriverToCarController extends HttpServlet {

    private static final Injector injector =
            Injector.getInstance(Main.class.getPackageName());
    private final CarService carService =
            (CarService) injector.getInstance(CarService.class);
    private final DriverService driverService =
            (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/cars/drivers/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String driverId = req.getParameter("driverId");
        String carId = req.getParameter("carId");
        Car car = carService.get(Long.valueOf(carId));
        Driver driver = driverService.get(Long.valueOf(driverId));
        carService.addDriverToCar(driver, car);
        resp.sendRedirect(req.getContextPath() + "/cars/");
    }
}
