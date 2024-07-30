package models;

import java.util.ArrayList;
import java.util.UUID;

public class HealthcareWorker implements UniqueResource {
    private UUID id;
    private UUID userId;
    private String name;
    private String surname;
    private String taxCode;
    private final ArrayList<String> specialties = new ArrayList<>();
    private HealthcareWorkerRole healthcareWorkerRole;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public HealthcareWorkerRole getHealthcareWorkerRole() {
        return healthcareWorkerRole;
    }

    public void setHealthcareWorkerRole(HealthcareWorkerRole healthcareWorkerRole) {
        this.healthcareWorkerRole = healthcareWorkerRole;
    }
}
