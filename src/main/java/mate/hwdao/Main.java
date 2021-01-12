package mate.hwdao;

import mate.hwdao.lib.Inject;
import mate.hwdao.lib.Injector;
import mate.hwdao.model.Manufacturer;
import mate.hwdao.service.ManufacturerService;
import mate.hwdao.service.ManufacturerServiceImpl;

public class Main {

    private static final Injector injector = Injector.getInstance(Main.class.getPackageName());
    @Inject
    private static mate.hwdao.dao.impl.ManufactureDaoImpl manufactureDaoImpl;
    @Inject
    private static ManufacturerServiceImpl manufacturerService;

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService) injector.getInstance(ManufacturerService.class);
        Manufacturer manufacturerHonda = new Manufacturer("Honda", "Jupan");
        manufacturerService.create(manufacturerHonda);
        Manufacturer manufacturerZaz = new Manufacturer("ZAZ", "Ukraine");
        manufacturerService.create(manufacturerZaz);
        System.out.println("How to work manufacturer service.create: " + manufacturerZaz.toString());
        System.out.println("How to work manufacturer service.getAll: " + manufacturerService.getAll().toString());
        System.out.println("How to work manufacturer service.getId: " + manufacturerService.get(manufacturerHonda.getId()).toString());
        manufacturerService.update(manufacturerHonda).setCountry("Japan");
        System.out.println("After change country: " + manufacturerHonda.toString());
        manufacturerService.delete(manufacturerHonda.getId());
        System.out.println(manufacturerService.getAll().toString());

    }

}
