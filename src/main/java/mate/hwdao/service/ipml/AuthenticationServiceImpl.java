package mate.hwdao.service.ipml;

import java.util.Optional;
import mate.hwdao.dao.exception.DataProcessingException;
import mate.hwdao.lib.Inject;
import mate.hwdao.lib.Service;
import mate.hwdao.model.Driver;
import mate.hwdao.service.DriverService;
import mate.hwdao.service.security.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverService driverService;

    @Override
    public Driver login(String login, String password) {
        Optional<Driver> driverFromDB = driverService.findByLogin(login);
        if (driverFromDB.isPresent() && driverFromDB.get().getPassword().equals(password)) {
            return driverFromDB.get();
        }
        throw new DataProcessingException("Wrong login or password");
    }
}
