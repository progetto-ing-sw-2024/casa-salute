package repositories;

import models.Patient;
import services.HealthcareDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class PatientsRepository extends AbstractRepository<Patient> {
    private final HealthcareDatabase database;

    public PatientsRepository(HealthcareDatabase database){
        this.database = database;
    }

    @Override
    protected ArrayList<Patient> GetDataSource() {
        return database.patients;
    }
}
