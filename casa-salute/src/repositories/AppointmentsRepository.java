package repositories;

import models.Appointment;
import services.PersistentStateManager;

import java.util.ArrayList;

public class AppointmentsRepository extends AbstractRepository<Appointment> {
    private final PersistentStateManager persistentStateManager;

    public AppointmentsRepository(PersistentStateManager persistentStateManager) {
        this.persistentStateManager = persistentStateManager;
    }

    @Override
    protected ArrayList<Appointment> GetDataSource() {
        return persistentStateManager.appointments;
    }
}
