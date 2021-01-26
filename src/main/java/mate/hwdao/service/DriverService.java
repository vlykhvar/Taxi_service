package mate.hwdao.service;

import java.util.Optional;
import mate.hwdao.model.Driver;

public interface DriverService extends Service<Driver, Long> {
    Optional<Driver> findByLogin(String login);
}
