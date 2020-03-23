package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.Race;

public class HorseDto extends BaseDto {

    private String name;
    private String notes;
    private int rating;
    private Date birthday;
    private Race race;

    public HorseDto() {
    }

    public HorseDto(Long id, String name, int rating, Date birthday, Race race, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.rating = rating;
        this.birthday = birthday;
        this.race = race;
    }

    public HorseDto(Long id, String name, String notes, int rating, Date birthday, Race race, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.notes = notes;
        this.rating = rating;
        this.birthday = birthday;
        this.race = race;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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
            birthday.equals(horseDto.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, notes, rating, birthday);
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
