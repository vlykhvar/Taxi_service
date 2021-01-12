package mate.hwdao;

import mate.hwdao.lib.Inject;
import mate.hwdao.lib.Injector;
import mate.hwdao.model.Car;
import mate.hwdao.model.Driver;
import mate.hwdao.model.Manufacturer;
import mate.hwdao.service.ManufacturerService;

public class Main {
    private static final Injector injector = Injector.getInstance(Main.class.getPackageName());
    @Inject
    private static Manufacturer manufacturer;
    @Inject
    private static mate.hwdao.dao.Impl.ManufactureDaoImpl manufactureDaoImpl;

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService) injector.getInstance(ManufacturerService.class);
        // initialize field values using setters or constructor
        Car car = new Car("Civic", new Manufacturer("Honda", "Japan"));
        Driver driver = new Driver("Vasa", "0001");
        car.getDrivers().add(driver);

        System.out.println(car.toString());
        manufacturerService.create(manufacturer);


        // same for all other crud methods and for all models
    }

}
