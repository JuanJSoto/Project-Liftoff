package org.launchcode.projectliftoff.models;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.Objects;

@Entity
//@MappedSuperClass
public class AbstractEntity {
    // Juan commented out ID and GeneratedValue until it is ready
    @Id
    @GeneratedValue
    private int id;

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}