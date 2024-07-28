package repositories;

import models.Clinic;
import models.UniqueResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public abstract class AbstractRepository<T extends UniqueResource> {
    public T getById(UUID id) {
        T result = this.getDataSource()
                .stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .orElse(null);

        return result;
    }

    public ArrayList<T> getAll(Predicate<? super T> filter) {
        return new ArrayList<T>(this.getDataSource().stream().filter(filter).toList());
    }

    public T get(Predicate<? super T> filter) {
        return this.getDataSource().stream().filter(filter).findFirst().orElse(null);
    }

    public void add(T data) throws IOException {
        if (data.getId() != null) throw new IllegalArgumentException();

        T existingData = getById(data.getId());

        if (existingData != null) {
            throw new IllegalArgumentException("id is used by another object");
        }

        getDataSource().add(data);
    }

    // this method will soon be deleted in favor of add(data)
    public void add(UUID id, T data) throws IOException {
        if (id == null) throw new NullPointerException("id is null");
        if (data.getId() != null && data.getId() != id) throw new IllegalArgumentException();

        T existingData = getById(id);

        if (existingData != null) {
            throw new IllegalArgumentException("id is used by another object");
        }

        data.setId(id);
        getDataSource().add(data);
    }

    public void update(UUID id, T data) throws IOException {
        if (id == null) throw new NullPointerException("id is null");
        if (data.getId() != null && data.getId() != id) throw new IllegalArgumentException();

        T existingData = getById(id);

        if (existingData == null) {
            throw new IllegalArgumentException("object not found");
        }

        // lazy update:
        data.setId(id);
        remove(id);
        add(id, data);
    }

    public void remove(UUID id) {
        if (id == null) throw new NullPointerException("id is null");

        T data = getById(id);

        if (data != null) {
            getDataSource().remove(data);
        }
    }

    protected abstract ArrayList<T> getDataSource();
}
