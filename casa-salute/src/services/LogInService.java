package services;

import models.User;
import repositories.UsersRepository;

import java.util.UUID;

public class LogInService {
    private final ApplicationStateManager applicationStateManager;
    private final UsersRepository usersRepository;

    public LogInService(ApplicationStateManager applicationStateManager,
                        UsersRepository usersRepository) {
        this.applicationStateManager = applicationStateManager;
        this.usersRepository = usersRepository;
    }

    public boolean login(String username, String password) {
        if (isLoggedIn()) {
            throw new IllegalArgumentException();
        }

        User user = usersRepository.get(u -> u.getUsername().equals(username) && u.getPassword().equals(password));

        if (user == null)
            return false;

        applicationStateManager.setLoggedInUserId(user.getId());
        return true;
    }

    public void logout() {
        applicationStateManager.setLoggedInUserId(null);
    }

    public boolean isLoggedIn() {
        UUID loggedInUserId = applicationStateManager.getLoggedInUserId();
        return loggedInUserId != null;
    }

    public boolean isAdminLoggedIn() {
        UUID loggedInUserId = applicationStateManager.getLoggedInUserId();
        return loggedInUserId != null && loggedInUserId.equals("00000000-0000-0000-0000-000000000000");
    }
}
