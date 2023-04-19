package parsingJsonCSVfiles;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParsingJSONFile {

    private final FileSearch fileSearch = new FileSearch();

    protected Map<String, String> getMapDepth() throws IOException {
        Map<String, String> propertiesStation = new LinkedHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> dataPaths = fileSearch.getJSONPaths(FileSearch.path);

        for (String path : dataPaths) {
            String line = Files.readString(Paths.get(path));
            JsonNode info = objectMapper.readTree(line);

            for (JsonNode station : info) {
                String stationName = station.get("station_name").asText();
                String depth = station.get("depth").asText();
                propertiesStation.put(stationName, depth);
            }

        }
        return propertiesStation;
    }

}
