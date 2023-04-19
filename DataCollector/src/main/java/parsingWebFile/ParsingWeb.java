package parsingWebFile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParsingWeb {


    private static Map<String, Boolean> parseConnection(Document page) {
        Map<String, Boolean> connection = new LinkedHashMap<>();
        Elements elementsStations_Lines = page.select("div");

        Elements stationsElements = elementsStations_Lines.select("p");

        for (Element elemen : stationsElements) {
            String nameStation = elemen.select("span[class=\"name\"]").text();
            String connections = elemen.select("span").attr("title");

            if (!connections.equals("")) {
                connection.put(nameStation, true);
            } else {
                connection.put(nameStation, false);
            }
        }
        return connection;
    }

    private static List<Station> parseStation(Document page) {
        List<Station> stations = new ArrayList<>();
        Elements dataLines = page.select("div > span");

        for (Element dataNum : dataLines) {
            String numLine = dataNum.attr("data-line");
            Elements stationList = page.select("div[data-depend-set=\"lines-" + numLine + "\"]");
            Elements dataStation = stationList.select("span[class=\"name\"]");

            dataStation.stream().map(Element::text).map(nameStation -> new Station(nameStation, numLine)).forEach(stations::add);

        }
        return stations;
    }

    private static List<Line> parseLines(Document page) {
        List<Line> lines = new ArrayList<>();
        Elements dataLines = page.select("div > span");

        dataLines.forEach(data -> {
            String nameLine = data.select("span").text();
            String numLine = data.attr("data-line");
            lines.add(new Line(nameLine, numLine));
        });

        return lines;
    }

    private static Document getPage() throws IOException {
        String url = "https://skillbox-java.github.io/";
        return Jsoup.parse(new URL(url), 5000);
    }

    public List<Station> getParseStation() throws IOException {
        return parseStation(getPage());
    }

    public List<Line> getParseLine() throws IOException {
        return parseLines(getPage());
    }

    public Map<String, Boolean> getMapConnection() throws IOException {
        return parseConnection(getPage());
    }

}
