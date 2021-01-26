package mate.hwdao.dao;

import mate.hwdao.model.Driver;
import java.util.Optional;

public interface DriverDao extends GenericDao<Driver, Long> {
    Optional<Driver> findByLogin(String login);
}
