package repositories;

import models.User;
import services.PersistentDataService;

import java.util.ArrayList;

public class UsersRepository extends AbstractRepository<User> {
    private final PersistentDataService persistentDataService;

    public UsersRepository(PersistentDataService persistentDataService) {
        this.persistentDataService = persistentDataService;
    }

    @Override
    protected ArrayList<User> getDataSource() {
        return persistentDataService.users;
    }
}
