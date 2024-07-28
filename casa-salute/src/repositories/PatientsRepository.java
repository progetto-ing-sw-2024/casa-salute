package repositories;

import models.Patient;
import services.PersistentDataService;

import java.util.ArrayList;

public class PatientsRepository extends AbstractRepository<Patient> {
    private final PersistentDataService persistentDataService;

    public PatientsRepository(PersistentDataService persistentDataService) {
        this.persistentDataService = persistentDataService;
    }

    @Override
    protected ArrayList<Patient> getDataSource() {
        return persistentDataService.patients;
    }
}
