package mate.hwdao.service.ipml;

import java.util.List;
import java.util.Optional;
import mate.hwdao.dao.DriverDao;
import mate.hwdao.dao.exception.DataProcessingException;
import mate.hwdao.lib.Inject;
import mate.hwdao.lib.Service;
import mate.hwdao.model.Driver;
import mate.hwdao.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {
    @Inject
    private DriverDao driverDao;

    @Override
    public Driver create(Driver driver) {
        driverDao.create(driver);
        return driver;
    }

    @Override
    public Driver get(Long id) {
        return driverDao.get(id)
               .orElseThrow(() -> new DataProcessingException("Can't find car with id " + id));
    }

    @Override
    public List<Driver> getAll() {
        return driverDao.getAll();
    }

    @Override
    public Driver update(Driver driver) {
        return driverDao.update(driver);
    }

    @Override
    public boolean delete(Long id) {
        return driverDao.delete(id);
    }

    @Override
    public Optional<Driver> findByLogin(String login) {
        return driverDao.findByLogin(login);
    }
}
