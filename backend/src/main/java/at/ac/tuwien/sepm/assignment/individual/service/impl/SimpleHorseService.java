package at.ac.tuwien.sepm.assignment.individual.service.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.persistence.HorseDao;
import at.ac.tuwien.sepm.assignment.individual.service.HorseService;
import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.util.Validator;
import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleHorseService implements HorseService{

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final HorseDao horseDao;
    private final Validator validator;

    @Autowired
    public SimpleHorseService(HorseDao horseDao, Validator validator) {
        this.horseDao = horseDao;
        this.validator = validator;
    }

    @Override
    public Horse findOneById(Long id) throws ServiceException, ValidationException, NotFoundException {
        LOGGER.trace("findOneById({})", id);

        validator.validateId(id);

        try {
            return horseDao.findOneById(id);
        } catch (PersistenceException e) {
            throw handleDataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Horse> getAllHorse() throws ServiceException {
        LOGGER.trace("getAllHorse");

        try {
            return horseDao.getAllHorse();
        } catch (PersistenceException e) {
            throw handleDataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Horse> getHorsefromOwner(Long id) throws ServiceException, ValidationException, NotFoundException {
        LOGGER.trace("getHorsesformOwner({}}", id);

        validator.validateId(id);

        try {
            return horseDao.getHorsefromOwner(id);
        } catch (PersistenceException e) {
            throw handleDataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public Horse save(Horse horse) throws ServiceException {
        LOGGER.trace("saveOwnerwithId({})", horse.getId());

        validator.validateNewHorse(horse);

        try {
            return horseDao.save(horse);
        } catch (PersistenceException e) {
            throw handleDataAccessException(e.getMessage(), e);
        }

    }

    @Override
    public void delete(Long id) throws ServiceException, ValidationException, NotFoundException {
        LOGGER.trace("deleteHorsewithId({})", id);

        validator.validateId(id);

        try {
            horseDao.delete(id);
        } catch (PersistenceException e) {
            throw handleDataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public Horse update(Long id, Horse horse) throws ServiceException, ValidationException, NotFoundException {
        LOGGER.trace("updateOwnerWithID({})", id);

        validator.validateNewHorse(horse);
        validator.validateId(id);
        try {
            return horseDao.update(id, horse);
        } catch (PersistenceException e) {
            throw handleDataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Horse> searchHorse(Horse param) throws ServiceException, ValidationException {
        LOGGER.trace("searchHorse({})", param);

        validator.validateSearchHorse(param);
        try {
            return horseDao.searchHorse(param);
        } catch (ValidationException e) {
            LOGGER.error(e.getMessage());
            throw new ValidationException("Could not delete because it still has horses");
        } catch (PersistenceException e) {
            throw handleDataAccessException(e.getMessage(), e);
        }
    }

    private RuntimeException handleDataAccessException(String errMsg, PersistenceException e) {
        LOGGER.error(errMsg, e);
        return new ServiceException(errMsg, e);
    }
}
