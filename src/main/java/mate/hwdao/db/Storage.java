package mate.hwdao.db;

import java.util.ArrayList;
import java.util.List;
import mate.hwdao.model.Car;
import mate.hwdao.model.Driver;
import mate.hwdao.model.Manufacturer;

public class Storage {
    public static final List<Car> cars = new ArrayList<>();
    public static final List<Driver> drivers = new ArrayList<>();
    public static final List<Manufacturer> manufacturers = new ArrayList<>();
    private static Long manufacturerId = 0L;
    private static Long driverId = 0L;
    private static Long carId = 0L;

    public static void addManufacturer(Manufacturer manufacturer) {
        manufacturer.setId(++manufacturerId);
        manufacturers.add(manufacturer);
    }

    public static void addCar(Car car) {
        car.setId(++carId);
        cars.add(car);
    }

    public static void addDriver(Driver driver) {
        driver.setId(++driverId);
        drivers.add(driver);
    }
}
