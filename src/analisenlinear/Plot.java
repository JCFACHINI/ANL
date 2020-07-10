/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analisenlinear;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 *
 * @author jc
 */
public class Plot {
    String cam;
    Utils util;
    String camGnuPlot;
    public Plot(boolean isWindows) throws IOException {
        util = new Utils();
        if(isWindows)
            camGnuPlot = "C:\\Program Files (x86)\\gp420win32\\gnuplot\\bin\\pgnuplot.exe";
        else
            camGnuPlot = "gnuplot -persist";
        cam = util.getPathExe() + "/bin";
    }

    public void exe(String camAmostra, String tipoGrafico, int tal, int n) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(camGnuPlot);
            java.io.OutputStream opStream = proc.getOutputStream();
            PrintWriter gp = new PrintWriter(new BufferedWriter(new OutputStreamWriter(opStream)));
            gp.println("set datafile commentschars ';'");
            gp.println("set ylabel 'valor amostra'");
            gp.println("set xlabel 'tempo (s)'");
            if(tipoGrafico.equalsIgnoreCase("2D")){
                gp.println("plot '" + cam + "/amostras.dat'" + " with lines lc rgbcolor '#a0a0b0' title 'Gráfico linear'");
            }
            else {
                gp.println("set multiplot layout 2,1");
                gp.println("plot '" + cam + "/trecho.dat'" + " with lines lc rgbcolor '#a0a0b0' title 'trecho amostra'");
                gp.println("set format y ''");
                gp.println("set format x ''");
                gp.println("set format z ''");
                gp.println("set ylabel 'x(n)'");
                gp.println("set xlabel 'x(n-"+tal+")'");
                gp.println("set zlabel 'x(n-"+tal*2+")'");
                if(n >= 3)
                    gp.println("splot '" + cam + "/delay.txt'" + " with lines lc rgbcolor '#000000' title 'Não linear'");
                else
                    gp.println("plot '" + cam + "/delay.txt'" + " with lines lc rgbcolor '#000000' title 'Não linear'");
                gp.println("unset multiplot");
            }
            gp.close();
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                proc.destroy();
            }
        } catch (Exception e) {
            util.addErrorLog(e.getMessage());
            e.printStackTrace();
        }
    }
}
