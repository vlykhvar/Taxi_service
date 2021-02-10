package mate.hwdao.service.security;

import mate.hwdao.dao.exception.AuthenticationException;
import mate.hwdao.model.Driver;

public interface AuthenticationService {
    Driver login(String login, String password) throws AuthenticationException;
}
