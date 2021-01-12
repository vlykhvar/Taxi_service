package mate.hwdao.service;

import java.util.List;
import mate.hwdao.model.Manufacturer;

public interface ManufacturerService {

    public Manufacturer create(Manufacturer manufacturer);
    public Manufacturer get(Long id);
    public List<Manufacturer> getAll();
    public Manufacturer update(Manufacturer manufacturer);
    public boolean delete(Long id);
}
