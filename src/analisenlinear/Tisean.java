/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analisenlinear;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Runtime;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sun.rmi.runtime.Log;

/**
 *
 * @author jc
 * o usuário tem a opção de dar um tal e/ou n, caso
 * não dê o programa calculará
 */
public class Tisean {
    int tal; 
    int n;
    String amostra;
    String binPath;
    Utils util;
    String cmd;
   
    public Tisean(int tal, int n)
    {
        util = new Utils();
        binPath = util.getPathExe() + "/bin";
        this.amostra = binPath + "/trecho.dat";
        if(tal == -1)
            mutual();
        else
            this.tal = tal;
        if(n == -1)
            falseNearest();
        else
            this.n = n;
        delay();
    }
    
    public int getN(){
        return n;
    }
    
    public int getTal()
    {
        return tal;
    }
        
    private void mutual()
    {
        if(util.isWindows())
            cmd = binPath + "/mutual.exe " + amostra + " -b 16 -c 2";// " -o " + cam + "/mut.txt";
        else
            cmd = binPath + "/mutual " + amostra + " -b 16 -c 2";
        tal = setTal(execute(cmd, false));
    }
    
    private void falseNearest()
    {
        if(util.isWindows())
            cmd = binPath + "/false_nearest.exe " + amostra + " -c 2 -d " + tal + " -M 1,5";
        else
            cmd = binPath + "/false_nearest " + amostra + " -c 2 -d " + tal + " -M 1,5";
        try{
            n = getN(execute(cmd, false));
        }catch(Exception e){ n = 3;}
    }
    
    
    
    private int setTal(ArrayList<String> mutTxt)
    {
        int tal = -1;
        Double valorA, valorB;
        int i = 0;
        valorA = Double.parseDouble(mutTxt.get(i++).split(" ")[1]);
        valorB = Double.parseDouble(mutTxt.get(i).split(" ")[1]);
        while (i < mutTxt.size() - 2 && valorA >= valorB) {
            valorA = Double.parseDouble(mutTxt.get(i++).split(" ")[1]);
            valorB = Double.parseDouble(mutTxt.get(i).split(" ")[1]);
        }
        if(i < mutTxt.size() - 2){
            tal = Integer.parseInt(mutTxt.get(i - 1).split(" ")[0]);
        }
        else
            tal = Integer.parseInt(mutTxt.get(i).split(" ")[0]);
        return tal;
    }
    
    private int getN(ArrayList<String> mutTxt)
    {
        int n = -1;
        Double menorValor = Double.parseDouble(mutTxt.get(0).split(" ")[1]);
        for(int i = 1; i < mutTxt.size() - 1; i++){
            Double valorAtual = Double.parseDouble(mutTxt.get(i).split(" ")[1]);
            if(valorAtual == 0)
                return n + 1;
            if(Math.abs(valorAtual) < Math.abs(menorValor)){
                menorValor = valorAtual;
                n = i + 1; //já pega result do near
            }
        }
        return n;
    }
    
    private void delay()
    {
        if(util.isWindows())
            cmd = binPath + "/delay.exe " + amostra + " -d " + tal + 
                " -m " + n + " -o " + binPath + "/delay.txt" + " -c 2";
        else
            cmd = binPath + "/delay " + amostra + " -d " + tal + 
                " -m " + n + " -o " + binPath + "/delay.txt" + " -c 2";
        execute(cmd, true);
    }
    
    private ArrayList<String> execute(String cmd, boolean isSaidaArq)
    {
        Process p;
        ArrayList<String> result = new ArrayList<String>();
        try {
            p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            if (!isSaidaArq) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        p.getInputStream()));
                String line = reader.readLine();
                result.add(line);
                //sb.append(line);
                while (line != null) {
                    line = reader.readLine();
                    result.add(line);
                }
            }
            p.destroy();
        }
        catch (Exception e) {
            util.addErrorLog(e.getMessage());
        }
        return result;
    }
}
