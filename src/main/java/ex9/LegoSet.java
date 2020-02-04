package ex9;

public class LegoSet {

    private Long id;
    private String number;
    private int year;
    private int pieces;

    public LegoSet() {
    }

    public LegoSet(Long id, String number, int year, int pieces) {
        this.id = id;
        this.number = number;
        this.year = year;
        this.pieces = pieces;
    }

    public LegoSet(String number, int year, int pieces) {
        this(null, number, year, pieces);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "LegoSet{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", year=" + year +
                ", pieces=" + pieces +
                '}';
    }

}
