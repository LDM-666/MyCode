package visualization;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
public class PCAPlotter {
    public static void plot(double[][] pcaResult, int[] labels) {
        DefaultXYDataset dataset = new DefaultXYDataset();

        // 按类别分组数据
        for (int i = 0; i < 3; i++) {
            double[][] classData = filterByClass(pcaResult, labels, i);
            dataset.addSeries("Class " + (i+1), classData);
        }

        // 创建图表
        JFreeChart chart = ChartFactory.createScatterPlot(
                "PCA Results - Iris Dataset",
                "Principal Component 1",
                "Principal Component 2",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // 显示窗口
        ChartFrame frame = new ChartFrame("PCA Visualization", chart);
        frame.pack();
        frame.setVisible(true);
    }

    private static double[][] filterByClass(double[][] data, int[] labels, int targetClass) {
        // 实现按类别过滤数据
        // 先统计该类有多少样本
        int count = 0;
        for (int label : labels) {
            if (label == targetClass) count++;
        }

        // 创建存储空间
        double[][] result = new double[2][count]; // 2行分别存储x和y坐标

        // 填充数据
        int index = 0;
        for (int i = 0; i < data.length; i++) {
            if (labels[i] == targetClass) {
                result[0][index] = data[i][0]; // PC1
                result[1][index] = data[i][1]; // PC2
                index++;
            }
        }

        return result;
    }
}
