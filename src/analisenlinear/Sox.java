/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analisenlinear;

import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author jc
 */
public class Sox {
    String caminhoWave;
    String caminhoSox;
    String caminhoDat;
    String caminhoDatCortado;
    Execucao exeSox;
    Utils util;
    public Sox(String caminhoWave, boolean isWindows)
    {
        this.caminhoWave = caminhoWave;
        this.caminhoWave = this.caminhoWave.replace("\"", " ");
        try {
            util = new Utils();
            String binPath = util.getPathExe() + "/bin";
            if(isWindows)
                this.caminhoSox = binPath + "/sox.exe";
            else
                this.caminhoSox = "sox";
            caminhoDat = binPath + "/amostras.dat";
            caminhoDatCortado = binPath + "/trecho.dat";
            exeSox = new Execucao();
        } catch (Exception ex) {
            new Utils().addErrorLog(ex.getMessage());
        }
    }
    
    public boolean exe(Double ini, Double fim, boolean isTodo)
    {
        if(isTodo){
            exeSox.execute(caminhoSox + " " + caminhoWave 
                    + " " + caminhoDat, true);
            File f = new File(caminhoDat);
            if (f.exists()) {
                return true;
            }
            return false;
        }
        else {
            exeSox.execute(caminhoSox + " " + caminhoWave + " "
                    + caminhoDatCortado + " trim " + ini + " " + fim, true);
            File f = new File(caminhoDatCortado);
            if (f.exists()) {
                return true;
            }
            return false;
        }
    }
}
