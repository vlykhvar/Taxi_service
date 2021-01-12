package mate.hwdao;

import mate.hwdao.db.Storage;
import mate.hwdao.lib.Inject;
import mate.hwdao.lib.Injector;
import mate.hwdao.model.Car;
import mate.hwdao.model.Driver;
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
        Car carVida = new Car("Vida", manufacturerZaz);
        Driver driverSofia = new Driver("Sofia", "0001");
        Storage.listDriver.add(driverSofia);
        Driver driverBogdan = new Driver("Bogdan", "0002");
        Storage.listDriver.add(driverBogdan);
        Driver driverRoman = new Driver("Roman" , "0003");
        Storage.listDriver.add(driverRoman);
        manufacturerService.update(manufacturerHonda).setCountry("Japan");
        System.out.println("After change country: " + manufacturerHonda.toString());
        manufacturerService.delete(manufacturerHonda.getId());
        System.out.println(manufacturerService.getAll().toString());
        carVida.getDrivers().add(driverSofia);
        carVida.getDrivers().add(driverBogdan);
        carVida.getDrivers().add(driverRoman);
        System.out.println(carVida.toString());
    }
}
