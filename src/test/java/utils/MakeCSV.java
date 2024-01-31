package utils;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MakeCSV {

    public static void writeDataAtOnce(String filePath, String firstDate, String firstAmount, String firstType,
                                       String secondDate, String secondAmount, String secondType) {
        File file = new File(filePath);
        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            List<String[]> data = new ArrayList<>();
            data.add(new String[] { firstDate, firstAmount, firstType });
            data.add(new String[] { secondDate, secondAmount, secondType });
            writer.writeAll(data);

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
