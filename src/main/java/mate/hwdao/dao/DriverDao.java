package mate.hwdao.dao;

import java.util.List;
import java.util.Optional;
import mate.hwdao.model.Driver;

public interface DriverDao extends GenericDao<Driver, Long> {
    Driver create(Driver driver);

    Optional<Driver> get(Long id);

    List<Driver> getAll();

    Driver update(Driver driver);

    boolean delete(Long id);
}
