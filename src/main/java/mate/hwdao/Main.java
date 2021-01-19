package mate.hwdao;

import mate.hwdao.lib.Injector;
import mate.hwdao.model.Car;
import mate.hwdao.model.Driver;
import mate.hwdao.model.Manufacturer;
import mate.hwdao.service.CarService;
import mate.hwdao.service.DriverService;
import mate.hwdao.service.ManufacturerService;

public class Main {

    private static final Injector injector = Injector.getInstance(Main.class.getPackageName());

    public static void main(String[] args) {
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
        Driver driverSofia = new Driver("Sofia", "0001");
        Driver driverBogdan = new Driver("Bogdan", "0002");
        Driver driverRoman = new Driver("Roman", "0003");
        Driver driverKsenia = new Driver("Ksenia", "0003");
        Driver driverDmytro = new Driver("Dmytro", "0005");
        Driver driverI = new Driver("Volodymyr", "0006");
        DriverService driverService = (DriverService) injector.getInstance(DriverService.class);
        driverService.create(driverSofia);
        driverService.create(driverBogdan);
        driverService.create(driverRoman);
        driverService.create(driverKsenia);
        driverService.create(driverDmytro);
        driverService.create(driverI);
        System.out.println("We are welcome our new employee "
                + driverDmytro);
        System.out.println("Here is a new list of employees working for our company: "
                + driverService.getAll().toString());
        System.out.println("The employee of the month is "
                + driverService.get(driverSofia.getId()).toString());
        driverKsenia.setLicenseNumber("0004");
        System.out.println(driverKsenia.getName() + ", sorry! "
                + "We've made mistake in your data, here is update for your, check it please "
                + driverService.update(driverKsenia).toString());
        if (driverService.delete(driverI.getId())) {
            System.out.println(driverI.getName()
                    + " left us after the Friday's exam,"
                    + " it was necessary to prepare");
        }
        Car carVida = new Car("Vida", manufacturerZaz);
        Car carCivic = new Car("Civic", manufacturerHonda);
        Car carFocus = new Car("Focus", manufacturerFord);
        Car carC5 = new Car("C5", manufacturerCitroen);
        CarService carService = (CarService) injector.getInstance(CarService.class);
        carService.create(carVida);
        carService.create(carCivic);
        carService.create(carFocus);
        carService.create(carC5);
        carService.addDriverToCar(driverDmytro, carVida);
        carService.addDriverToCar(driverRoman, carVida);
        carService.removeDriverFromCar(driverSofia, carVida);
        System.out.println(carService.getAll().toString());
        System.out.println(carService.get(carVida.getId()).toString());
        carService.addDriverToCar(driverSofia, carCivic);
        carService.addDriverToCar(driverBogdan, carCivic);
        carService.addDriverToCar(driverRoman, carCivic);
        carService.addDriverToCar(driverKsenia, carCivic);
        carService.addDriverToCar(driverDmytro, carCivic);
        carService.addDriverToCar(driverBogdan, carFocus);
        carService.addDriverToCar(driverBogdan, carC5);
        carService.addDriverToCar(driverBogdan, carVida);
        System.out.println("Driver Bogdan ride on "
                + carService.getAllByDriver(driverBogdan.getId()).toString());
        System.out.println("But Bogdan diced not to work on "
                + carVida.toString());
        carService.removeDriverFromCar(driverBogdan, carVida);
        System.out.println("His new list of work car is "
                + carService.getAllByDriver(driverBogdan.getId()).toString());
    }
}
