package at.ac.tuwien.sepm.assignment.individual.util;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;

@Component
public class Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public void validateNewOwner(Owner owner) throws ValidationException {
        if (owner.getName() == "" || owner.getName() == null) {
            LOGGER.error("The owner name can not be empty.");
            throw new ValidationException("The name can not be empty.");
        }
        if (owner.getName().length() > 255) {
            LOGGER.error("The owner name can not be this long.");
            throw new ValidationException("The name can not be this long.");
        }
    }

    public void validateSearchOwner(Owner owner) throws ValidationException {
        if (owner.getName() == null) {
            LOGGER.error("The owner name can not be null.");
            throw new ValidationException("The name can not be null.");
        }
        if (owner.getName().length() > 255) {
            LOGGER.error("The owner name can not be this long.");
            throw new ValidationException("The name can not be this long.");
        }
    }

    public void validateNewHorse(Horse horse) throws ValidationException {
        if (horse.getName() == "" || horse.getName() == null) {
            LOGGER.error("The horse name can not be empty.");
            throw new ValidationException("The name can not be empty.");
        }
        if (horse.getBirthday() == null || horse.getBirthday().isBefore(LocalDate.of(1980, 1, 1)) ||  horse.getBirthday().isAfter(LocalDate.now())) {
            LOGGER.error("The horse can not be this old and alive, don't lie.");
            throw new ValidationException("The horse can not be this old and alive, don't lie.");
        }
        if (horse.getRating() < 1 || horse.getRating() > 5) {
            LOGGER.error("The horse must have a rating between 1 nad 5.");
            throw new ValidationException("The horse must have a rating between 1 nad 5.");
        }
        if (!horse.getRace().equals("MORGAN") && !horse.getRace().equals("ARABIAN") && !horse.getRace().equals("PAINT") && !horse.getRace().equals("APPALOOSA")) {
            LOGGER.error("The horse must have a valid race.");
            throw new ValidationException("The horse must have a valid race.");
        }
        if (!horse.getType().equals("data:image/png;base64,") && !horse.getType().equals("data:image/jpeg;base64,") && !horse.getType().equals("data:image/jpg;base64,")) {
            LOGGER.error("The image has to be PNG or JPEG.");
            throw new ValidationException("The image has to be PNG or JPEG.");
        }
        if (horse.getNotes().length() > 255) {
            LOGGER.error("The notes can not be this long.");
            throw new ValidationException("The notes can not be this long.");
        }
        if (horse.getName().length() > 255) {
            LOGGER.error("The name can not be this long.");
            throw new ValidationException("The name can not be this long.");
        }
    }

    public void validateSearchHorse(Horse horse) throws ValidationException {
        if (horse.getName() != null) {
            if (horse.getName().length() > 255) {
                LOGGER.error("The name can not be this long.");
                throw new ValidationException("The name can not be this long.");
            }
        }
        if (horse.getBirthday() != null) {
            if (horse.getBirthday().isAfter(LocalDate.of(1970, 1, 1))) {
                LOGGER.error("The horse can not be this old and alive, don't lie.");
                throw new ValidationException("The horse can not be this old and alive, don't lie.");
            }
        }
        if (horse.getRating() != null) {
            if (horse.getRating() < 1 || horse.getRating() > 5) {
                LOGGER.error("The horse must have a rating between 1 nad 5.");
                throw new ValidationException("The horse must have a rating between 1 nad 5.");
            }
        }
        if (horse.getRace() != null) {
            if (!horse.getRace().equals("MORGAN") && !horse.getRace().equals("ARABIAN") && !horse.getRace().equals("PAINT") && !horse.getRace().equals("APPALOOSA")) {
                LOGGER.error("The horse must have a valid race.");
                throw new ValidationException("The horse must have a valid race.");
            }
        }
        if (horse.getNotes() != null) {
            if (horse.getNotes().length() > 255) {
                LOGGER.error("The notes can not be this long.");
                throw new ValidationException("The notes can not be this long.");
            }
        }
    }

    public void validateId(Long id) throws ValidationException {
        if (id < 1) {
            LOGGER.error("The id has to be greater than 0");
            throw new ValidationException("The id has to be greater than 0");
        }
    }
}
