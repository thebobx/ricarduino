package net.tillard.ricarduino.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@NamePattern("%s|name")
@Table(name = "RICARDUINO_ACTUATOR")
@Entity(name = "ricarduino$Actuator")
public class Actuator extends StandardEntity {
    private static final long serialVersionUID = 7593127605766932655L;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    protected String name;

    @NotNull
    @Column(name = "GPIO", nullable = false, unique = true)
    protected Integer gpio;

    @NotNull
    @Column(name = "SIZE_", nullable = false)
    protected Double size;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "actuator")
    protected Ingredient ingredient;

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGpio(Integer gpio) {
        this.gpio = gpio;
    }

    public Integer getGpio() {
        return gpio;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getSize() {
        return size;
    }


}