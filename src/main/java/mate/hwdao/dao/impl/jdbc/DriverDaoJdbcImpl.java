package mate.hwdao.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.hwdao.dao.DriverDao;
import mate.hwdao.dao.exception.DataProcessingException;
import mate.hwdao.lib.Dao;
import mate.hwdao.model.Driver;
import mate.hwdao.util.ConnectionUtil;

@Dao
public class DriverDaoJdbcImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        String query = "INSERT INTO drivers (name, license_number) VALUES (?, ?)";
        try (PreparedStatement preparedStatement
                     = ConnectionUtil.getConnection()
                .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, driver.getName());
            preparedStatement.setString(2, driver.getLicenseNumber());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                driver.setId(resultSet.getObject(1, Long.class));
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't saving driver " + driver
                    + " failed", ex);
        }
        return driver;
    }

    @Override
    public Optional<Driver> get(Long id) {
        Driver driver = null;
        String query = "SELECT * FROM drivers WHERE id = ? AND deleted = false";
        try (PreparedStatement preparedStatement
                     = ConnectionUtil.getConnection().prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                driver = getDriver(resultSet);
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't get driver with " + id, ex);
        }
        return Optional.ofNullable(driver);
    }

    @Override
    public List<Driver> getAll() {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM drivers WHERE deleted = false";
        try (PreparedStatement preparedStatement
                     = ConnectionUtil.getConnection().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                drivers.add(getDriver(resultSet));
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't get list of manufacturers because of ", ex);
        }
        return drivers;
    }

    @Override
    public Driver update(Driver driver) {
        String query = "UPDATE drivers SET name = ?, license_number = ?"
                + " WHERE id = ? AND deleted = false";
        try (PreparedStatement preparedStatement
                     = ConnectionUtil.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, driver.getName());
            preparedStatement.setString(2, driver.getLicenseNumber());
            preparedStatement.setLong(3, driver.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return driver;
            }
        } catch (SQLException ex) {
            throw new DataProcessingException(driver.toString()
                    + "was not updated, because of ", ex);
        }
        throw new RuntimeException("Can't find manufacturer with id " + driver.getId());
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE drivers SET deleted = true WHERE id = ?";
        try (PreparedStatement preparedStatement
                     = ConnectionUtil.getConnection()
                .prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DataProcessingException("drivers with " + id
                    + "was not deleted because of", ex);
        }
    }

    private Driver getDriver(ResultSet resultSet) throws SQLException {
        Driver driver = new Driver();
        driver.setId(resultSet.getObject(1, Long.class));
        driver.setName(resultSet.getObject(2, String.class));
        driver.setLicenseNumber(resultSet.getObject(3, String.class));
        return driver;
    }
}
