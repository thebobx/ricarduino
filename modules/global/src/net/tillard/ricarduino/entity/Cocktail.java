package net.tillard.ricarduino.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.FileDescriptor;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.List;
import javax.persistence.OneToMany;
import com.haulmont.cuba.core.entity.annotation.Listeners;

@NamePattern("%s %s|name,name")
@Table(name = "RICARDUINO_COCKTAIL")
@Entity(name = "ricarduino$Cocktail")
public class Cocktail extends StandardEntity {
    private static final long serialVersionUID = -4188293036823515893L;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    protected String name;

    @Column(name = "DESCRIPTION", length = 500)
    protected String description;

    @Column(name = "HOW_TO", length = 500)
    protected String howTo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PICTURE_ID")
    protected FileDescriptor picture;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "cocktail")
    protected List<CocktailLine> cocktailLines;

    public void setCocktailLines(List<CocktailLine> cocktailLines) {
        this.cocktailLines = cocktailLines;
    }

    public List<CocktailLine> getCocktailLines() {
        return cocktailLines;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setHowTo(String howTo) {
        this.howTo = howTo;
    }

    public String getHowTo() {
        return howTo;
    }

    public void setPicture(FileDescriptor picture) {
        this.picture = picture;
    }

    public FileDescriptor getPicture() {
        return picture;
    }


}