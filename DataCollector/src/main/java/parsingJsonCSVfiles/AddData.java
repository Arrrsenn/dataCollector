package parsingJsonCSVfiles;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import parsingWebFile.Line;
import parsingWebFile.ParsingWeb;
import parsingWebFile.Station;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AddData {
    private static final AddData addData = new AddData();
    private final ParsingWeb parsingWeb = new ParsingWeb();
    private final ParsingCSVFile csv = new ParsingCSVFile();
    private final ParsingJSONFile json = new ParsingJSONFile();

    public static void main(String[] args) throws IOException {

        createMSCMetro();

        createStationsJSON();
    }

    //TODO Записать свойства станции в msc_metro.json
    private static void createMSCMetro() throws IOException {
        ObjectMapper mapperMetro = new ObjectMapper();
        ObjectWriter writer = mapperMetro.writerWithDefaultPrettyPrinter();

        List<Line> dataLine = addData.getLine();
        Map<String, List<String>> dataStation = addData.getDataStation();

        ObjectNode nodeMSCmetro = mapperMetro.createObjectNode();
        putIntoNodeMSC(dataLine, dataStation, nodeMSCmetro);
        writingToMSCJSON(nodeMSCmetro, writer);
    }

    //TODO Записать свойства станции в stations.json
    private static void createStationsJSON() throws IOException {
        ObjectMapper mapperStations = new ObjectMapper();
        ObjectWriter writer = mapperStations.writerWithDefaultPrettyPrinter();
        ObjectNode nodeStations = mapperStations.createObjectNode();
        putIntoStations(nodeStations);
        writingToStationsJSON(nodeStations, writer);
    }

    //TODO Создать "stations.json" file
    private static void writingToStationsJSON(ObjectNode nodeStations, ObjectWriter writer) throws IOException {
        FileWriter stationsProperties = new FileWriter("stations.json");
        writer.writeValue(stationsProperties, nodeStations);
    }

    //TODO Добавить в узел nodeStations
    private static void putIntoStations(ObjectNode nodeStations) throws IOException {
        List<StationProperties> list = new ArrayList<>();
        List<Station> stations = addData.getStation();
        List<Line> dataLine = addData.getLine();
        Map<String, String> dates = addData.getDates();
        Map<String, String> depths = addData.getDepths();
        Map<String, Boolean> connection = addData.getConnection();

        for (Station station : stations) {
            String nameStation = station.getName();

            dataLine.forEach(line -> {
                String nameLine = line.getName();

                if (station.getNumber().equals(line.getNumber())) {
                    String date = dates.get(nameStation);
                    String depth = depths.get(nameStation);
                    Boolean hasConnect = connection.get(nameStation);

                    list.add(new StationProperties(nameStation,
                            nameLine,
                            date,
                            depth,
                            hasConnect));
                }

            });
        }
        nodeStations.putPOJO("stations", list);
    }

    //TODO Создать "msc_metro.json" file
    private static void writingToMSCJSON(ObjectNode rootNode, ObjectWriter writer) throws IOException {
        FileWriter mscMetro = new FileWriter("msc_metro.json");
        writer.writeValue(mscMetro, rootNode);
    }

    //TODO Добавить в узел nodeMSCMetro
    private static void putIntoNodeMSC(List<Line> dataLine, Map<String, List<String>> dataStation, ObjectNode nodeMSCmetro) {
        nodeMSCmetro.putPOJO("lines", dataLine);
        nodeMSCmetro.putPOJO("stations", dataStation);
    }


    //TODO Карта номера линии и станции
    public Map<String, List<String>> getDataStation() throws IOException {
        List<Station> stations = addData.getStation();
        Map<String, List<String>> dataStation = new LinkedHashMap<>();
        for (Station station : stations) {
            dataStation.put(station.getNumber(), new ArrayList<>());
        }

        for (Station station : stations) {
            if (dataStation.containsKey(station.getNumber())) {
                dataStation.get(station.getNumber()).add(station.getName());
            }
        }
        return dataStation;
    }


    //TODO Карта станций и дат
    public Map<String, String> getDates() throws IOException {
        return csv.getMapDate();
    }

    //TODO Карта станций и глубины
    public Map<String, String> getDepths() throws IOException {
        return json.getMapDepth();
    }

    //TODO Список линий метро
    public List<Line> getLine() throws IOException {
        return parsingWeb.getParseLine();
    }

    //TODO Список станций метро
    public List<Station> getStation() throws IOException {
        return parsingWeb.getParseStation();
    }

    //TODO Информация о переходах
    public Map<String, Boolean> getConnection() throws IOException {
        return parsingWeb.getMapConnection();
    }


}
