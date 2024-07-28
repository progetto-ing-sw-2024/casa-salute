package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Patient implements UniqueResource {
    private UUID id;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String birthPlace;
    private String taxCode;
    private String email;
    private UUID physicianId;
    private ArrayList<UUID> supervisedUsersId = new ArrayList<UUID>();

    public UUID getId() {
        return id;
    }

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

    public LocalDate getBirthDate() {
        return birthdate;
    }

    public void setBirthDate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(UUID physicianId) {
        this.physicianId = physicianId;
    }
}
