package mate.hwdao.db;

import java.util.ArrayList;
import java.util.List;
import mate.hwdao.model.Manufacturer;

public class Storage {
    public static final List<Manufacturer> listManufacturer = new ArrayList<>();
    private static Long manufacturerId = 0L;

    public static void addManufacturer(Manufacturer manufacturer) {
        manufacturer.setId(++manufacturerId);
        listManufacturer.add(manufacturer);
    }
}
