package solutions;

public class RungeKuttaSol {
    double k1,k2,k3,k4;
    public double[] solve(double x0, double y0, double Xmax, int n) {
        double[] valuesy = new double[n+1];
        double[] valuesx = new double[n+1];
        int i=0;
        valuesy[i]=y0;
        valuesx[i]=x0;
        double h=(Xmax-x0)/n;
        i++;
        for(;i<=n;i++) {
            k1=myFunc(valuesx[i-1],y0);
            k2=myFunc((valuesx[i-1]+h/2),y0+k1*h/2);
            k3=myFunc((valuesx[i-1]+h/2),y0+k2*h/2);
            k4=myFunc((valuesx[i-1]+h),y0+k3*h);
            valuesy[i]=y0+h*(k1+2*k2+2*k3+k4)/6;
            y0=valuesy[i]; valuesx[i]=valuesx[i-1]+h;
        }
        return valuesy;
    }
    public double myFunc(double x, double y) {
        double res = (1/x+(2*y)/(x*Math.log(x)));
        return res;
    }
}
