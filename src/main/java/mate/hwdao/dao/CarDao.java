package mate.hwdao.dao;

import java.util.List;
import mate.hwdao.model.Car;

public interface CarDao extends GenericDao<Car, Long> {

    List<Car> getAllByDriver(Long driverId);
}
