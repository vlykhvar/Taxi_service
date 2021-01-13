package mate.hwdao.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.hwdao.dao.ManufacturerDao;
import mate.hwdao.db.Storage;
import mate.hwdao.lib.Dao;
import mate.hwdao.model.Manufacturer;

@Dao
public class ManufactureDaoImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        Storage.addManufacturer(manufacturer);
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        return Optional.ofNullable(Storage.manufacturers.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst().get());
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.manufacturers;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        for (int i = 0; i < Storage.manufacturers.size(); i++) {
            if (Storage.manufacturers.get(i).getId().equals(manufacturer.getId())) {
                return Storage.manufacturers.set(i, manufacturer);
            }
        }
        throw new RuntimeException(manufacturer.getName()
                + " is not added to our database. "
                + "Contact us to +380000000 in case of the question");
    }

    @Override
    public boolean delete(Long id) {
        return Storage.manufacturers.removeIf(x -> x.getId().equals(id));
    }
}
