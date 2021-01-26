package mate.hwdao.dao;

import java.util.Optional;
import mate.hwdao.model.Driver;

public interface DriverDao extends GenericDao<Driver, Long> {
    Optional<Driver> findByLogin(String login);
}
