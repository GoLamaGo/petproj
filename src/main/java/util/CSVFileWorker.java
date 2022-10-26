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

    private BufferedReader makeFileReader() throws FileNotFoundException {
        return new BufferedReader(new FileReader(file));
    }

    private BufferedWriter makeFileWriter() throws IOException {
        return new BufferedWriter(new FileWriter(file, true));
    }

    public String readLine() throws IOException {
        var fileReader = makeFileReader();
        var result = fileReader.readLine();
        fileReader.close();
        return result;
    }

    public List<String> readAllLines() throws IOException {
        List<String> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.lines().forEach(list::add);
        } catch (Exception e) {
            throw new IOException("smth going wrong!");
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
