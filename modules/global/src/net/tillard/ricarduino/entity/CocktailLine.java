package net.tillard.ricarduino.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Table(name = "RICARDUINO_COCKTAIL_LINE")
@Entity(name = "ricarduino$CocktailLine")
public class CocktailLine extends StandardEntity {
    private static final long serialVersionUID = -4256700513984751179L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "INGREDIENT_ID")
    protected Ingredient ingredient;

    @NotNull
    @Column(name = "PARTS", nullable = false)
    protected Double parts;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COCKTAIL_ID")
    protected Cocktail cocktail;

    public void setCocktail(Cocktail cocktail) {
        this.cocktail = cocktail;
    }

    public Cocktail getCocktail() {
        return cocktail;
    }


    public void setParts(Double parts) {
        this.parts = parts;
    }

    public Double getParts() {
        return parts;
    }


    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public static CocktailLine getCopyFrom(CocktailLine origCocktailLine) {
        CocktailLine copyCocktailLine = new CocktailLine();
        copyCocktailLine.parts = origCocktailLine.parts;
        copyCocktailLine.ingredient = origCocktailLine.ingredient;
        copyCocktailLine.cocktail = origCocktailLine.cocktail;
        return copyCocktailLine;
    }
}