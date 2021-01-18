package mate.hwdao.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.hwdao.dao.CarDao;
import mate.hwdao.dao.exception.DataProcessingException;
import mate.hwdao.lib.Dao;
import mate.hwdao.model.Car;
import mate.hwdao.model.Driver;
import mate.hwdao.model.Manufacturer;
import mate.hwdao.util.ConnectionUtil;

@Dao
public class CarDaoJdbcImpl implements CarDao {
    @Override
    public Car create(Car car) {
        String query = "INSERT INTO cars (manufacturer_id, model) VALUES (?, ?)";
        try (PreparedStatement preparedStatement
                     = ConnectionUtil.getConnection()
                .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, car.getManufacturer().getId());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
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
                + "WHERE cars.id = ? and deleted = false;";
        try (PreparedStatement preparedStatement
                     = ConnectionUtil.getConnection()
                .prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Car car = null;
            while (resultSet.next()) {
                car = makeCar(resultSet);
            }
            return Optional.ofNullable(car);
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't get car with " + id, ex);
        }

    }

    @Override
    public List<Car> getAll() {
        String query = "SELECT * FROM cars INNER JOIN manufactures"
                + " ON cars.manufacturer_id = manufactures.id WHERE deleted = false";
        try (PreparedStatement preparedStatement
                     = ConnectionUtil.getConnection()
                .prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                cars.add(makeCar(resultSet));
            }
            return cars;
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't get list of cars ", ex);
        }
    }

    @Override
    public Car update(Car car) {
        String updateCar = "UPDATE cars SET manufacturer_id = ?, model = ? "
                + "WHERE id = ? AND deleted = false";
        String deletedOldDrivers = "DELETE FROM cars_drivers WHERE car_id = ?";
        String setNewDrivers = "INSERT INTO cars_drivers(driver_id, car_id) VALUES (?, ?)";
        try (PreparedStatement updateCarStatement
                     = ConnectionUtil.getConnection()
                .prepareStatement(updateCar)) {
            updateCarStatement.setLong(1, car.getManufacturer().getId());
            updateCarStatement.setString(2, car.getModel());
            updateCarStatement.setLong(3, car.getId());
            updateCarStatement.executeUpdate();
            delateOldDrivers(deletedOldDrivers, car);
            setNewDriversStatement(setNewDrivers,car);
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't save " + car
                    + " failed", ex);
        }
        return car;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE cars SET deleted = true WHERE id = ?";
        try (PreparedStatement preparedStatement
                     = ConnectionUtil.getConnection()
                .prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DataProcessingException("cars with " + id
                    + "was not deleted because of", ex);
        }
    }

    public List<Car> getAllByDriver(Long driverId) {
        String query = "Select cars.id, manufacturer_id, model, deleted, "
                + "manufactures.id, manufactures.name, manufactures.country "
                + "From cars  INNER JOIN manufactures ON cars.manufacturer_id = manufactures.id "
                + "JOIN cars_drivers WHERE cars.id = cars_drivers.car_id "
                + "AND  cars_drivers.driver_id = ? AND deleted = false";
        try (PreparedStatement preparedStatement
                     = ConnectionUtil.getConnection()
                .prepareStatement(query)) {
            preparedStatement.setLong(1, driverId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                cars.add(makeCar(resultSet));
            }
            return cars;
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't get list of car by driver id: "
                    + driverId, ex);
        }
    }

    private void delateOldDrivers(String query, Car car) {
        try (PreparedStatement deletedOldDriversStatement = ConnectionUtil.getConnection()
                .prepareStatement(query)) {
            deletedOldDriversStatement.setLong(1, car.getId());
            deletedOldDriversStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't save " + car
                    + " failed", ex);
        }
    }

    private void setNewDriversStatement(String query, Car car) {
        try (PreparedStatement setNewDriversStatement = ConnectionUtil.getConnection()
                .prepareStatement(query)) {
            for (Driver driver : car.getDrivers()) {
                setNewDriversStatement.setLong(1, driver.getId());
                setNewDriversStatement.setLong(2, car.getId());
                setNewDriversStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't save " + car
                    + " failed", ex);
        }
    }

    private Car makeCar(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getObject(1, Long.class));
        car.setModel(resultSet.getObject(3, String.class));
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(resultSet.getObject(5, Long.class));
        manufacturer.setName(resultSet.getObject(6, String.class));
        manufacturer.setCountry(resultSet.getObject(7, String.class));
        car.setManufacturer(manufacturer);
        car.getDrivers().addAll(getDriver(car));
        return car;
    }

    private List<Driver> getDriver(Car car) {
        String query = "SELECT * FROM cars_drivers JOIN drivers "
                + "WHERE cars_drivers.driver_id = drivers.id AND cars_drivers.car_id = ?";
        try (PreparedStatement preparedStatement
                     = ConnectionUtil.getConnection()
                .prepareStatement(query)) {
            preparedStatement.setLong(1, car.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Driver> drivers = new ArrayList<>();
            while (resultSet.next()) {
                Driver driver = new Driver();
                driver.setId(resultSet.getObject(3, Long.class));
                driver.setName(resultSet.getObject(4, String.class));
                driver.setLicenseNumber(resultSet.getObject(5, String.class));
                drivers.add(driver);
            }
            return drivers;
        } catch (SQLException ex) {
            throw new DataProcessingException("Can not get list of drivers for car with id :"
                    + car.getId(), ex);
        }
    }
}
