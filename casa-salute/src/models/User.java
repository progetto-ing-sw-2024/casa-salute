package models;

import java.util.UUID;

public class User implements UniqueResource {
    private UUID id;
    private String username;
    private String password;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }
}
