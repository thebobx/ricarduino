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
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DeletePolicy;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import net.tillard.ricarduino.service.GpioService;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.ManyToOne;

@NamePattern("%s %s|name,name")
@Table(name = "RICARDUINO_COCKTAIL")
@Entity(name = "ricarduino$Cocktail")
public class Cocktail extends StandardEntity {
    private static final long serialVersionUID = -4188293036823515893L;

    private static Logger log = LoggerFactory.getLogger(Cocktail.class);

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    protected String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GLASS_ID")
    protected Glass glass;

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

    public void setGlass(Glass glass) {
        this.glass = glass;
    }

    public Glass getGlass() {
        return glass;
    }


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

    public static Cocktail getCopyFrom(Cocktail origCocktail) {
        Cocktail copyCocktail = new Cocktail();
        copyCocktail.name = origCocktail.name;
        copyCocktail.description = origCocktail.description;
        copyCocktail.howTo = origCocktail.howTo;
        copyCocktail.picture = origCocktail.picture;
        List<CocktailLine> copyCocktailLines = new ArrayList<>();
        for (CocktailLine origCocktailLine : origCocktail.getCocktailLines()) {
            copyCocktailLines.add(CocktailLine.getCopyFrom(origCocktailLine));
        }
        copyCocktail.cocktailLines = copyCocktailLines;
        return copyCocktail;
    }

    public void prepareCocktail() {
        Double glassSize = this.getGlass().getSize();
        Double totalParts = new Double(0);
        for (CocktailLine cocktailLine : this.getCocktailLines()) {
            totalParts += cocktailLine.getParts();
        }
        for (CocktailLine cocktailLine : this.getCocktailLines()) {
            try {
                pourIngredient(cocktailLine.getIngredient(), glassSize/totalParts*cocktailLine.getParts());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void pourIngredient(Ingredient ingredient, Double amount) throws InterruptedException {
        log.info("test2 " + ingredient.getName());
        if (ingredient.getAvailable()) {
            Integer gpioNumber = ingredient.getActuator().getGpio();
            Double actuatorSize = ingredient.getActuator().getSize();
            //log.info("Ingredient : " + ingredient.getName() + " | Amount : " + amount.toString());
            //TODO : deal with the amount > actuatorSize
            AppBeans.get(GpioService.class).pulseGpio(gpioNumber,1000);
        }
        Thread.sleep(2000);
    }
}