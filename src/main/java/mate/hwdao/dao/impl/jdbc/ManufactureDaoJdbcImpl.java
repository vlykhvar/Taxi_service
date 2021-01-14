package mate.hwdao.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.hwdao.dao.ManufacturerDao;
import mate.hwdao.lib.Dao;
import mate.hwdao.model.Manufacturer;
import mate.hwdao.util.ConnectionUtil;

@Dao
public class ManufactureDaoJdbcImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        try {
            String query = "INSERT INTO manufacture (name, country, isexist) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement
                    = ConnectionUtil.getConnection().prepareStatement(query);
            preparedStatement.setString(1, manufacturer.getName());
            preparedStatement.setString(2, manufacturer.getCountry());
            preparedStatement.setBoolean(3, true);
            if (preparedStatement.executeUpdate() > 0) {
                preparedStatement.close();
                query = "SELECT id FROM manufacture WHERE name LIKE ? AND country LIKE ?";
                preparedStatement = ConnectionUtil.getConnection().prepareStatement(query);
                preparedStatement.setString(1, manufacturer.getName());
                preparedStatement.setString(2, manufacturer.getCountry());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    manufacturer.setId(resultSet.getObject("id", Long.class));
                }
                resultSet.close();
            }
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("Connection failed..." + ex);
        }
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        Manufacturer manufacturer = null;
        String query = "SELECT * FROM manufacture WHERE id = ?";
        try {
            PreparedStatement preparedStatement
                    = ConnectionUtil.getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getBoolean(4)) {
                    manufacturer = new Manufacturer();
                    manufacturer.setId(resultSet.getLong(1));
                    manufacturer.setName(resultSet.getString(2));
                    manufacturer.setCountry(resultSet.getString(3));
                }
            }
            resultSet.close();
            preparedStatement.close();
            return Optional.ofNullable(manufacturer);
        } catch (SQLException ex) {
            System.out.println("Connection failed..." + ex);
        }
        return Optional.empty();
    }

    @Override
    public List<Manufacturer> getAll() {
        Manufacturer manufacturer = null;
        List<Manufacturer> manufacturers = new ArrayList<>();
        String query = "SELECT * FROM manufacture";
        try {
            PreparedStatement preparedStatement
                    = ConnectionUtil.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getBoolean(4)) {
                    manufacturer = new Manufacturer();
                    manufacturer.setId(resultSet.getLong(1));
                    manufacturer.setName(resultSet.getString(2));
                    manufacturer.setCountry(resultSet.getString(3));
                    manufacturers.add(manufacturer);
                }
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("Connection failed..." + ex);
        }
        return manufacturers;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        String query = "UPDATE manufacture SET name = ?, country = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement
                    = ConnectionUtil.getConnection().prepareStatement(query);
            preparedStatement.setString(1, manufacturer.getName());
            preparedStatement.setString(2, manufacturer.getCountry());
            preparedStatement.setLong(3, manufacturer.getId());
            if (preparedStatement.executeUpdate() > 0) {
                preparedStatement.close();
                return manufacturer;
            }
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("Connection failed..." + ex);
        }
        throw new RuntimeException("Can't find manufacturer with id " + manufacturer.getId());
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE manufacture SET isexist = false WHERE id = ?";
        try (PreparedStatement preparedStatement
                     = ConnectionUtil.getConnection().prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                preparedStatement.close();
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Connection failed..." + ex);
        }
        return false;
    }
}
