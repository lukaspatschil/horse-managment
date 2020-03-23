package at.ac.tuwien.sepm.assignment.individual.endpoint.mapper;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.OwnerDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OwnerMapper {

    public OwnerDto entityToDto(Owner owner) {
        return new OwnerDto(owner.getId(), owner.getName(), owner.getCreatedAt(), owner.getUpdatedAt());
    }

    public List<OwnerDto> entitysToDto(List<Owner> owners) {
        List<OwnerDto> ownersDto = new ArrayList<>();
        for (Owner owner: owners) {
            ownersDto.add(entityToDto(owner));
        }
        return ownersDto;
    }

    public Owner dtoToEntity(OwnerDto owner) {
        return new Owner(owner.getId(), owner.getName(), owner.getCreatedAt(), owner.getUpdatedAt());
    }
}
