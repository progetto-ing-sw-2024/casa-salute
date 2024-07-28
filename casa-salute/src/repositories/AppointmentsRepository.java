package repositories;

import models.Appointment;
import services.PersistentDataService;

import java.util.ArrayList;

public class AppointmentsRepository extends AbstractRepository<Appointment> {
    private final PersistentDataService persistentDataService;

    public AppointmentsRepository(PersistentDataService persistentDataService) {
        this.persistentDataService = persistentDataService;
    }

    @Override
    protected ArrayList<Appointment> getDataSource() {
        return persistentDataService.appointments;
    }
}
