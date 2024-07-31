import java.util.HashMap;

public class AppSettings {
    private String userName;
    private HashMap<String, String> baseDirectoryPath;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public HashMap<String, String> getBaseDirectoryPath() {
        return baseDirectoryPath;
    }

    public void setBaseDirectoryPath(HashMap<String, String> baseDirectoryPath) {
        this.baseDirectoryPath = baseDirectoryPath;
    }

    public String getUserBaseDirectoryPath() {
        String path = baseDirectoryPath.get(userName);
        return path;
    }
}
