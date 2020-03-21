package at.ac.tuwien.sepm.assignment.individual.endpoint.mapper;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import org.springframework.stereotype.Component;

@Component
public class HorseMapper {

    public HorseDto entityToDto(Horse horse) {
        return new HorseDto(horse.getId(), horse.getName(), horse.getNotes(), horse.getRating(), horse.getBirthday(), horse.getCreatedAt(), horse.getUpdatedAt());
    }

    public Horse dtoToEntity(HorseDto horse) {
        return new Horse(horse.getId(), horse.getName(), horse.getNotes(), horse.getRating(), horse.getBirthday(), horse.getCreatedAt(), horse.getUpdatedAt());
    }
}