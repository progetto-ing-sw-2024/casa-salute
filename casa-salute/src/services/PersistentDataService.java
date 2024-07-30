package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import models.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PersistentDataService {
    private final String baseDirectoryPath;

    public ArrayList<User> users = new ArrayList<>();
    public ArrayList<Clinic> clinics = new ArrayList<>();
    public ArrayList<Patient> patients = new ArrayList<>();
    public ArrayList<Physician> physicians = new ArrayList<>();
    public ArrayList<Nurse> nurses = new ArrayList<>();
    public ArrayList<Appointment> appointments = new ArrayList<>();
    public ArrayList<HealthcareWorker> healthcareWorkers = new ArrayList<>();

    public PersistentDataService(String baseDirectoryPath) {
        this.baseDirectoryPath = baseDirectoryPath;
    }
    public boolean exists() {
        File creationLog = new File(getCreationLogFilePath().toString());
        return creationLog.exists();
    }

    public void setup() throws IOException {
        this.createDatabaseDirectory();

        File creationLog = new File(getCreationLogFilePath().toString());
        if (!creationLog.exists()) creationLog.createNewFile();

        File usersFile = new File(getUsersFilePath().toString());
        if (!usersFile.exists()) saveUsers();

        File clinicsFile = new File(getClinicsFilePath().toString());
        if (!clinicsFile.exists()) saveClinics();

        File patientsFile = new File(getPatientsFilePath().toString());
        if (!patientsFile.exists()) savePatients();

        File physiciansFile = new File(getPhysiciansFilePath().toString());
        if (!physiciansFile.exists()) savePhysicians();

        File nursesFile = new File(getNursesFilePath().toString());
        if (!nursesFile.exists()) saveNurses();

        File appointmentsFile = new File(getAppointmentsFilePath().toString());
        if (!appointmentsFile.exists()) saveAppointments();

        File healtcareWorkersFile = new File(getHealtcareWorkersFilePath().toString());
        if (!healtcareWorkersFile.exists()) saveHealtcareWorkers();

        load();
    }

    private void load() throws IOException {
        this.users = loadUsers();
        this.clinics = loadClinics();
        this.patients = loadPatients();
        this.physicians = loadPhysicians();
        this.nurses = loadNurses();
        this.appointments = loadAppointments();
        this.healthcareWorkers = loadHealthcareWorkers();
    }

    public void save() throws IOException {
        this.saveUsers();
        this.saveClinics();
        this.savePatients();
        this.savePhysicians();
        this.saveNurses();
        this.saveAppointments();
        this.saveHealtcareWorkers();
    }

    private void createDatabaseDirectory() throws IOException {
        Path path = Paths.get(this.baseDirectoryPath);
        Files.createDirectories(path);
    }

    private <T> void writeFile(List<T> data, Path filePath) throws IOException {
        File file = filePath.toFile();
        if (!file.exists()) {
            file.createNewFile();
        }

        ObjectMapper objMapper = new ObjectMapper();
        String json = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
        Files.writeString(filePath, json);
    }

    private <T> ArrayList<T> readFile(Path filePath, Class<T> elementClass) throws IOException {
        String fileContent = Files.readString(filePath);
        ArrayList<T> data = DeserializeList(fileContent, elementClass);
        return data;
    }

    private void saveUsers() throws IOException {
        var usersFilePath = getUsersFilePath();
        this.writeFile(this.users, usersFilePath);
    }

    private void saveClinics() throws IOException {
        var clinicsFilePath = getClinicsFilePath();
        this.writeFile(this.clinics, clinicsFilePath);
    }

    private void savePatients() throws IOException {
        var patientsFilePath = getPatientsFilePath();
        this.writeFile(this.patients, patientsFilePath);
    }

    private void savePhysicians() throws IOException {
        var physiciansFilePath = getPhysiciansFilePath();
        this.writeFile(this.physicians, physiciansFilePath);
    }

    private void saveNurses() throws IOException {
        var nursesFilePath = getNursesFilePath();
        this.writeFile(this.nurses, nursesFilePath);
    }

    private void saveAppointments() throws IOException {
        var appointmentsFilePath = getAppointmentsFilePath();
        this.writeFile(this.appointments, appointmentsFilePath);
    }

    private void saveHealtcareWorkers() throws IOException {
        var healtcareWorkersFilePath = getHealtcareWorkersFilePath();
        this.writeFile(this.healthcareWorkers, healtcareWorkersFilePath);
    }

    private ArrayList<User> loadUsers() throws IOException {
        var filePath = getUsersFilePath();
        return readFile(filePath, User.class);
    }

    private ArrayList<Clinic> loadClinics() throws IOException {
        var filePath = getClinicsFilePath();
        return readFile(filePath, Clinic.class);
    }

    private ArrayList<Patient> loadPatients() throws IOException {
        var filePath = getPatientsFilePath();
        return readFile(filePath, Patient.class);
    }

    private ArrayList<Physician> loadPhysicians() throws IOException {
        var filePath = getPhysiciansFilePath();
        return readFile(filePath, Physician.class);
    }

    private ArrayList<Nurse> loadNurses() throws IOException {
        var filePath = getNursesFilePath();
        return readFile(filePath, Nurse.class);
    }

    private ArrayList<Appointment> loadAppointments() throws IOException {
        var filePath = getAppointmentsFilePath();
        return readFile(filePath, Appointment.class);
    }

    private ArrayList<HealthcareWorker> loadHealthcareWorkers() throws IOException {
        var filePath = getHealtcareWorkersFilePath();
        return readFile(filePath, HealthcareWorker.class);
    }

    private <T> ArrayList<T> DeserializeList(String json, Class<T> genericClass) throws IOException {
        ObjectMapper objMapper = new ObjectMapper();
        CollectionType ct = objMapper.getTypeFactory().constructCollectionType(ArrayList.class, genericClass);
        ArrayList<T> result = objMapper.readValue(json, ct);
        return result;
    }

    private Path getCreationLogFilePath() {
        return Paths.get(baseDirectoryPath, ".creation-log");
    }

    private Path getUsersFilePath() {
        return Paths.get(baseDirectoryPath, "users.json");
    }

    private Path getAppointmentsFilePath() {
        return Paths.get(baseDirectoryPath, "appointments.json");
    }

    private Path getNursesFilePath() {
        return Paths.get(baseDirectoryPath, "nurses.json");
    }

    private Path getPhysiciansFilePath() {
        return Paths.get(baseDirectoryPath, "physicians.json");
    }

    private Path getPatientsFilePath() {
        return Paths.get(baseDirectoryPath, "patients.json");
    }

    private Path getClinicsFilePath() {
        return Paths.get(baseDirectoryPath, "clinics.json");
    }

    private Path getHealtcareWorkersFilePath() {
        return Paths.get(baseDirectoryPath, "healtcareWorkers.json");
    }
}
