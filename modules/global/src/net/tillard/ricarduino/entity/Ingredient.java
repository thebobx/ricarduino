package net.tillard.ricarduino.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@NamePattern("%s|name")
@Table(name = "RICARDUINO_INGREDIENT")
@Entity(name = "ricarduino$Ingredient")
public class Ingredient extends StandardEntity {
    private static final long serialVersionUID = -7335626463285660301L;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    protected String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PICTURE_ID")
    protected FileDescriptor picture;

    @NotNull
    @Column(name = "AVAILABLE", nullable = false)
    protected Boolean available = false;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTUATOR_ID")
    protected Actuator actuator;

    public void setActuator(Actuator actuator) {
        this.actuator = actuator;
    }

    public Actuator getActuator() {
        return actuator;
    }


    public void setPicture(FileDescriptor picture) {
        this.picture = picture;
    }

    public FileDescriptor getPicture() {
        return picture;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Boolean getAvailable() {
        return available;
    }


}