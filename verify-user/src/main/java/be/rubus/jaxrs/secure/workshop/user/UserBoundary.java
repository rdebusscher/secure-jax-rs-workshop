package be.rubus.jaxrs.secure.workshop.user;

import be.rubus.jaxrs.secure.workshop.UsernamePassword;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 */

@ApplicationScoped
public class UserBoundary {

    public User findUser(UsernamePassword usernamePassword) {
        User result = null;

        if (usernamePassword.getUsername().equals(usernamePassword.getPassword())) {
            result = new User();
            result.setId(123L);
            result.setName(usernamePassword.getUsername());
            result.setUserName(usernamePassword.getPassword());

        }
        return result;
    }

}
