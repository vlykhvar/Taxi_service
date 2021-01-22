package mate.hwdao.service;

import mate.hwdao.model.Driver;

public interface AuthenticationService {
    Driver login(String login, String password);
}
