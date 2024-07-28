package repositories;

import models.Clinic;
import services.PersistentStateManager;

import java.util.ArrayList;

public class ClinicsRepository extends AbstractRepository<Clinic> {
    private final PersistentStateManager persistentStateManager;

    public ClinicsRepository(PersistentStateManager persistentStateManager) {
        this.persistentStateManager = persistentStateManager;
    }

    @Override
    protected ArrayList<Clinic> GetDataSource() {
        return persistentStateManager.clinics;
    }
}
