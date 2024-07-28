package repositories;

import models.Clinic;
import services.PersistentStateManager;

import java.util.ArrayList;
import java.util.function.Predicate;

public class ClinicsRepository extends AbstractRepository<Clinic> {
    private final PersistentStateManager persistentStateManager;

    public ClinicsRepository(PersistentStateManager persistentStateManager) {
        this.persistentStateManager = persistentStateManager;
    }

    @Override
    protected ArrayList<Clinic> getDataSource() {
        return persistentStateManager.clinics;
    }
}
