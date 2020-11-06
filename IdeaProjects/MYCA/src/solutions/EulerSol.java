package solutions;

import java.util.ArrayList;

public class EulerSol {
    public double[] solve(double x0, double y0, double Xmax, int n) {
        double[] valuesy = new double[n+1];
        double[] valuesx = new double[n+1];
        int i = 0;
        valuesy[i]=y0;
        valuesx[i]=x0;
        double h=(Xmax-x0)/n;
        i++;
        for(;i<=n;i++) {
            valuesy[i]=y0+h*myFunc(valuesx[i-1],y0);
            y0=valuesy[i]; valuesx[i]=valuesx[i-1]+h;
        }
        return valuesy;
    }
    public double myFunc(double x, double y) {
        return (1/x+(2*y)/(x*Math.log(x)));

    }
}
