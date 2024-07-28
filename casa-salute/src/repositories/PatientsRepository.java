package repositories;

import models.Patient;
import services.PersistentStateManager;

import java.util.ArrayList;

public class PatientsRepository extends AbstractRepository<Patient> {
    private final PersistentStateManager persistentStateManager;

    public PatientsRepository(PersistentStateManager persistentStateManager) {
        this.persistentStateManager = persistentStateManager;
    }

    @Override
    protected ArrayList<Patient> GetDataSource() {
        return persistentStateManager.patients;
    }
}
