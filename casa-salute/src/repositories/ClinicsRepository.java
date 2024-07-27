package repositories;

import models.Clinic;
import services.HealthcareDatabase;

import java.util.ArrayList;

public class ClinicsRepository extends AbstractRepository<Clinic> {
    private final HealthcareDatabase database;

    public ClinicsRepository(HealthcareDatabase database){
        this.database = database;
    }

    @Override
    protected ArrayList<Clinic> GetDataSource() {
        return database.clinics;
    }
}
