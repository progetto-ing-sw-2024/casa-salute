package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import models.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;

public class PersistentStateManager {
    private static final PersistentStateManager instance = new PersistentStateManager();

    private String databaseDirectoryPath;

    public ArrayList<User> users = new ArrayList<>();
    public ArrayList<Clinic> clinics = new ArrayList<>();
    public ArrayList<Patient> patients = new ArrayList<>();
    public ArrayList<Physician> physicians = new ArrayList<>();
    public ArrayList<Nurse> nurses = new ArrayList<>();
    public ArrayList<Appointment> appointments = new ArrayList<>();

    private PersistentStateManager() {
    }

    public static PersistentStateManager GetInstance() {
        return PersistentStateManager.instance;
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

        Object[] emptyArray = {};

        if (!usersFile.exists()) {
            usersFile.createNewFile();
            this.save(emptyArray, getUsersFilePath());
        }

        if (!clinicsFile.exists()) {
            clinicsFile.createNewFile();
            this.save(emptyArray, getClinicsFilePath());
        }

        if (!patientsFile.exists()) {
            patientsFile.createNewFile();
            this.save(emptyArray, getPatientsFilePath());
        }

        if (!physiciansFile.exists()) {
            physiciansFile.createNewFile();
            this.save(emptyArray, getPhysiciansFilePath());
        }

        if (!nursesFile.exists()) {
            nursesFile.createNewFile();
            this.save(emptyArray, getNursesFilePath());
        }

        if (!appointmentsFile.exists()) {
            appointmentsFile.createNewFile();
            this.save(emptyArray, getAppointmentsFilePath());
        }
    }

    public void load() throws IOException {
        this.users = this.loadUsers();
        this.clinics = this.loadClinics();
        this.patients = this.loadPatients();
        this.physicians = this.loadPhysicians();
        this.nurses = this.loadNurses();
        this.appointments = this.loadAppointments();
    }

    public void save() throws IOException {
        this.saveUsers();
        this.saveClinics();
        this.savePatients();
        this.savePhysicians();
        this.saveNurses();
        this.saveAppointments();
    }

    private void createDatabaseDirectory() throws IOException {
        Path path = Paths.get(this.databaseDirectoryPath);
        Files.createDirectories(path);
    }

    private void saveUsers() throws IOException {
        var usersFilePath = getUsersFilePath();
        this.save(this.users, usersFilePath);
    }

    private void saveClinics() throws IOException {
        var clinicsFilePath = getClinicsFilePath();
        this.save(this.clinics, clinicsFilePath);
    }

    private void savePatients() throws IOException {
        var patientsFilePath = getPatientsFilePath();
        this.save(this.patients, patientsFilePath);
    }

    private void savePhysicians() throws IOException {
        var physiciansFilePath = getPhysiciansFilePath();
        this.save(this.physicians, physiciansFilePath);
    }

    private void saveNurses() throws IOException {
        var nursesFilePath = getNursesFilePath();
        this.save(this.nurses, nursesFilePath);
    }

    private void saveAppointments() throws IOException {
        var appointmentsFilePath = getAppointmentsFilePath();
        this.save(this.appointments, appointmentsFilePath);
    }

    private void save(Object data, Path filePath) throws IOException {
        ObjectMapper objMapper = new ObjectMapper();
        String json = objMapper.writeValueAsString(data);
        Files.writeString(filePath, json);
    }

    private ArrayList<User> loadUsers() throws IOException {
        var filePath = getUsersFilePath();
        String fileContent = Files.readString(filePath);
        ArrayList<User> users = DeserializeList(fileContent, User.class);
        return users;
    }

    private ArrayList<Clinic> loadClinics() throws IOException {
        var filePath = getClinicsFilePath();
        String fileContent = Files.readString(filePath);
        ArrayList<Clinic> clinics = DeserializeList(fileContent, Clinic.class);
        return clinics;
    }

    private ArrayList<Patient> loadPatients() throws IOException {
        var filePath = getPatientsFilePath();
        String fileContent = Files.readString(filePath);
        ArrayList<Patient> patients = DeserializeList(fileContent, Patient.class);
        return patients;
    }

    private ArrayList<Physician> loadPhysicians() throws IOException {
        var filePath = getPhysiciansFilePath();
        String fileContent = Files.readString(filePath);
        ArrayList<Physician> physicians = DeserializeList(fileContent, Physician.class);
        return physicians;
    }

    private ArrayList<Nurse> loadNurses() throws IOException {
        var filePath = getNursesFilePath();
        String fileContent = Files.readString(filePath);
        ArrayList<Nurse> nurses = DeserializeList(fileContent, Nurse.class);
        return nurses;
    }

    private ArrayList<Appointment> loadAppointments() throws IOException {
        var filePath = getAppointmentsFilePath();
        String fileContent = Files.readString(filePath);
        ArrayList<Appointment> appointments = DeserializeList(fileContent, Appointment.class);
        return appointments;
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
}
