package parsingJsonCSVfiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingCSVFile {

    private final static String regexNameStation = "([а-яА-Я]+(\\s*)(-?)([а-яА-Я]+)?)";
    private final static String regexDate = "\\d{1,2}\\.\\d{1,2}\\.\\d{4}";
    private final FileSearch fileSearch = new FileSearch();


    protected Map<String, String> getMapDate() throws IOException {
        List<String> dataPaths = fileSearch.getCSVPaths(FileSearch.path);
        Map<String, String> data = new LinkedHashMap<>();

        Pattern patDate = Pattern.compile(regexDate);
        Pattern patNameStation = Pattern.compile(regexNameStation);
        Matcher matchDate;
        Matcher matchNameStation;

        for (String paths : dataPaths) {
            String line = Files.readString(Paths.get(paths));

            matchNameStation = patNameStation.matcher(line);
            matchDate = patDate.matcher(line);

            while (matchDate.find() && matchNameStation.find()) {
                String nameStation = matchNameStation.group();
                String dateStation = matchDate.group();
                data.put(nameStation, dateStation);
            }
        }
        return data;
    }


}

