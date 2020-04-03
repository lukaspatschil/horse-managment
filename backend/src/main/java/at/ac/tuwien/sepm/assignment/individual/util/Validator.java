package at.ac.tuwien.sepm.assignment.individual.util;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Validator {



    public void validateNewOwner(Owner owner) throws ValidationException {
        if (owner.getName() == "" || owner.getName() == null) {
            throw new ValidationException("The name can not be empty.");
        }
        if (owner.getName().length() > 255) {
            throw new ValidationException("The name can not be this long.");
        }
    }

    public void validateUpdateOwner(Owner owner) throws ValidationException {
        if (owner.getName() == "" || owner.getName() == null) {
            throw new ValidationException("The name can not be empty.");
        }
        if (owner.getName().length() > 255) {
            throw new ValidationException("The name can not be this long.");
        }
    }

    public void validateSearchOwner(Owner owner) throws ValidationException {
        if (owner.getName() == null) {
            throw new ValidationException("The name can not be null.");
        }
        if (owner.getName().length() > 255) {
            throw new ValidationException("The name can not be this long.");
        }
    }

    public void validateNewHorse(Horse horse) throws ValidationException {
        if (horse.getName() == "" || horse.getName() == null) {
            throw new ValidationException("The name can not be empty.");
        }
        if (horse.getBirthday()== null || horse.getBirthday().isAfter(LocalDate.of(1980, 1, 1))) {
            throw new ValidationException("The horse can not be this old and alive, don't lie.");
        }
        if (horse.getRating() < 1 || horse.getRating() > 5) {
            throw new ValidationException("The horse must have a rating between 1 nad 5.");
        }
        if (!horse.getRace().equals("MORGAN") && !horse.getRace().equals("ARABIAN") && !horse.getRace().equals("PAINT") && !horse.getRace().equals("APPALOOSA")) {
            throw new ValidationException("The horse must have a valid race.");
        }
        if (!horse.getType().equals("data:image/png;base64,") && !horse.getType().equals("data:image/jpeg;base64,")) {
            throw new ValidationException("The image has to be PNG or JPEG.");
        }
        if (horse.getNotes().length() > 255) {
            throw new ValidationException("The notes can not be this long.");
        }
        if (horse.getName().length() > 255) {
            throw new ValidationException("The name can not be this long.");
        }
    }

    public void validateSearchHorse(Horse horse) throws ValidationException {
        if (horse.getName() != null) {
            if (horse.getName().equals("")) {
                throw new ValidationException("The name can not be empty.");
            }
            if (horse.getName().length() > 255) {
                throw new ValidationException("The name can not be this long.");
            }
        }
        if (horse.getBirthday() != null) {
            if (horse.getBirthday().isAfter(LocalDate.of(1980, 1, 1))) {
                throw new ValidationException("The horse can not be this old and alive, don't lie.");
            }
        }
        if (horse.getRating() != null) {
            if (horse.getRating() < 1 || horse.getRating() > 5) {
                throw new ValidationException("The horse must have a rating between 1 nad 5.");
            }
        }
        if (horse.getRace() != null) {
            if (!horse.getRace().equals("MORGAN") && !horse.getRace().equals("ARABIAN") && !horse.getRace().equals("PAINT") && !horse.getRace().equals("APPALOOSA")) {
                throw new ValidationException("The horse must have a valid race.");
            }
        }
        if (horse.getNotes() != null) {
            if (horse.getNotes().length() > 255) {
                throw new ValidationException("The notes can not be this long.");
            }
        }
    }

    public void validateId(Long id) throws ValidationException {
        if (id < 1) {
            throw new ValidationException("The id has to be grater than 0");
        }
    }
}
