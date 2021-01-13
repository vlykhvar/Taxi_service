package mate.hwdao.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.hwdao.dao.DriverDao;
import mate.hwdao.db.Storage;
import mate.hwdao.lib.Dao;
import mate.hwdao.model.Driver;

@Dao
public class DriverDaoImpl implements DriverDao {

    @Override
    public Driver create(Driver driver) {
        Storage.addDriver(driver);
        return driver;
    }

    @Override
    public Optional<Driver> get(Long id) {
        return Optional.of(Storage.drivers
                .stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .get());
    }

    @Override
    public List<Driver> getAll() {
        return Storage.drivers;
    }

    @Override
    public Driver update(Driver driver) {
        for (int i = 0; i < Storage.drivers.size(); i++) {
            if (Storage.drivers.get(i).getId().equals(driver.getId())) {
                Storage.drivers.remove(i);
                Storage.drivers.set(i, driver);
            }
        }
        return driver;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.drivers.removeIf(x -> x.getId().equals(id));
    }
}
