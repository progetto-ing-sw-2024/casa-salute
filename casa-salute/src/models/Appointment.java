package models;

import java.util.Date;

public class Appointment {
    private int patientId;
    private int physicianId;
    private int clinicId;
    private Date appointmentDate;
    private Urgency urgengy;
    private String examinationResult;
}
