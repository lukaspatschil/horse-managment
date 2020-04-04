package at.ac.tuwien.sepm.assignment.individual.service.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.persistence.HorseDao;
import at.ac.tuwien.sepm.assignment.individual.service.HorseService;
import at.ac.tuwien.sepm.assignment.individual.util.Validator;
import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;

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
    public Horse findOneById(Long id) {
        LOGGER.trace("findOneById({})", id);

        validator.validateId(id);
        try {
            return horseDao.findOneById(id);
        } catch (DataAccessException e) {
            throw handleDataAccessException(String.format("Problem while reading horse with id %s", id), e);
        }
    }

    @Override
    public List<Horse> getAllHorse() {
        LOGGER.trace("getAllHorse");

        try {
            return horseDao.getAllHorse();
        } catch (DataAccessException e) {
            throw handleDataAccessException("Problem while reading horses", e);
        }
    }

    @Override
    public List<Horse> getHorsefromOwner(Long id) {
        LOGGER.trace("getHorsesformOwner({}}", id);

        validator.validateId(id);
        try {
            return horseDao.getHorsefromOwner(id);
        } catch (DataAccessException e) {
            throw handleDataAccessException(String.format("Problem while getting owner from horse with id %s", id), e);
        }
    }

    @Override
    public Horse save(Horse horse) {
        LOGGER.trace("saveOwnerwithId({})", horse.getId());

        validator.validateNewHorse(horse);

        try {
            return horseDao.save(horse);
        } catch (DataAccessException e) {
            throw handleDataAccessException(String.format("Problem while saving horse with name %s", horse.getName()), e);
        }

    }

    @Override
    public void delete(Long id) {
        LOGGER.trace("deleteHorsewithId({})", id);

        validator.validateId(id);

        try {
            horseDao.delete(id);
        } catch (DataAccessException e) {
            throw handleDataAccessException(String.format("Problem while deleting horse with id %s", id), e);
        }
    }

    @Override
    public Horse update(Long id, Horse horse) {
        LOGGER.trace("updateOwnerWithID({})", id);

        validator.validateNewHorse(horse);
        validator.validateId(id);
        try {
            return horseDao.update(id, horse);
        } catch (DataAccessException e) {
            throw handleDataAccessException(String.format("Problem while updating horse with id %s", id), e);
        }
    }

    @Override
    public List<Horse> searchHorse(Horse param) {
        LOGGER.trace("searchHorse({})", param);

        validator.validateSearchHorse(param);
        try {
            return horseDao.searchHorse(param);
        } catch (DataAccessException e) {
            throw handleDataAccessException("Problem while searching horse with parameter", e);
        }
    }

    private RuntimeException handleDataAccessException(String errMsg, DataAccessException e) {
        LOGGER.error(errMsg, e);
        return new RuntimeException(errMsg, e);
    }
}
