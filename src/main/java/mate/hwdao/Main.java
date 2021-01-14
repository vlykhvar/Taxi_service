package mate.hwdao;

import mate.hwdao.lib.Injector;
import mate.hwdao.model.Manufacturer;
import mate.hwdao.service.ManufacturerService;
import mate.hwdao.util.ConnectionUtil;

public class Main {

    private static final Injector injector = Injector.getInstance(Main.class.getPackageName());

    public static void main(String[] args) {
        ConnectionUtil.setBase();
        System.out.println("All the characters and events depicted are fictitious. "
                + "Any resemblance to a person living or dead is purely coincidental");
        ManufacturerService manufacturerService =
                (ManufacturerService) injector.getInstance(ManufacturerService.class);
        Manufacturer manufacturerHonda = new Manufacturer("Honda", "Jupan");
        Manufacturer manufacturerZaz = new Manufacturer("ZAZ", "Ukraine");
        Manufacturer manufacturerCitroen = new Manufacturer("Citroën", "France");
        Manufacturer manufacturerFord = new Manufacturer("Ford", "USA");
        manufacturerService.create(manufacturerHonda);
        manufacturerService.create(manufacturerZaz);
        manufacturerService.create(manufacturerFord);
        manufacturerService.create(manufacturerCitroen);
        System.out.println("How to work manufacturer service.create: "
                + manufacturerZaz.toString());
        System.out.println("How to work manufacturer service.getAll: "
                + manufacturerService.getAll().toString());
        System.out.println("How to work manufacturer service.getId: "
                + manufacturerService.get(manufacturerHonda.getId()).toString());
        manufacturerHonda.setCountry("Japan");
        System.out.println("How to work service.update: "
                + (manufacturerService.update(manufacturerHonda)).toString());
        manufacturerService.delete(manufacturerHonda.getId());
        System.out.println("How to work service.getAll after deleted: "
                + manufacturerService.getAll().toString());
        ConnectionUtil.dropTable();
       
    }
}
