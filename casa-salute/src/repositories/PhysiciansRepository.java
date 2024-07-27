package repositories;

import models.Physician;
import services.HealthcareDatabase;

import java.util.ArrayList;

public class PhysiciansRepository extends AbstractRepository<Physician> {
    private final HealthcareDatabase database;

    public PhysiciansRepository(HealthcareDatabase database){
        this.database = database;
    }

    @Override
    protected ArrayList<Physician> GetDataSource() {
        return database.physicians;
    }
}
