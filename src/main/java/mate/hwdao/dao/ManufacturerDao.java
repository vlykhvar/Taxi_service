package mate.hwdao.dao;

import java.util.List;
import java.util.Optional;
import mate.hwdao.model.Manufacturer;

public interface ManufacturerDao {
    public Manufacturer create(Manufacturer manufacturer);
    public Optional<Manufacturer> get(Long id);
    public List<Manufacturer> getAll();
    public Manufacturer update(Manufacturer manufacturer);
    public boolean delete(Long id);
}
