package mate.hwdao.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.hwdao.dao.CarDao;
import mate.hwdao.db.Storage;
import mate.hwdao.lib.Dao;
import mate.hwdao.model.Car;

@Dao
public class CarDaoImpl implements CarDao {

    @Override
    public Car create(Car car) {
        Storage.addCar(car);
        return car;
    }

    @Override
    public Optional<Car> get(Long id) {
        return Optional.of(Storage.cars
                .stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .get());
    }

    @Override
    public List<Car> getAll() {
        return Storage.cars;
    }

    @Override
    public Car update(Car car) {
        for (int i = 0; i < Storage.cars.size(); i++) {
            if (Storage.cars.get(i).getId().equals(car.getId())) {
                Storage.cars.remove(i);
                Storage.cars.set(i, car);
            }
        }
        return car;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.cars.removeIf(x -> x.getId().equals(id));
    }
}
