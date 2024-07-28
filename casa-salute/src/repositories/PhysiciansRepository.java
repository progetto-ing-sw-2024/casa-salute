package repositories;

import models.Physician;
import services.PersistentStateManager;

import java.util.ArrayList;

public class PhysiciansRepository extends AbstractRepository<Physician> {
    private final PersistentStateManager persistentStateManager;

    public PhysiciansRepository(PersistentStateManager persistentStateManager){
        this.persistentStateManager = persistentStateManager;
    }

    @Override
    protected ArrayList<Physician> GetDataSource() {
        return persistentStateManager.physicians;
    }
}
