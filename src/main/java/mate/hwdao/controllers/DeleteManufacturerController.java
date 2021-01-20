package mate.hwdao.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.hwdao.Main;
import mate.hwdao.lib.Injector;
import mate.hwdao.service.ManufacturerService;

public class DeleteManufacturerController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance(Main.class.getPackageName());
    private final ManufacturerService manufacturerService =
            (ManufacturerService) injector.getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String manufacturerId = req.getParameter("id");
        Long id = Long.valueOf(manufacturerId);
        manufacturerService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/manufacturer/");
    }
}
