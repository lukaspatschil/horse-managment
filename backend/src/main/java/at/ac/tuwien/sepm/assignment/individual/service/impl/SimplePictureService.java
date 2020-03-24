package at.ac.tuwien.sepm.assignment.individual.service.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Picture;
import at.ac.tuwien.sepm.assignment.individual.persistence.PictureDao;
import at.ac.tuwien.sepm.assignment.individual.service.PictureService;
import at.ac.tuwien.sepm.assignment.individual.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class SimplePictureService implements PictureService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final PictureDao pictureDao;
    private final Validator validator;

    @Autowired
    public SimplePictureService(PictureDao pictureDao, Validator validator) {
        this.pictureDao = pictureDao;
        this.validator = validator;
    }

    @Override
    public Picture findOneById(Long id) {
        LOGGER.trace("findOneById({})", id);
        return pictureDao.findOneById(id);
    }

    @Override
    public Picture save(Picture picture) {
        LOGGER.trace("saveOwnerwithId({})", picture.getId());

        // do some validation

        return pictureDao.save(picture);
    }
}
