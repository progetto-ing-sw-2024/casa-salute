package models;

import java.util.Date;
import java.util.UUID;

public class Appointment {
    private UUID patientId;
    private UUID workerId;
    private UUID clinicId;
    private Date appointmentDate;
    private Urgency urgengy;
    private String examinationResult;
}
