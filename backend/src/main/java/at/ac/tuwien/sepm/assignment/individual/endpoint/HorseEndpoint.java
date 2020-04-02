package at.ac.tuwien.sepm.assignment.individual.endpoint;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseSearch;
import at.ac.tuwien.sepm.assignment.individual.endpoint.mapper.HorseMapper;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import java.lang.invoke.MethodHandles;
import java.util.List;

import at.ac.tuwien.sepm.assignment.individual.service.HorseService;
import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(HorseEndpoint.BASE_URL)
public class HorseEndpoint {

    static final String BASE_URL = "/horses";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final HorseService horseService;
    private final HorseMapper horseMapper;

    @Autowired
    public HorseEndpoint(HorseService horseService, HorseMapper horseMapper) {
        this.horseService = horseService;
        this.horseMapper = horseMapper;
    }

    @GetMapping(value = "/{id}")
    public HorseDto getOneById(@PathVariable("id") Long id) {
        LOGGER.info("GET " + BASE_URL + "/{}", id);
        try {
            return horseMapper.entityToDto(horseService.findOneById(id));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading horse", e);
        }
    }

    @GetMapping
    public List<HorseDto> getAllHorse() {
        LOGGER.info("GET " + BASE_URL + " all horses");
        try {
            return horseMapper.entitysToDto(horseService.getAllHorse());
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading horse", e);
        }
    }

    @GetMapping("/owners/{id}")
    public List<HorseDto> getHorseformOwner(@PathVariable("id") Long id) {
        LOGGER.info("GET horses from owner" +  BASE_URL + "/{}", id);
        try {
            return horseMapper.entitysToDto(horseService.getHorsefromOwner(id));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading horse");
        }
    }

    @GetMapping("/search")
    public List<HorseDto> searchHorse(HorseSearch params) {
        LOGGER.info("GET (search) " + BASE_URL + "/{}", params.getName());
        try {
            return horseMapper.entitysToDto(horseService.searchHorse(horseMapper.paramstoEntity(params)));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during searching horse", e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HorseDto post(@RequestBody HorseDto horse) {
        LOGGER.info("POST " + BASE_URL + "/{}", horse.getName());
        try {
            return horseMapper.entityToDto(horseService.save(horseMapper.dtoToEntity(horse)));
        } catch (ValidationException e) {
            throw new
                ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                "Error during saving horse", e);
        }
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("DELETE " + BASE_URL + "/{}", id);
        try {
            horseService.delete(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading horse", e);
        }
    }

    @PutMapping(value = "/{id}")
    public HorseDto updateOne(@RequestBody HorseDto newHorse, @PathVariable Long id) {
        LOGGER.info("PUT " + BASE_URL + "/{}", id);
        try {
            Horse newHorseEntity = horseMapper.dtoToEntity(newHorse);
            return horseMapper.entityToDto(horseService.update(id, newHorseEntity));
        } catch (NotFoundException e) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading horse", e);
        }
    }
}
