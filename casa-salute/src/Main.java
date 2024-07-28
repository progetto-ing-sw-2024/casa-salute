import models.Physician;
import models.User;
import repositories.PhysiciansRepository;
import repositories.UsersRepository;
import services.PersistentStateManager;

import java.io.IOException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            test1();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test1() throws IOException {
        PersistentStateManager psm = PersistentStateManager.GetInstance();
        psm.init("C:\\Users\\Manuel\\Documents\\manuel-documents\\c-sharp-projects\\univr\\casa-salute-database-test-1");
        psm.load();

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

        UsersRepository usersRepository = new UsersRepository(psm);
        usersRepository.Add(user1.getId(), user1);

        PhysiciansRepository physiciansRepository = new PhysiciansRepository(psm);
        physiciansRepository.Add(physician1.getId(), physician1);

        psm.save();
    }
}