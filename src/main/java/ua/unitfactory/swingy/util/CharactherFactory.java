package ua.unitfactory.swingy.util;

import ua.unitfactory.swingy.model.artifacts.Armor;
import ua.unitfactory.swingy.model.artifacts.Helm;
import ua.unitfactory.swingy.model.artifacts.Weapon;
import ua.unitfactory.swingy.model.characthers.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by skushnir on 12.09.2018.
 */
public class CharactherFactory {

    public static ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    public static Validator validator = vf.getValidator();

     public static Characther Factory(String type) {
         Characther newUnit;
        if (type.compareTo("BlackMage") == 0)
            newUnit = new BlackMage();
        else if (type.compareTo("Elf") == 0)
            newUnit = new Elf();
        else if (type.compareTo("Orc") == 0)
            newUnit = new Orc();
        else
            newUnit = new Villain();

        validate(newUnit);
        return newUnit;
    }

    public static Hero createNewHero(String name, Characther unit, Armor armor, Helm helm, Weapon weapon) {
         Hero newHero = new Hero(name, unit, armor, helm, weapon);
         validate(newHero);
         return newHero;
    }

    public static void validate(Object object) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

        for (ConstraintViolation<Object> cv : constraintViolations) {
            System.out.println(String.format("Кол-во ошибок: %d",
                    constraintViolations.size()));

            System.out.println(String.format(
                    "Внимание, ошибка! property: [%s], value: [%s], message: [%s]",
                    cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));
        }
        if (constraintViolations.size() != 0)
            System.exit(0);
    }
}
