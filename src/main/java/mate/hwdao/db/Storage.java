package mate.hwdao.db;

import java.util.ArrayList;
import java.util.List;
import mate.hwdao.model.Car;
import mate.hwdao.model.Driver;
import mate.hwdao.model.Manufacturer;

public class Storage {
    public static final List<Car> listCar = new ArrayList<>();
    public static final List<Driver> listDriver = new ArrayList<>();
    public static final List<Manufacturer> listManufacturer = new ArrayList<>();
    private static Long manufacturerId = 0L;
    private static Long driverId = 0L;
    private static Long carId = 0L;

    public static void addManufacturer(Manufacturer manufacturer) {
        manufacturer.setId(++manufacturerId);
        listManufacturer.add(manufacturer);
    }

    public static void addCar(Car car) {
        car.setId(++carId);
        listCar.add(car);
    }

    public static void addDriver(Driver driver) {
        driver.setId(++driverId);
        listDriver.add(driver);
    }
}
