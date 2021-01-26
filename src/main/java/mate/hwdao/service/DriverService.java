package mate.hwdao.service;

import mate.hwdao.model.Driver;

import java.util.Optional;

public interface DriverService extends Service<Driver, Long> {
    Optional<Driver> findByLogin(String login);
}
