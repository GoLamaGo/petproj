package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVFileWorker {
    private final File file;

    public CSVFileWorker(File file) {
        this.file = file;
    }

    public List<String> readCsv() {
        List<String> csvLines = new ArrayList<>();

        try (var fileReader = new BufferedReader(new FileReader(file))) {
            fileReader.lines().forEach(csvLines::add);
        } catch (IOException ioe) {
            System.out.println("Ошибка при чтении файла");
            ioe.printStackTrace();
        }
        return csvLines;
    }

    // TODO на методы, использующие этот метот нет смысла навештвать synchronized ?
    public synchronized void writeCsv(List<String> stringLines) {
        try (var fileWriter = new BufferedWriter(new FileWriter(file))) {
            for (String line : stringLines) {
                fileWriter.write(line);
                fileWriter.newLine();
//                fileWriter.close();   // TODO try ведь сам закроет?
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Проблемы при записи в файл");
        }
    }

    public static File createFile(String csvFileLocation, String csvFileName) {
        var file = new File(csvFileLocation, csvFileName);

        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    System.out.println("Ошибка при создании файла");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

}
