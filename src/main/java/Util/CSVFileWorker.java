package Util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVFileWorker {
    private final static String csvFileLocation = "src/main/resources/";
    private final static String csvFileName = "users.csv"; // TODO вынести в конфиг какой-нибудь?

    private BufferedReader makeFileReader() throws FileNotFoundException {
        return new BufferedReader(new FileReader(csvFileLocation + csvFileName));
    }

    private BufferedWriter makeFileWriter() throws IOException {
        return new BufferedWriter(new FileWriter(csvFileLocation + csvFileName,true));
    }

    public CSVFileWorker() throws IOException { // TODO как с исключением быть?
        File fileName = new File(csvFileLocation, csvFileName);

        if (!fileName.exists()) {
            if (!fileName.createNewFile()) {
                throw new IOException("File hasn't been created");
            }
        }
    }

    public String readLine() throws IOException {
        var fileReader = makeFileReader();
        var result = fileReader.readLine();
        fileReader.close();
        return result;
    }

    public List<String> readAllLines() throws IOException {
        // TODO передать Stream ? .lines()
        List<String> list = new ArrayList<>();

        try (var fileReader = makeFileReader()) {
            fileReader.lines().forEach(list::add);
        }

        return list;
    }

    public void writeCsv(String stringLine) throws IOException {
        var fileWriter = makeFileWriter();
        fileWriter.write(stringLine);
        fileWriter.newLine();
        fileWriter.close();
    }

}
