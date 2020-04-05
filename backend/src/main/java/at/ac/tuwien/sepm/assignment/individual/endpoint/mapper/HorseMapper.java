package at.ac.tuwien.sepm.assignment.individual.endpoint.mapper;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseSearch;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class HorseMapper {

    public HorseDto entityToDto(Horse horse) {
        return new HorseDto(horse.getId(), horse.getName(), horse.getNotes(), horse.getRating(), horse.getBirthday(), horse.getRace(), horse.getOwner(), Base64.getEncoder().encodeToString(horse.getImage()), horse.getType(), horse.getCreatedAt(), horse.getUpdatedAt());
    }

    public List<HorseDto> entitysToDto(List<Horse> horses) {
        List<HorseDto> horsesDtos = new ArrayList<>();
        for (Horse horse: horses) {
            horsesDtos.add(entityToDto(horse));
        }
        return horsesDtos;
    }

    public Horse dtoToEntity(HorseDto horse) {
        try {
            return new Horse(horse.getId(), horse.getName(), horse.getNotes(), horse.getRating(), horse.getBirthday(), horse.getRace(), horse.getOwner(), Base64.getDecoder().decode(horse.getImage()), horse.getType(), horse.getCreatedAt(), horse.getUpdatedAt());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("The image is not a Base64");
        }
    }

    public Horse paramstoEntity(HorseSearch horse) {
        return new Horse(Long.valueOf(0), horse.getName(), horse.getNotes(), horse.getRating(), horse.getBirhtday(), horse.getRace(), Long.valueOf(0), Base64.getDecoder().decode(""), "", null,  null);
    }
}