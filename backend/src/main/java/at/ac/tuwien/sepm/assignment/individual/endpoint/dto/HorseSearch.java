package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import java.time.LocalDate;

public class HorseSearch {

    String name;
    String notes;
    LocalDate birhtday;
    HorseRace race;
    int rating;

    public HorseSearch(String name, String notes, LocalDate birhtday, HorseRace race, int rating) {
        this.name = name;
        this.notes = notes;
        this.birhtday = birhtday;
        this.race = race;
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public HorseRace getRace() {
        return race;
    }

    public void setRace(HorseRace race) {
        this.race = race;
    }

    public LocalDate getBirhtday() {
        return birhtday;
    }

    public void setBirhtday(LocalDate birhtday) {
        this.birhtday = birhtday;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
