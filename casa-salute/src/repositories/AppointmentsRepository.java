package repositories;

import models.Appointment;
import services.HealthcareDatabase;

import java.util.ArrayList;

public class AppointmentsRepository extends AbstractRepository<Appointment> {
    private final HealthcareDatabase database;

    public AppointmentsRepository(HealthcareDatabase database){
        this.database = database;
    }

    @Override
    protected ArrayList<Appointment> GetDataSource() {
        return database.appointments;
    }
}
