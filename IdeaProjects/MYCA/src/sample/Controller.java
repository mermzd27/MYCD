package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import solutions.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button btn;

    @FXML
    private CheckBox cExact;

    @FXML
    private CheckBox cRungeKutta;

    @FXML
    private CheckBox cImprovedEuler;

    @FXML
    private CheckBox cEuler;

    @FXML
    private LineChart<?, ?> myGraph;

    @FXML
    private NumberAxis myX;

    @FXML
    private NumberAxis myY;

    @FXML
    private LineChart<?, ?> myGraph1;

    @FXML
    private NumberAxis myX1;

    @FXML
    private NumberAxis myY1;

    @FXML
    private LineChart<?, ?> myGraph2;

    @FXML
    private NumberAxis myX2;

    @FXML
    private NumberAxis myY2;

    @FXML
    private TextField tx0;

    @FXML
    private TextField ty0;

    @FXML
    private TextField txMax;

    @FXML
    private TextField tN;

    @FXML
    private TextField tNmax;

    @FXML
    private TextField tNmin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cExact.setSelected(true);
        cEuler.setSelected(true);
        cImprovedEuler.setSelected(true);
        cRungeKutta.setSelected(true);
        double x0=2; tx0.setText(""+x0);
        double y0=0; ty0.setText(""+y0);
        double Xmax=12; txMax.setText(""+Xmax);
        int n = 10; tN.setText(""+n);
        int Nmax = 20; tNmax.setText(""+Nmax);
        int Nmin = 10; tNmin.setText(""+Nmin);
        double h = (Xmax-x0)/n;
        double[] x = new double[n+1];
        for (int i = 0; i<=n; i++) {
            if(i==0) x[i]=x0;
            else x[i]=x[i-1]+h;
        }
        ExactSol exactSol = new ExactSol();
        double yExact[] = exactSol.solve(x0,y0,Xmax,n);
        EulerSol eulerSol = new EulerSol();
        double yEuler[] = eulerSol.solve(x0,y0,Xmax,n);
        ImprovedEulerSol impeulerSol = new ImprovedEulerSol();
        double yImprovedEuler[] = impeulerSol.solve(x0,y0,Xmax,n);
        RungeKuttaSol rungeKuttaSol = new RungeKuttaSol();
        double yRungeKutta[] = rungeKuttaSol.solve(x0,y0,Xmax,n);
        LocalError localError = new LocalError();
        double[] error = localError.solve(yEuler,yExact);
        LocalError localError1 = new LocalError();
        double[] error1 = localError1.solve(yImprovedEuler,yExact);
        LocalError localError2 = new LocalError();
        double[] error2 = localError2.solve(yRungeKutta,yExact);
        GlobalError globalError = new GlobalError();
        double[] gError = globalError.solveEuler(x0,y0,Xmax,n,Nmax,Nmin);
        GlobalError globalError1 = new GlobalError();
        double[] gError1 = globalError1.solveImprovedEuler(x0,y0,Xmax,n,Nmax,Nmin);
        GlobalError globalError2 = new GlobalError();
        double[] gError2 = globalError2.solveRungeKutta(x0,y0,Xmax,n,Nmax,Nmin);
        XYChart.Series ExactXY = new XYChart.Series();
        XYChart.Series EulerXY = new XYChart.Series();
        XYChart.Series EulerLocal = new XYChart.Series();
        XYChart.Series EulerGlobal = new XYChart.Series();
        XYChart.Series ImprovedXY = new XYChart.Series();
        XYChart.Series ImprovedLocal = new XYChart.Series();
        XYChart.Series ImprovedGlobal = new XYChart.Series();
        XYChart.Series RungeKuttaXY = new XYChart.Series();
        XYChart.Series RungeKuttaLocal = new XYChart.Series();
        XYChart.Series RungeKuttaGlobal = new XYChart.Series();
        ExactXY.setName("Exact");
        EulerXY.setName("Euler");
        EulerLocal.setName("Euler");
        EulerGlobal.setName("Euler");
        ImprovedXY.setName("Improved Euler");
        ImprovedLocal.setName("Improved Euler");
        ImprovedGlobal.setName("Improved Euler");
        RungeKuttaXY.setName("Runge-Kutta");
        RungeKuttaLocal.setName("Runge-Kutta");
        RungeKuttaGlobal.setName("Runge-Kutta");
        for(int i = 0; i<=n; i++) {
            ExactXY.getData().add(new XYChart.Data(x[i],yExact[i]));
            EulerXY.getData().add(new XYChart.Data(x[i],yEuler[i]));
            ImprovedXY.getData().add(new XYChart.Data(x[i],yImprovedEuler[i]));
            RungeKuttaXY.getData().add(new XYChart.Data(x[i],yRungeKutta[i]));
            EulerLocal.getData().add(new XYChart.Data(x[i],error[i]));
            ImprovedLocal.getData().add(new XYChart.Data(x[i],error1[i]));
            RungeKuttaLocal.getData().add(new XYChart.Data(x[i],error2[i]));
        }
        for(int j = 0; j<Nmax-Nmin+1;j++) {
            EulerGlobal.getData().add(new XYChart.Data(j+Nmin,gError[j]));
            ImprovedGlobal.getData().add(new XYChart.Data(j+Nmin,gError1[j]));
            RungeKuttaGlobal.getData().add(new XYChart.Data(j+Nmin,gError2[j]));
        }
        myGraph.getData().addAll(ExactXY,EulerXY,ImprovedXY,RungeKuttaXY);
        myGraph1.getData().addAll(EulerLocal,ImprovedLocal,RungeKuttaLocal);
        myGraph2.getData().addAll(EulerGlobal,ImprovedGlobal,RungeKuttaGlobal);
        myGraph2.setCreateSymbols(false);
        myGraph1.setCreateSymbols(false);
        myGraph.setCreateSymbols(false);

        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn.setText("Restart");
                myGraph.getData().clear();
                myGraph1.getData().clear();
                myGraph2.getData().clear();
                double x01=Double.parseDouble(tx0.getText());
                double y01=Double.parseDouble(ty0.getText());
                double Xmax1=Double.parseDouble(txMax.getText());
                int n1 = Integer.parseInt(tN.getText());
                int Nmax1 = Integer.parseInt(tNmax.getText());
                int Nmin1 = Integer.parseInt(tNmin.getText());
                double h1 = (Xmax1-x01)/n1;
                double[] x1 = new double[n1+1];
                for (int i = 0; i<=n1; i++) {
                    if(i==0) x1[i]=x01;
                    else x1[i]=x1[i-1]+h1;
                };
                double yExact1[] = exactSol.solve(x01,y01,Xmax1,n1);
                double yEuler1[] = eulerSol.solve(x01,y01,Xmax1,n1);
                double yImprovedEuler1[] = impeulerSol.solve(x01,y01,Xmax1,n1);
                double yRungeKutta1[] = rungeKuttaSol.solve(x01,y01,Xmax1,n1);
                double[] errors = localError.solve(yEuler1,yExact1);
                double[] error1s = localError1.solve(yImprovedEuler1,yExact1);
                double[] error2s = localError2.solve(yRungeKutta1,yExact1);
                double[] gErrors = globalError.solveEuler(x01,y01,Xmax1,n1,Nmax1,Nmin1);
                double[] gError1s = globalError1.solveImprovedEuler(x01,y01,Xmax1,n1,Nmax1,Nmin1);
                double[] gError2s = globalError2.solveRungeKutta(x01,y01,Xmax1,n1,Nmax1,Nmin1);
                XYChart.Series ExactXY1 = new XYChart.Series();
                XYChart.Series EulerXY1 = new XYChart.Series();
                XYChart.Series EulerLocal1 = new XYChart.Series();
                XYChart.Series EulerGlobal1 = new XYChart.Series();
                XYChart.Series ImprovedXY1 = new XYChart.Series();
                XYChart.Series ImprovedLocal1 = new XYChart.Series();
                XYChart.Series ImprovedGlobal1 = new XYChart.Series();
                XYChart.Series RungeKuttaXY1 = new XYChart.Series();
                XYChart.Series RungeKuttaLocal1 = new XYChart.Series();
                XYChart.Series RungeKuttaGlobal1 = new XYChart.Series();
                ExactXY1.setName("Exact");
                EulerXY1.setName("Euler");
                EulerLocal1.setName("Euler");
                EulerGlobal1.setName("Euler");
                ImprovedXY1.setName("Improved Euler");
                ImprovedLocal1.setName("Improved Euler");
                ImprovedGlobal1.setName("Improved Euler");
                RungeKuttaXY1.setName("Runge-Kutta");
                RungeKuttaLocal1.setName("Runge-Kutta");
                RungeKuttaGlobal1.setName("Runge-Kutta");
                for(int i = 0; i<=n1; i++) {
                    ExactXY1.getData().add(new XYChart.Data(x1[i],yExact1[i]));
                    EulerXY1.getData().add(new XYChart.Data(x1[i],yEuler1[i]));
                    ImprovedXY1.getData().add(new XYChart.Data(x1[i],yImprovedEuler1[i]));
                    RungeKuttaXY1.getData().add(new XYChart.Data(x1[i],yRungeKutta1[i]));
                    EulerLocal1.getData().add(new XYChart.Data(x1[i],errors[i]));
                    ImprovedLocal1.getData().add(new XYChart.Data(x1[i],error1s[i]));
                    RungeKuttaLocal1.getData().add(new XYChart.Data(x1[i],error2s[i]));
                }
                for(int j = 0; j<Nmax1-Nmin1+1;j++) {
                    EulerGlobal1.getData().add(new XYChart.Data(j+Nmin1,gErrors[j]));
                    ImprovedGlobal1.getData().add(new XYChart.Data(j+Nmin1,gError1s[j]));
                    RungeKuttaGlobal1.getData().add(new XYChart.Data(j+Nmin1,gError2s[j]));
                }
                if(cExact.isSelected()) {
                    myGraph.getData().add(ExactXY1);
                }
                if(cEuler.isSelected()) {
                    myGraph.getData().add(EulerXY1);
                    myGraph1.getData().add(EulerLocal1);
                    myGraph2.getData().add(EulerGlobal1);
                }
                if(cImprovedEuler.isSelected()) {
                    myGraph.getData().add(ImprovedXY1);
                    myGraph1.getData().add(ImprovedLocal1);
                    myGraph2.getData().add(ImprovedGlobal1);
                }
                if(cRungeKutta.isSelected()) {
                    myGraph.getData().add(RungeKuttaXY1);
                    myGraph1.getData().add(RungeKuttaLocal1);
                    myGraph2.getData().add(RungeKuttaGlobal1);
                }
            }
        });
    }

}