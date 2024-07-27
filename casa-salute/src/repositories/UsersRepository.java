package repositories;

import models.User;
import services.HealthcareDatabase;

import java.util.ArrayList;

public class UsersRepository extends AbstractRepository<User> {
    private final HealthcareDatabase database;

    public UsersRepository(HealthcareDatabase database){
        this.database = database;
    }

    @Override
    protected ArrayList<User> GetDataSource() {
        return database.users;
    }
}
