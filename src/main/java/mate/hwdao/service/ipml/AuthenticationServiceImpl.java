package mate.hwdao.service.ipml;

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
        Driver driverFromDB = driverService.findByLogin(login)
                .orElseThrow(() -> new DataProcessingException("Wrong login or password"));
        if (driverFromDB.getPassword().equals(password)) {
            return driverFromDB;
        }
        throw new DataProcessingException("Wrong login or password");
    }
}
