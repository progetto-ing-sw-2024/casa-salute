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

    public ClinicType getClinicType() {
        return clinicType;
    }

    public void setClinicType(ClinicType clinicType) {
        this.clinicType = clinicType;
    }
}
