package ex8;

import java.time.Year;

public class LegoSet {

    private String number;
    private Year year;
    private int pieces;

    public LegoSet() {
    }

    public LegoSet(String number, Year year, int pieces) {
        this.number = number;
        this.year = year;
        this.pieces = pieces;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    @Override
    public String toString() {
        return "LegoSet{" +
                "number='" + number + '\'' +
                ", year=" + year +
                ", pieces=" + pieces +
                '}';
    }

}
