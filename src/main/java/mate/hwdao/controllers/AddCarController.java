package mate.hwdao.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.hwdao.Main;
import mate.hwdao.lib.Injector;
import mate.hwdao.model.Car;
import mate.hwdao.model.Manufacturer;
import mate.hwdao.service.CarService;
import mate.hwdao.service.ManufacturerService;

public class AddCarController extends HttpServlet {

    private static final Injector injector =
            Injector.getInstance(Main.class.getPackageName());
    private final CarService carService =
            (CarService) injector.getInstance(CarService.class);
    private final ManufacturerService manufacturerService =
            (ManufacturerService) injector.getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/cars/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String model = req.getParameter("model");
        String manufacturerId = req.getParameter("manufacturerId");
        Manufacturer manufacturer = manufacturerService.get(Long.valueOf(manufacturerId));
        carService.create(new Car(model, manufacturer));
    }
}
