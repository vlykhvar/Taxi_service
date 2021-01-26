package mate.hwdao.service.security;

import mate.hwdao.model.Driver;

public interface AuthenticationService {
    Driver login(String login, String password);
}
