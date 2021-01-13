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
        return Optional.of(Storage.listCar
                .stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .get());
    }

    @Override
    public List<Car> getAll() {
        return Storage.listCar;
    }

    @Override
    public Car update(Car car) {
        for (int i = 0; i < Storage.listCar.size(); i++) {
            if (Storage.listCar.get(i).getId().equals(car.getId())) {
                Storage.listCar.remove(i);
                Storage.listCar.set(i, car);
            }
        }
        return car;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.listCar.removeIf(x -> x.getId().equals(id));
    }
}
  