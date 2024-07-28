package services;

import models.Appointment;
import models.Patient;
import models.Physician;
import models.Urgency;
import repositories.AppointmentsRepository;
import repositories.ClinicsRepository;
import repositories.PatientsRepository;
import repositories.PhysiciansRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;

public class AppointmentsService {
    private final PersistentStateManager persistentStateManager;
    private final AppointmentsRepository appointmentsRepository;
    private final PatientsRepository patientsRepository;
    private final PhysiciansRepository physiciansRepository;
    private final ClinicsRepository clinicsRepository;

    public AppointmentsService(
            PersistentStateManager persistentStateManager,
            AppointmentsRepository appointmentsRepository,
            PatientsRepository patientsRepository,
            PhysiciansRepository physiciansRepository,
            ClinicsRepository clinicsRepository
    ) {
        this.persistentStateManager = persistentStateManager;
        this.appointmentsRepository = appointmentsRepository;
        this.patientsRepository = patientsRepository;
        this.physiciansRepository = physiciansRepository;
        this.clinicsRepository = clinicsRepository;
    }

    public void bookPhysician(UUID patientId, UUID physicianId, LocalDateTime appointmentDateTime) throws IOException {
        // https://stackoverflow.com/questions/32437550/whats-the-difference-between-instant-and-localdatetime

        if (patientId == null) throw new IllegalArgumentException();
        if (physicianId == null) throw new IllegalArgumentException();
        if (appointmentDateTime == null) throw new IllegalArgumentException();
        if (appointmentDateTime.isBefore(LocalDateTime.now())) throw new IllegalArgumentException();

        Patient patient = patientsRepository.getById(patientId);
        if (patient == null) throw new IllegalArgumentException();

        Physician physician = physiciansRepository.getById(physicianId);
        if (physician == null) throw new IllegalArgumentException();

        Period diff = Period.between(patient.getBirthDate(), LocalDate.now());
        boolean isPediatricPatient = diff.getYears() <= 13;

        if (isPediatricPatient) {
            // todo
            // check whether the logged in user is a supervisor of patientId
        }

        boolean isPatientPhysician = patient.getPhysicianId() == physician.getId();

        if (!isPatientPhysician) {
            // if it's not, check whether physicianId is the temporary substitute physician
            boolean isSubstitutePhysician = true;

            if (!isSubstitutePhysician) {
                throw new IllegalArgumentException();
            }
        }

        // check physician availability on given date

        // check if there's any clinic available
        var clinicId = UUID.randomUUID(); // use random id for now

        Appointment appointment = new Appointment();
        appointment.setId(UUID.randomUUID());
        appointment.setAppointmentDate(appointmentDateTime);
        appointment.setPatientId(patient.getId());
        appointment.setUrgengy(Urgency.Normal);
        appointment.setWorkerId(physician.getId());
        appointment.setClinicId(clinicId);

        appointmentsRepository.Add(appointment.getId(), appointment);
        persistentStateManager.save();
    }
}