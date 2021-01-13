package mate.hwdao.service;

import java.util.List;
import mate.hwdao.dao.DriverDao;
import mate.hwdao.lib.Inject;
import mate.hwdao.lib.Service;
import mate.hwdao.model.Driver;

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
               .orElseThrow(() -> new RuntimeException("Can't find car with id " + id));
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
}
