package models;

import java.util.Date;
import java.util.UUID;

public class Appointment implements UniqueResource {
    private UUID id;
    private UUID patientId;
    private UUID workerId;
    private UUID clinicId;
    private Date appointmentDate;
    private Urgency urgengy;
    private String examinationResult;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }
}
