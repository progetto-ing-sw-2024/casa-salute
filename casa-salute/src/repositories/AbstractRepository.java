package repositories;

import models.Patient;
import models.UniqueResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public abstract class AbstractRepository<T extends UniqueResource> {
    public T GetById(UUID id) {
        T result = this.GetDataSource()
                .stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .orElse(null);

        return result;
    }

    public void Add(UUID id, T data) throws IOException {
        if (id == null) throw new NullPointerException("id is null");
        if (data.getId() != null && data.getId() != id) throw new IllegalArgumentException();

        T existingData = GetById(id);

        if (existingData != null) {
            throw new IllegalArgumentException("id is used by another object");
        }

        data.setId(id);
        GetDataSource().add(data);
    }

    public void Update(UUID id, T data) throws IOException {
        if (id == null) throw new NullPointerException("id is null");
        if (data.getId() != null && data.getId() != id) throw new IllegalArgumentException();

        T existingData = GetById(id);

        if (existingData == null) {
            throw new IllegalArgumentException("object not found");
        }

        // lazy update:
        Remove(id);
        Add(id, data);
    }

    public void Remove(UUID id) {
        if (id == null) throw new NullPointerException("id is null");

        T data = GetById(id);

        if (data != null) {
            GetDataSource().remove(data);
        }
    }

    protected abstract ArrayList<T> GetDataSource();
}
