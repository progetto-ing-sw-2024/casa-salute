package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import models.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PersistentDataService {
    private static final PersistentDataService instance = new PersistentDataService();

    private String databaseDirectoryPath;

    public ArrayList<User> users = new ArrayList<>();
    public ArrayList<Clinic> clinics = new ArrayList<>();
    public ArrayList<Patient> patients = new ArrayList<>();
    public ArrayList<Physician> physicians = new ArrayList<>();
    public ArrayList<Nurse> nurses = new ArrayList<>();
    public ArrayList<Appointment> appointments = new ArrayList<>();
    public ArrayList<HealthcareWorker> healthcareWorkers = new ArrayList<>();

    private PersistentDataService() {
    }

    public static PersistentDataService GetInstance() {
        return PersistentDataService.instance;
    }

    public void init(String databaseDirectoryPath) throws IOException {
        this.databaseDirectoryPath = databaseDirectoryPath;
        this.createDatabaseDirectory();

        File usersFile = new File(getUsersFilePath().toString());
        File clinicsFile = new File(getClinicsFilePath().toString());
        File patientsFile = new File(getPatientsFilePath().toString());
        File physiciansFile = new File(getPhysiciansFilePath().toString());
        File nursesFile = new File(getNursesFilePath().toString());
        File appointmentsFile = new File(getAppointmentsFilePath().toString());
        File healtcareWorkersFile = new File(getHealtcareWorkersFilePath().toString());

        if (!usersFile.exists()) {
            saveUsers();
        }

        if (!clinicsFile.exists()) {
            saveClinics();
        }

        if (!patientsFile.exists()) {
            savePatients();
        }

        if (!physiciansFile.exists()) {
            savePhysicians();
        }

        if (!nursesFile.exists()) {
            saveNurses();
        }

        if (!appointmentsFile.exists()) {
            saveAppointments();
        }

        if (!healtcareWorkersFile.exists()) {
            saveHealtcareWorkers();
        }

        load();
    }

    private void load() throws IOException {
        this.users = this.loadUsers();
        this.clinics = this.loadClinics();
        this.patients = this.loadPatients();
        this.physicians = this.loadPhysicians();
        this.nurses = this.loadNurses();
        this.appointments = this.loadAppointments();
        this.healthcareWorkers = this.loadHealthcareWorkers();
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
        Path path = Paths.get(this.databaseDirectoryPath);
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

    private Path getUsersFilePath() {
        return Paths.get(databaseDirectoryPath, "users.json");
    }

    private Path getAppointmentsFilePath() {
        return Paths.get(databaseDirectoryPath, "appointments.json");
    }

    private Path getNursesFilePath() {
        return Paths.get(databaseDirectoryPath, "nurses.json");
    }

    private Path getPhysiciansFilePath() {
        return Paths.get(databaseDirectoryPath, "physicians.json");
    }

    private Path getPatientsFilePath() {
        return Paths.get(databaseDirectoryPath, "patients.json");
    }

    private Path getClinicsFilePath() {
        return Paths.get(databaseDirectoryPath, "clinics.json");
    }

    private Path getHealtcareWorkersFilePath() {
        return Paths.get(databaseDirectoryPath, "healtcareWorkers.json");
    }
}
