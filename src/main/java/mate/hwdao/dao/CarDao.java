package mate.hwdao.dao;

import java.util.List;
import java.util.Optional;
import mate.hwdao.model.Car;

public interface CarDao extends GenericDao<Car, Long> {
    Car create(Car car);

    Optional<Car> get(Long id);

    List<Car> getAll();

    Car update(Car car);

    boolean delete(Long id);

    List<Car> getAllByDriver(Long driverId);
}
