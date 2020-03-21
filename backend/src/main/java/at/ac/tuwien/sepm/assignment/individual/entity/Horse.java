package at.ac.tuwien.sepm.assignment.individual.entity;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;

import java.time.LocalDateTime;
import java.util.Objects;

public class Horse extends BaseEntity {

    private String name;
    private String notes;
    private int rating;
    private LocalDateTime birthday;

    public Horse() {
    }

    public Horse(Long id, String name, int rating, LocalDateTime birthday, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.rating = rating;
        this.birthday = birthday;
    }

    public Horse(Long id, String name, String notes, int rating, LocalDateTime birthday, LocalDateTime created, LocalDateTime updated) {
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
        Horse horse = (Horse) o;
        return rating == horse.rating &&
            Objects.equals(name, horse.name) &&
            notes.equals(horse.notes) &&
            Objects.equals(birthday, horse.birthday);
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
        return "Horse{" + fieldsString() + '}';
    }
}
