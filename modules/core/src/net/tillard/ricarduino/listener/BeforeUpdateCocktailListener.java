package net.tillard.ricarduino.listener;

import org.springframework.stereotype.Component;
import com.haulmont.cuba.core.listener.BeforeUpdateEntityListener;
import com.haulmont.cuba.core.EntityManager;
import net.tillard.ricarduino.entity.Cocktail;

@Component("ricarduino_BeforeUpdateCocktailListener")
public class BeforeUpdateCocktailListener implements BeforeUpdateEntityListener<Cocktail> {


    @Override
    public void onBeforeUpdate(Cocktail entity, EntityManager entityManager) {

    }


}