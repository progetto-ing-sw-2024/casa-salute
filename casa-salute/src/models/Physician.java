package models;

import java.util.ArrayList;
import java.util.UUID;

public class Physician implements UniqueResource {
    private UUID id;
    private String name;
    private String surname;
    private String taxCode;
    private ArrayList<Object> specialties;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }
}
