package at.ac.tuwien.sepm.assignment.individual.endpoint;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.OwnerDto;
import at.ac.tuwien.sepm.assignment.individual.endpoint.mapper.OwnerMapper;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.service.OwnerService;
import java.lang.invoke.MethodHandles;
import java.util.List;

import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(OwnerEndpoint.BASE_URL)
public class OwnerEndpoint {

    static final String BASE_URL = "/owners";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;

    @Autowired
    public OwnerEndpoint(OwnerService ownerService, OwnerMapper ownerMapper) {
        this.ownerService = ownerService;
        this.ownerMapper = ownerMapper;
    }

    @GetMapping(value = "/{id}")
    public OwnerDto getOneById(@PathVariable("id") Long id) {
        LOGGER.info("GET " + BASE_URL + "/{}", id);
        try {
            return ownerMapper.entityToDto(ownerService.findOneById(id));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading owner", e);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Error during reading owner", e);
        }
    }

    @GetMapping
    public List<OwnerDto> getAllOwner() {
        LOGGER.info("GET " + BASE_URL + " all owners");

        return ownerMapper.entitysToDto(ownerService.getAllOwner());
    }

    @GetMapping("/search")
    public List<OwnerDto> searchOwner(@RequestParam("params") OwnerDto params) {
        LOGGER.info("GET (search) " + BASE_URL + "/{}", params);
        try {
            return ownerMapper.entitysToDto(ownerService.searchOwner(ownerMapper.dtoToEntity(params)));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading owner", e);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Error during searching owner", e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerDto post(@RequestBody OwnerDto owner) {
        LOGGER.info("POST " + BASE_URL + "/{}", owner.getName());
        try {
            Owner ownerEntity = ownerMapper.dtoToEntity(owner);
            return ownerMapper.entityToDto(ownerService.save(ownerEntity));
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Error during saving owner", e);
        }
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("DELETE " + BASE_URL + "/{}", id);
        try {
            ownerService.delete(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during deleting owner", e);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Error during deleting owner", e);
        }
    }

    @PutMapping(value = "/{id}")
    public OwnerDto updateOne(@RequestBody OwnerDto newOwner, @PathVariable Long id) {
        LOGGER.info("PUT " + BASE_URL + "/{}", id);
        try {
            return ownerMapper.entityToDto(ownerService.update(id, ownerMapper.dtoToEntity(newOwner)));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during updating owner", e);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Error during updating owner", e);
        }
    }
}
