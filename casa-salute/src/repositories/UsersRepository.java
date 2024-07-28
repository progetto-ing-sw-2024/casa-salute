package repositories;

import models.User;
import services.PersistentStateManager;

import java.util.ArrayList;

public class UsersRepository extends AbstractRepository<User> {
    private final PersistentStateManager persistentStateManager;

    public UsersRepository(PersistentStateManager persistentStateManager) {
        this.persistentStateManager = persistentStateManager;
    }

    @Override
    protected ArrayList<User> GetDataSource() {
        return persistentStateManager.users;
    }
}
