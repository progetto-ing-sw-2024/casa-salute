import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class Database {
    private final String databaseDirectoryPath;

    private ArrayList<Clinic> clinics = new ArrayList<>();
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Physician> physicians = new ArrayList<>();
    private ArrayList<Nurse> nurses = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public Database(String databaseDirectoryPath) {
        this.databaseDirectoryPath = databaseDirectoryPath;
    }

    public void init() throws IOException {
        this.createDatabaseDirectory();

        File clinicsFile = new File(getClinicsFilePath().toString());
        File patientsFile = new File(getPatientsFilePath().toString());
        File physiciansFile = new File(getPhysiciansFilePath().toString());
        File nursesFile = new File(getNursesFilePath().toString());
        File appointmentsFile = new File(getAppointmentsFilePath().toString());

        clinicsFile.createNewFile();
        patientsFile.createNewFile();
        physiciansFile.createNewFile();
        nursesFile.createNewFile();
        appointmentsFile.createNewFile();
    }

    public void load() throws IOException {
        this.clinics = this.loadClinics();
        this.patients = this.loadPatients();
        this.physicians = this.loadPhysicians();
        this.nurses = this.loadNurses();
        this.appointments = this.loadAppointments();
    }

    public void save() throws IOException {
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

    private ArrayList<Clinic> loadClinics() throws IOException {
        var clinicsFilePath = getClinicsFilePath();
        ArrayList<Clinic> clinics = this.<Clinic>load(clinicsFilePath);
        return clinics;
    }

    private ArrayList<Patient> loadPatients() throws IOException {
        var patientsFilePath = getPatientsFilePath();
        ArrayList<Patient> patients = this.<Patient>load(patientsFilePath);
        return patients;
    }

    private ArrayList<Physician> loadPhysicians() throws IOException {
        var physiciansFilePath = getPhysiciansFilePath();
        ArrayList<Physician> physicians = this.<Physician>load(physiciansFilePath);
        return physicians;
    }

    private ArrayList<Nurse> loadNurses() throws IOException {
        var nursesFilePath = getNursesFilePath();
        ArrayList<Nurse> nurses = this.<Nurse>load(nursesFilePath);
        return nurses;
    }

    private ArrayList<Appointment> loadAppointments() throws IOException {
        var appointmentsFilePath = getAppointmentsFilePath();
        ArrayList<Appointment> appointments = this.<Appointment>load(appointmentsFilePath);
        return appointments;
    }

    private <T> ArrayList<T> load(Path filePath) throws IOException {
        String fileContent = Files.readString(filePath);
        ObjectMapper objMapper = new ObjectMapper();
        var rf = new TypeReference<ArrayList<T>>() {};
        ArrayList<T> result = objMapper.readValue(fileContent, rf);
        return result;
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
