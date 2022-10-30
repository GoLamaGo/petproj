package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVFileWorker {
    private final File file;

    public CSVFileWorker(String csvFileLocation, String csvFileName)
            throws IOException { // TODO как с исключением быть?
        file = new File(csvFileLocation, csvFileName);

        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException("File hasn't been created");
            }
        }
    }

    public List<String> readCsv() throws IOException {
        List<String> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.lines().forEach(list::add);
        } catch (Exception e) {
            throw new IOException("smth going wrong!");
        }

        return list;
    }

    public void writeCsv(List<String> stringLines) throws IOException {
        var fileWriter = new BufferedWriter(new FileWriter(file));

        for (String line : stringLines) {
            fileWriter.write(line);
            fileWriter.newLine();
        }

        fileWriter.close();
    }

}
