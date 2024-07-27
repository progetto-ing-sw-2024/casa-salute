package models;

import java.util.UUID;

public class Clinic implements UniqueResource {
    private UUID id;
    private ClinicType clinicType;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }
}
