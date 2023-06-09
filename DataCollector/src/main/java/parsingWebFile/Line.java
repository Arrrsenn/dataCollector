package parsingWebFile;

public class Line {
    private String number;
    private String name;

    public Line(String lineName, String numLine) {
        this.name = lineName;
        this.number = numLine;
    }

    @Override
    public String toString() {
        return "number " + number + " name" + "\"" + name + "\"";
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
