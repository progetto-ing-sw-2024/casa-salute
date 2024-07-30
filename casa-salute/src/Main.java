import models.Clinic;
import models.ClinicType;
import models.Physician;
import models.User;
import repositories.ClinicsRepository;
import repositories.PhysiciansRepository;
import repositories.UsersRepository;
import services.PersistentDataService;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

public class Main {
    private static String baseDirectoryPath;
    private static String productionDirectoryPath;

    public static void main(String[] args) throws IOException {
        try {
            baseDirectoryPath = "C:\\Users\\Manuel\\Documents\\manuel-documents\\c-sharp-projects\\univr\\";
            productionDirectoryPath = Paths.get(baseDirectoryPath, "health-facility-data-prod").toString();

            boolean prod = true;

            if (prod) {
                setupInitialData();
            }
            else {
                test1();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setupInitialData() throws IOException {
        PersistentDataService pds = new PersistentDataService(productionDirectoryPath);
        boolean firstInstallation = !pds.exists();
        if (!firstInstallation) return;

        pds.setup();

        ClinicsRepository clinicsRepository = new ClinicsRepository(pds);
        UsersRepository usersRepository = new UsersRepository(pds);

        // set up the admin user
        User admin = new User();
        admin.setId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        admin.setUsername("admin");
        admin.setPassword("admin");

        usersRepository.add(admin);

        // set up 3 clinics for physicians
        for (int i = 0; i < 3; i++) {
            Clinic clinic = new Clinic();
            clinic.setId(UUID.randomUUID());
            clinic.setClinicType(ClinicType.Physician);

            clinicsRepository.add(clinic);
        }

        // set up 1 clinic for pediatricians
        Clinic pediatricClinic = new Clinic();
        pediatricClinic.setId(UUID.randomUUID());
        pediatricClinic.setClinicType(ClinicType.Pediatrician);

        clinicsRepository.add(pediatricClinic);

        // set up 1 blood work clinic
        Clinic bloodWorkClinic = new Clinic();
        bloodWorkClinic.setId(UUID.randomUUID());
        bloodWorkClinic.setClinicType(ClinicType.BloodWork);

        clinicsRepository.add(bloodWorkClinic);

        // set up 1 clinic for nurses
        Clinic nurseClinic = new Clinic();
        nurseClinic.setId(UUID.randomUUID());
        nurseClinic.setClinicType(ClinicType.Nurse);

        pds.save();
    }

    private static void test1() throws IOException {
        PersistentDataService pds = new PersistentDataService(Paths.get(baseDirectoryPath, "health-facility-data-test-0").toString());
        pds.setup();

        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("password1");
        user1.setId(UUID.randomUUID());

        Physician physician1 = new Physician();
        physician1.setId(UUID.randomUUID());
        physician1.setName("Anakin");
        physician1.setSurname("Skywalker");
        physician1.setTaxCode("SKYNKN81D19L781D");
        physician1.setUserId(user1.getId());

        UsersRepository usersRepository = new UsersRepository(pds);
        usersRepository.add(user1);

        PhysiciansRepository physiciansRepository = new PhysiciansRepository(pds);
        physiciansRepository.add(physician1);

        pds.save();
    }
}