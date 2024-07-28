package repositories;

import models.Physician;
import services.PersistentDataService;

import java.util.ArrayList;

public class PhysiciansRepository extends AbstractRepository<Physician> {
    private final PersistentDataService persistentDataService;

    public PhysiciansRepository(PersistentDataService persistentDataService) {
        this.persistentDataService = persistentDataService;
    }

    @Override
    protected ArrayList<Physician> getDataSource() {
        return persistentDataService.physicians;
    }
}
