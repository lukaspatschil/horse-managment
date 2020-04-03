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
    public Horse findOneById(Long id) {
        LOGGER.trace("findOneById({})", id);

        validator.validateId(id);

        return horseDao.findOneById(id);
    }

    @Override
    public List<Horse> getAllHorse() {
        LOGGER.trace("getAllHorse");
        return horseDao.getAllHorse();
    }

    @Override
    public List<Horse> getHorsefromOwner(Long id) {
        LOGGER.trace("getHorsesformOwner({}}", id);

        validator.validateId(id);

        return horseDao.getHorsefromOwner(id);
    }

    @Override
    public Horse save(Horse horse) {
        LOGGER.trace("saveOwnerwithId({})", horse.getId());

        validator.validateNewHorse(horse);

        return horseDao.save(horse);
    }

    @Override
    public void delete(Long id) {
        LOGGER.trace("deleteHorsewithId({})", id);

        validator.validateId(id);

        horseDao.delete(id);
    }

    @Override
    public Horse update(Long id, Horse horse) {
        LOGGER.trace("updateOwnerWithID({})", id);

        validator.validateNewHorse(horse);
        validator.validateId(id);

        return horseDao.update(id, horse);
    }

    @Override
    public List<Horse> searchHorse(Horse param) {
        LOGGER.trace("searchHorse({})", param);

        validator.validateSearchHorse(param);

        return horseDao.searchHorse(param);
    }
}
