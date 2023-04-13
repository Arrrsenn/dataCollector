# dataCollector
Utility for collecting and processing data from multiple sources and generating JSON files

- Web page parsing class (class ParsingWeb). It should take place (implement each operation in separate methods):
    * getting the HTML code of the page "List of Moscow Metro stations" using the jsoup library;
    * parsing the received page and getting the following data from it (create separate classes for each data type):
          - Moscow metro lines (name and line number, color is not needed) (class Line);
          - Moscow metro stations (station name and line number) (class Station).

- A class for searching files in folders (class FileSearch). implementation of traversal of folders lying in the archive (https://drive.google.com/file/d/1nb3NIfsIp7KLF0OfmZ_nzwYQLM9zfDUg/view?usp=sharing).

- A class for parsing JSON format files (class ParsingJSONFile). Collection of JSON data and output to the list of corresponding objects

- A class for parsing CSV files (class ParsingCSVFile). Collection of CSV data and output to the list of objects corresponding to them

- A class to which you can add the data obtained in the previous steps, and which creates and writes two JSON files to disk (class AddData):
    * with a list of stations by lines and a list of lines by JSON file format from the SPBMetro project (map.json file);
    * the stations file.json with station properties
