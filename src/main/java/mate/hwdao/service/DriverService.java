package mate.hwdao.service;

import java.util.List;
import mate.hwdao.model.Driver;

public interface DriverService {
    Driver create(Driver driver);

    Driver get(Long id);

    List<Driver> getAll();

    Driver update(Driver driver);

    boolean delete(Long id);
}
