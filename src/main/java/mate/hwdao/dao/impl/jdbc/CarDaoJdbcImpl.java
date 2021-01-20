package mate.hwdao.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.hwdao.dao.GenericDao;
import mate.hwdao.dao.exception.DataProcessingException;
import mate.hwdao.lib.Dao;
import mate.hwdao.model.Car;
import mate.hwdao.model.Driver;
import mate.hwdao.model.Manufacturer;
import mate.hwdao.util.ConnectionUtil;

@Dao
public class CarDaoJdbcImpl implements GenericDao<Car, Long> {
    @Override
    public Car create(Car car) {
        String query = "INSERT INTO cars (manufacturer_id, model) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement
                         = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, car.getManufacturer().getId());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                car.setId(resultSet.getObject("GENERATED_KEY", Long.class));
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't save " + car
                    + " failed", ex);
        }
        return car;
    }

    @Override
    public Optional<Car> get(Long id) {
        String query = "SELECT * FROM cars "
                + "INNER JOIN manufactures ON cars.manufacturer_id = manufactures.id "
                + "WHERE cars.id = ? and cars.deleted = false;";
        Car car = null;
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement
                         = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                car = makeCar(resultSet);
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't get car with " + id, ex);
        }
        if (car != null) {
            car.setDrivers(getDriversByCar(car));
        }
        return Optional.ofNullable(car);

    }

    @Override
    public List<Car> getAll() {
        String query = "SELECT * FROM cars INNER JOIN manufactures "
                + "ON cars.manufacturer_id = manufactures.id "
                + "WHERE cars.deleted = false";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement
                         = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cars.add(makeCar(resultSet));
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't get list of cars ", ex);
        }
        for (Car car : cars) {
            car.setDrivers(getDriversByCar(car));
        }
        return cars;
    }

    @Override
    public Car update(Car car) {
        String updateCar = "UPDATE cars SET manufacturer_id = ?, model = ? "
                + "WHERE id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement updateCarStatement
                         = connection.prepareStatement(updateCar)) {
            updateCarStatement.setLong(1, car.getManufacturer().getId());
            updateCarStatement.setString(2, car.getModel());
            updateCarStatement.setLong(3, car.getId());
            updateCarStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't update " + car
                    + " failed", ex);
        }
        deleteOldDrivers(car);
        setNewDrivers(car);
        return car;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE cars SET deleted = true WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement
                         = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DataProcessingException("cars with " + id
                    + "was not deleted", ex);
        }
    }

    public List<Car> getAllByDriver(Long driverId) {
        String query = "SELECT cars.id, model, manufactures.id, "
                + "manufactures.name, manufactures.country "
                + "FROM cars JOIN manufactures ON cars.manufacturer_id = manufactures.id "
                + "JOIN cars_drivers ON cars.id = cars_drivers.car_id "
                + "JOIN drivers ON cars_drivers.driver_id = drivers.id "
                + "WHERE drivers.deleted = false AND cars_drivers.driver_id = ? "
                + "AND cars.deleted = false;";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement
                         = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, driverId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cars.add(makeCar(resultSet));
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't get list of car by driver id: "
                    + driverId, ex);
        }
        for (Car car : cars) {
            car.setDrivers(getDriversByCar(car));
        }
        return cars;
    }

    private void deleteOldDrivers(Car car) {
        String query = "DELETE FROM cars_drivers WHERE car_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement deletedOldDriversStatement
                         = connection.prepareStatement(query)) {
            deletedOldDriversStatement.setLong(1, car.getId());
            deletedOldDriversStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't deleted drivers from  "
                    + car, ex);
        }
    }

    private void setNewDrivers(Car car) {
        String query = "INSERT INTO cars_drivers(driver_id, car_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement setNewDriversStatement = connection.prepareStatement(query)) {
            for (Driver driver : car.getDrivers()) {
                setNewDriversStatement.setLong(1, driver.getId());
                setNewDriversStatement.setLong(2, car.getId());
                setNewDriversStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't add new driver to "
                    + car, ex);
        }
    }

    private Car makeCar(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getObject("id", Long.class));
        car.setModel(resultSet.getObject("model", String.class));
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(resultSet.getObject("manufactures.id", Long.class));
        manufacturer.setName(resultSet.getObject("name", String.class));
        manufacturer.setCountry(resultSet.getObject("country", String.class));
        car.setManufacturer(manufacturer);
        return car;
    }

    private List<Driver> getDriversByCar(Car car) {
        String query = "SELECT * FROM cars_drivers JOIN drivers "
                + "ON cars_drivers.driver_id = drivers.id WHERE cars_drivers.car_id = ? "
                + "AND drivers.deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement
                         = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, car.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Driver> drivers = new ArrayList<>();
            while (resultSet.next()) {
                Driver driver = new Driver();
                driver.setId(resultSet.getObject("driver_id", Long.class));
                driver.setName(resultSet.getObject("name", String.class));
                driver.setLicenseNumber(resultSet.getObject("license_number", String.class));
                drivers.add(driver);
            }
            return drivers;
        } catch (SQLException ex) {
            throw new DataProcessingException("Can not get list of drivers for car with id :"
                    + car.getId(), ex);
        }
    }
}
