package repositories;

import models.Clinic;
import services.PersistentDataService;

import java.util.ArrayList;

public class ClinicsRepository extends AbstractRepository<Clinic> {
    private final PersistentDataService persistentDataService;

    public ClinicsRepository(PersistentDataService persistentDataService) {
        this.persistentDataService = persistentDataService;
    }

    @Override
    protected ArrayList<Clinic> getDataSource() {
        return persistentDataService.clinics;
    }
}
