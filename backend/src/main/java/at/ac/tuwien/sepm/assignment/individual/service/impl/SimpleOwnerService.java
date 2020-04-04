package at.ac.tuwien.sepm.assignment.individual.service.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.persistence.OwnerDao;
import at.ac.tuwien.sepm.assignment.individual.service.OwnerService;
import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.util.Validator;
import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleOwnerService implements OwnerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final OwnerDao ownerDao;
    private final Validator validator;

    @Autowired
    public SimpleOwnerService(OwnerDao ownerDao, Validator validator) {
        this.ownerDao = ownerDao;
        this.validator = validator;
    }

    @Override
    public Owner findOneById(Long id) throws NotFoundException, ServiceException, ValidationException {
        LOGGER.trace("findOneById({})", id);

        validator.validateId(id);

        try {
            return ownerDao.findOneById(id);
        } catch (PersistenceException e) {
            throw handleDataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Owner> getAllOwner() throws ServiceException {
        LOGGER.trace("getAllOwner");

        try {
            return ownerDao.getAllOwner();
        } catch (PersistenceException e) {
            throw handleDataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public Owner save(Owner owner) throws ValidationException, ServiceException {
        LOGGER.trace("saveOwnerwithId({})", owner.getId());

        validator.validateNewOwner(owner);

        try {
            return ownerDao.save(owner);
        } catch (PersistenceException e) {
            throw handleDataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException, NotFoundException, ValidationException {
        LOGGER.trace("deleteOwnerwithId({})", id);

        validator.validateId(id);

        try {
            ownerDao.delete(id);
        } catch (PersistenceException e) {
            throw handleDataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public Owner update(Long id, Owner owner) throws ValidationException, ServiceException, NotFoundException {
        LOGGER.trace("updateOwnerWithID({})", id);

        validator.validateNewOwner(owner);
        validator.validateId(id);

        try {
            return ownerDao.update(id, owner);
        } catch (PersistenceException e) {
            throw handleDataAccessException(String.format("Problem while updating owner with id %s", id), e);
        }
    }

    @Override
    public List<Owner> searchOwner(Owner param) throws ServiceException, ValidationException {
        LOGGER.trace("searchOwner({})", param);

        validator.validateSearchOwner(param);

        try {
            return ownerDao.searchOwner(param);
        } catch (PersistenceException e) {
            throw handleDataAccessException("Problem while searching owner with parameter", e);
        }
    }

    private RuntimeException handleDataAccessException(String errMsg, PersistenceException e) {
        LOGGER.error(errMsg, e);
        return new ServiceException(errMsg, e);
    }
}
