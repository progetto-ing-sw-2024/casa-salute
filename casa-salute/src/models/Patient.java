package models;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Patient implements UniqueResource {
    private UUID id;
    private String name;
    private String surname;
    private Date birthdate;
    private String birthPlace;
    private String taxCode;
    private String email;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
