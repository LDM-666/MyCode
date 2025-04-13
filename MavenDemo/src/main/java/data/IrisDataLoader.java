package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
public class IrisDataLoader {
    public static double[][] loadCSV(String filePath) throws Exception {
        List<double[]> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double[] features = new double[4];
                for (int i = 0; i < 4; i++) {
                    features[i] = Double.parseDouble(values[i]);
                }
                dataList.add(features);
            }
        }
        return dataList.toArray(new double[0][]);
    }
}
