package mate.hwdao.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.hwdao.Main;
import mate.hwdao.lib.Injector;
import mate.hwdao.model.Manufacturer;
import mate.hwdao.service.ManufacturerService;

public class DisplayAllManufacturesController extends HttpServlet {

    private static final Injector injector =
            Injector.getInstance(Main.class.getPackageName());
    private final ManufacturerService manufacturerService =
            (ManufacturerService) injector.getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Manufacturer> manufacturers = manufacturerService.getAll();
        req.setAttribute("manufacturers", manufacturers);
        req.getRequestDispatcher("/WEB-INF/views/manufacturer/get.jsp").forward(req, resp);
    }
}
