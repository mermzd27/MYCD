package solutions;

public class GlobalError {
    public double[] solveEuler(double x0, double y0, double Xmax, int n, int Nmax, int Nmin) {
        int dif = Nmax-Nmin+1;
        double[] Errors = new double[dif];
        for(int i=0; i<(dif); i++, Nmin++) {
            EulerSol euler = new EulerSol();
            double[] valY1 = euler.solve(x0,y0,Xmax,Nmin);
            ExactSol ex = new ExactSol();
            double[] valY2 = ex.solve(x0, y0, Xmax, Nmin);
            double maxE = -9999;
            for (int j = 0; j < Nmin; j++) {
                if (Math.abs(valY1[j] - valY2[j]) > maxE) {
                    maxE = Math.abs(valY1[j] - valY2[j]);
                }
            }
            Errors[i]=maxE;
        }
        return Errors;
    }
    public double[] solveImprovedEuler(double x0, double y0, double Xmax, int n, int Nmax, int Nmin) {
        int dif = Nmax-Nmin+1;
        double[] Errors = new double[dif];
        for(int i=0; i<(dif); i++, Nmin++) {
            ImprovedEulerSol ImpEuler = new ImprovedEulerSol();
            double[] valY1 = ImpEuler.solve(x0, y0, Xmax, Nmin);
            ExactSol ex = new ExactSol();
            double[] valY2 = ex.solve(x0, y0, Xmax, Nmin);
            double maxE = -9999;
            for (int j = 0; j < Nmin; j++) {
                if (Math.abs(valY1[j] - valY2[j]) > maxE) {
                    maxE = Math.abs(valY1[j] - valY2[j]);
                }
            }
            Errors[i]=maxE;
        }
        return Errors;
    }
    public double[] solveRungeKutta(double x0, double y0, double Xmax, int n, int Nmax, int Nmin) {
        int dif = Nmax-Nmin+1;
        double[] Errors = new double[dif];
        for(int i=0; i<(dif); i++, Nmin++) {
            RungeKuttaSol RungeK = new RungeKuttaSol();
            double[] valY1 = RungeK.solve(x0, y0, Xmax, Nmin);
            ExactSol ex = new ExactSol();
            double[] valY2 = ex.solve(x0, y0, Xmax, Nmin);
            double maxE = -9999;
            for (int j = 0; j < Nmin; j++) {
                if (Math.abs(valY1[j] - valY2[j]) > maxE) {
                    maxE = Math.abs(valY1[j] - valY2[j]);
                }
            }
            Errors[i]=maxE;
        }
        return Errors;
    }
}
