package parsingWebFile;

public class Station {

    private String name;
    private String number;

    public Station(String nameStation, String numLine) {
        this.name = nameStation;
        this.number = numLine;
    }

    @Override
    public String toString() {
        return "line: " + number + "  " + "station: " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
