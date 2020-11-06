package solutions;

public class LocalError {
    public double[] solve(double[] y1, double[] y2) {
        double[] Errors = new double[y1.length+1];
        for(int i=0; i<y1.length; i++) {
            Errors[i]=Math.abs(y1[i]-y2[i]);
        }
        return Errors;
    }
}
