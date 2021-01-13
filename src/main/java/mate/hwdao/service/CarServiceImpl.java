package mate.hwdao.service;

import java.util.ArrayList;
import java.util.List;
import mate.hwdao.dao.CarDao;
import mate.hwdao.db.Storage;
import mate.hwdao.lib.Inject;
import mate.hwdao.lib.Service;
import mate.hwdao.model.Car;
import mate.hwdao.model.Driver;

@Service
public class CarServiceImpl implements CarService {
    @Inject
    CarDao carDao;

    @Override
    public Car create(Car car) {
        carDao.create(car);
        return car;
    }

    @Override
    public Car get(Long id) {
        return carDao.get(id)
                .orElseThrow(() -> new RuntimeException("Can't find car with id " + id));
    }

    @Override
    public List<Car> getAll() {
        return carDao.getAll();
    }

    @Override
    public Car update(Car car) {
        carDao.update(car);
        return car;
    }

    @Override
    public boolean delete(Long id) {
        return carDao.delete(id);
    }

    @Override
    public void addDriverToCar(Driver driver, Car car) {
        car.getDrivers().add(driver);
    }

    @Override
    public void removeDriverFromCar(Driver driver, Car car) {
        car.getDrivers().remove(driver);
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        List<Car> newListCar = new ArrayList<>();
        for (Car car : Storage.listCar) {
            for (Driver driver : car.getDrivers()) {
                if (driver.getId().equals(driverId)) {
                    newListCar.add(car);
                }
            }
        }
        return newListCar;
    }
}
