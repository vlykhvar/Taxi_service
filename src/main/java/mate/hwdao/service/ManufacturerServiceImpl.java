package mate.hwdao.service;

import java.util.List;
import mate.hwdao.dao.ManufacturerDao;
import mate.hwdao.lib.Inject;
import mate.hwdao.lib.Service;
import mate.hwdao.model.Manufacturer;

@Service
public class ManufacturerServiceImpl implements ManufacturerService{
    @Inject
    private ManufacturerDao manufacturerDao;
    private long countId = 0;

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        manufacturer.setId(++countId);
        manufacturerDao.create(manufacturer);
        return manufacturer;
    }

    @Override
    public Manufacturer get(Long id) {
       return manufacturerDao.get(id)
               .orElseThrow(() -> new RuntimeException("Can't find " + id));
    }

    @Override
    public List<Manufacturer> getAll() {
       return manufacturerDao.getAll();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        return manufacturerDao.update(manufacturer);
    }

    @Override
    public boolean delete(Long id) {
        return manufacturerDao.delete(id);
    }

}
