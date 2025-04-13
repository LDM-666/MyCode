package pca;
import org.apache.commons.math3.linear.*;
import org.apache.commons.math3.stat.correlation.Covariance;
public class PCA {
    public static double[][] reduceDimensions(double[][] data, int dimensions) {
        // 计算协方差矩阵
        Covariance covariance = new Covariance(data);
        RealMatrix covMatrix = covariance.getCovarianceMatrix();

        // 特征值分解
        EigenDecomposition eig = new EigenDecomposition(covMatrix);
        RealMatrix eigenvectors = eig.getV();

        // 投影到主成分空间
        RealMatrix projection = new Array2DRowRealMatrix(data)
                .multiply(eigenvectors.getSubMatrix(0, data[0].length-1, 0, dimensions-1));

        return projection.getData();
    }

    public static double[] getExplainedVariance(double[][] data) {
        Covariance covariance = new Covariance(data);
        RealMatrix covMatrix = covariance.getCovarianceMatrix();
        EigenDecomposition eig = new EigenDecomposition(covMatrix);
        return eig.getRealEigenvalues();
    }
}
