package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class HorseDto extends BaseDto {

    private String name;
    private String notes;
    private int rating;
    private LocalDateTime birthday;

    public HorseDto() {
    }

    public HorseDto(Long id, String name, int rating, LocalDateTime birthday, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.rating = rating;
        this.birthday = birthday;
    }

    public HorseDto(Long id, String name, String notes, int rating, LocalDateTime birthday, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.notes = notes;
        this.rating = rating;
        this.birthday = birthday;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
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
            Objects.equals(name, horseDto.name) &&
            notes.equals(horseDto.notes) &&
            Objects.equals(birthday, horseDto.birthday);
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
