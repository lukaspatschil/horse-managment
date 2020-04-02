package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class HorseDto extends BaseDto {

    private String name;
    private String notes;
    private Integer rating;
    private LocalDate birthday;
    private String race;
    private Long owner;
    private String image;
    String type;

    public HorseDto() {
    }

    public HorseDto(Long id, String name, Integer rating, LocalDate birthday, String race, Long owner, String image, String type, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.rating = rating;
        this.birthday = birthday;
        this.race = race;
        this.owner = owner;
        this.image = image;
        this.type = type;
    }

    public HorseDto(Long id, String name, String notes, Integer rating, LocalDate birthday, String race, Long owner, String image, String type, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.notes = notes;
        this.rating = rating;
        this.birthday = birthday;
        this.race = race;
        this.owner = owner;
        this.image = image;
        this.type = type;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HorseDto horseDto = (HorseDto) o;
        return rating == horseDto.rating &&
            name.equals(horseDto.name) &&
            Objects.equals(notes, horseDto.notes) &&
            birthday.equals(horseDto.birthday) &&
            race == horseDto.race &&
            owner.equals(horseDto.owner) &&
            image.equals(horseDto.image) &&
            type.equals(horseDto.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, notes, rating, birthday, race, owner, image, type);
    }

    @Override
    protected String fieldsString() {
        return super.fieldsString() + ", name='" + name + ", notes='" + notes + ", rating='" + rating + ", birthday='" + birthday + '\'';
    }

    @Override
    public String toString() {
        return "HorseDto{" + fieldsString() + '}';
    }
}
