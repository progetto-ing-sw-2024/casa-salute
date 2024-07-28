package services;

import models.*;
import repositories.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.UUID;

public class AppointmentsService {
    private final ApplicationStateManager applicationStateManager;
    private final PersistentDataService persistentStateManager;
    private final AppointmentsRepository appointmentsRepository;
    private final PatientsRepository patientsRepository;
    private final PhysiciansRepository physiciansRepository;
    private final ClinicsRepository clinicsRepository;
    private final UsersRepository usersRepository;

    public AppointmentsService(
            ApplicationStateManager applicationStateManager,
            PersistentDataService persistentDataService,
            AppointmentsRepository appointmentsRepository,
            PatientsRepository patientsRepository,
            PhysiciansRepository physiciansRepository,
            ClinicsRepository clinicsRepository,
            UsersRepository usersRepository
    ) {
        this.applicationStateManager = applicationStateManager;
        this.persistentStateManager = persistentDataService;
        this.appointmentsRepository = appointmentsRepository;
        this.patientsRepository = patientsRepository;
        this.physiciansRepository = physiciansRepository;
        this.clinicsRepository = clinicsRepository;
        this.usersRepository = usersRepository;
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

        ClinicType clinicType = isPediatricPatient(patient) ? ClinicType.Pediatrician : ClinicType.Physician;
        Clinic clinic = findAnyAvailableClinic(clinicType, appointmentDateTime);
        if (clinic == null) throw new IllegalArgumentException();

        UUID loggedInUserId = applicationStateManager.getLoggedInUserId();
        if (loggedInUserId == null) throw new IllegalArgumentException();

        User loggedInUser = usersRepository.getById(loggedInUserId);
        if (loggedInUser == null) throw new IllegalArgumentException();

        if (!canUserAccessPatient(loggedInUser, patient)) {
            throw new IllegalArgumentException();
        }

        boolean isPhysicianAssignedToPatient = patient.getPhysicianId() == physician.getId();

        if (!isPhysicianAssignedToPatient) {
            // if it's not, check whether physicianId is the temporary substitute physician
            boolean isSubstitutePhysician = true;

            if (!isSubstitutePhysician) {
                throw new IllegalArgumentException();
            }
        }

        // check physician availability on given date

        // check if there's any clinic available


        Appointment appointment = createAppointment(patient, physician, clinic, appointmentDateTime);

        appointmentsRepository.add(appointment);
        persistentStateManager.save();
    }

    private boolean canUserAccessPatient(User user, Patient patient) {
        // A user is a supervisor of himself
        if (patient.getUserId() == user.getId())
            return true;

        if (isPediatricPatient(patient)) {
            Patient supervisor = patientsRepository.get(p -> p.getUserId() == user.getId());

            if (supervisor == null)
                return false;

            boolean isSupervisor = patient.getSupervisorPatientsId().contains(supervisor.getId());
            return isSupervisor;
        }

        return false;
    }

    private boolean isPediatricPatient(Patient patient) {
        int ageLimit = 14;
        Period diff = Period.between(patient.getBirthDate(), LocalDate.now());
        boolean result = diff.getYears() < ageLimit;
        return result;
    }

    private Appointment createAppointment(Patient patient, Physician physician, Clinic clinic, LocalDateTime appointmentDateTime) {
        var appointment = new Appointment();
        appointment.setId(UUID.randomUUID());
        appointment.setAppointmentDate(appointmentDateTime);
        appointment.setPatientId(patient.getId());
        appointment.setUrgengy(Urgency.Normal);
        appointment.setWorkerId(physician.getId());
        appointment.setClinicId(clinic.getId());

        return appointment;
    }

    private Clinic findAnyAvailableClinic(ClinicType clinicType, LocalDateTime appointmentDateTime) {
        List<Clinic> clinics = clinicsRepository.getAll(clinic -> clinic.getClinicType().equals(clinicType));
        List<Clinic> availableClinics = clinics.stream().filter(clinic -> isClinicAvailable(clinic, appointmentDateTime)).toList();

        if (availableClinics.isEmpty()) {
            return null;
        }

        Clinic clinic = availableClinics.getFirst();
        return clinic;
    }

    private boolean isClinicAvailable(Clinic clinic, LocalDateTime appointmentDateTime) {
        // get all appointments with the same appointDateTime
        // and check whether the given clinic is already taken
        return true;
    }
}
