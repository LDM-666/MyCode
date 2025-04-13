import data.IrisDataLoader;
import pca.PCA;
import utils.DataNormalizer;
import visualization.PCAPlotter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws Exception {
        // 1. 加载数据
        double[][] irisData = IrisDataLoader.loadCSV("C:\\Users\\23230\\Desktop\\iris.csv");
        int[] labels = loadLabels(); // 加载标签方法

        // 2. 数据标准化
        double[][] normalizedData = DataNormalizer.zScoreNormalize(irisData);

        // 3. PCA降维
        double[][] pcaResult = PCA.reduceDimensions(normalizedData, 2);
        double[] explainedVariance = PCA.getExplainedVariance(normalizedData);

        // 4. 输出解释方差
        System.out.println("Explained Variance:");
        System.out.println("PC1: " + explainedVariance[0]);
        System.out.println("PC2: " + explainedVariance[1]);

        // 5. 可视化
        PCAPlotter.plot(pcaResult, labels);
    }

    private static int[] loadLabels() {
        // 实现标签加载
        int[] labels = new int[150]; // Iris数据集固定150个样本
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\23230\\Desktop\\iris.csv"))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null && index < 150) {
                String[] values = line.split(",");
                // 假设类别在最后一列
                String label = values[values.length-1].trim();
                switch (label) {
                    case "setosa":
                        labels[index] = 0;
                        break;
                    case "versicolor":
                        labels[index] = 1;
                        break;
                    case "virginica":
                        labels[index] = 2;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown label: " + label);
                }
                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return labels;
    }
}
