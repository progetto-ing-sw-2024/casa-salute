package services;

import java.util.UUID;

public class ApplicationStateManager {
    private static final ApplicationStateManager instance = new ApplicationStateManager();

    private UUID loggedInUserId;

    private ApplicationStateManager() {}

    public static ApplicationStateManager getInstance() {
        return instance;
    }

    public UUID getLoggedInUserId() {
        return loggedInUserId;
    }

    public void setLoggedInUserId(UUID loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
    }
}
