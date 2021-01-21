package mate.hwdao.service;

import java.util.List;
import mate.hwdao.model.Car;
import mate.hwdao.model.Driver;

public interface CarService extends Service<Car, Long> {
    void addDriverToCar(Driver driver, Car car);

    void removeDriverFromCar(Driver driver, Car car);

    List<Car> getAllByDriver(Long driverId);
}
