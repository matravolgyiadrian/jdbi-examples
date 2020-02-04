package ex7;

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

public class LegoSet {

    private String number;
    private int year;
    private int pieces;
    private Set<String> tags = new HashSet<>();

    public LegoSet() {
    }

    public LegoSet(String number, int year, int pieces, String... tags) {
        this.number = number;
        this.year = year;
        this.pieces = pieces;
        Collections.addAll(this.tags, tags);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public LegoSet addTag(String tag) {
        tags.add(tag);
        return this;
    }

    @Override
    public String toString() {
        return "LegoSet{" +
                "number='" + number + '\'' +
                ", year=" + year +
                ", pieces=" + pieces +
                ", tags=" + tags +
                '}';
    }

}
