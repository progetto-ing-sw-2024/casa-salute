package repositories;

import models.HealthcareWorker;
import services.PersistentDataService;

import java.util.ArrayList;
import java.util.List;

public class HealthcareWorkerRepository extends AbstractRepository<HealthcareWorker> {
    private final PersistentDataService persistentDataService;

    public HealthcareWorkerRepository(PersistentDataService persistentDataService) {
        this.persistentDataService = persistentDataService;
    }

    @Override
    protected List<HealthcareWorker> getDataSource() {
        return persistentDataService.healthcareWorkers;
    }
}
