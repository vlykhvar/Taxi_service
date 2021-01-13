package mate.hwdao.service;

import java.util.List;
import mate.hwdao.model.Manufacturer;

public interface ManufacturerService {
    Manufacturer create(Manufacturer manufacturer);

    Manufacturer get(Long id);

    List<Manufacturer> getAll();

    Manufacturer update(Manufacturer manufacturer);

    boolean delete(Long id);
}
