package solutions;

public class ExactSol {
    public double[] solve(double x0, double y0, double Xmax, int n) {
        double[] valuesy = new double[n+1];
        double[] valuesx = new double[n+1];
        int i = 0;
        valuesy[i]=y0;
        valuesx[i]=x0;
        double h=(Xmax-x0)/n;
        i++;
        double myC=y0/(Math.pow(Math.log(x0),2))+1/Math.log(x0);;
        for(; i<=n; i++) {
            valuesx[i]=valuesx[i-1]+h;
            valuesy[i]=-Math.log(valuesx[i])+Math.pow(Math.log(valuesx[i]),2)*myC;
        }
        return valuesy;
    }

}
