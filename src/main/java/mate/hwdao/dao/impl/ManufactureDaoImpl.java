package mate.hwdao.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.hwdao.dao.ManufacturerDao;
import mate.hwdao.db.Storage;
import mate.hwdao.model.Manufacturer;

public class ManufactureDaoImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        Storage.addManufacturer(manufacturer);
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        return Storage.manufacturers.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.manufacturers;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        for (int i = 0; i < Storage.manufacturers.size(); i++) {
            if (Storage.manufacturers.get(i).getId().equals(manufacturer.getId())) {
                Storage.manufacturers.set(i, manufacturer);
            }
        }
        return manufacturer;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.manufacturers.removeIf(x -> x.getId().equals(id));
    }
}
