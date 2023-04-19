package parsingJsonCSVfiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSearch {
    protected static final String path = "C:\\Users\\alevo\\IdeaProjects\\java_basics\\FilesAndNetwork\\DataCollector\\src\\file";
    private static final String CSV = ".+\\.(csv)$";
    private static final String JSON = ".+\\.(json)$";
    private final List<String> JSONPaths = new ArrayList<>();
    private final List<String> CSVPaths = new ArrayList<>();

    private static boolean isJson(String fileName) {
        Pattern pattern = Pattern.compile(JSON);
        Matcher matcher = pattern.matcher(fileName);
        return matcher.find();
    }

    private static boolean isCSV(String fileName) {
        Pattern pattern = Pattern.compile(CSV);
        Matcher matcher = pattern.matcher(fileName);
        return matcher.find();
    }

    protected List<String> getJSONPaths(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        assert files != null;
        for (File pathFile : files) {
            if (pathFile.isDirectory()) {
                getJSONPaths(pathFile.getAbsolutePath());
            } else if (isJson(pathFile.getName())) {
                JSONPaths.add(pathFile.getAbsolutePath());
            }
        }
        return JSONPaths;
    }

    protected List<String> getCSVPaths(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        assert files != null;
        for (File pathFile : files) {
            if (pathFile.isDirectory()) {
                getCSVPaths(pathFile.getAbsolutePath());
            } else if (isCSV(pathFile.getName())) {
                CSVPaths.add(pathFile.getAbsolutePath());
            }
        }
        return CSVPaths;
    }

}
