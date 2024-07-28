package models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Appointment implements UniqueResource {
    private UUID id;
    private UUID patientId;
    private UUID workerId;
    private UUID clinicId;
    private LocalDateTime appointmentDate;
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

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public UUID getWorkerId() {
        return workerId;
    }

    public void setWorkerId(UUID workerId) {
        this.workerId = workerId;
    }

    public UUID getClinicId() {
        return clinicId;
    }

    public void setClinicId(UUID clinicId) {
        this.clinicId = clinicId;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Urgency getUrgengy() {
        return urgengy;
    }

    public void setUrgengy(Urgency urgengy) {
        this.urgengy = urgengy;
    }

    public String getExaminationResult() {
        return examinationResult;
    }

    public void setExaminationResult(String examinationResult) {
        this.examinationResult = examinationResult;
    }
}
