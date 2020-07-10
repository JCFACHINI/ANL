/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analisenlinear;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jc
 */
public class Utils {
    File arqLog = null;
    String SO;
    public Utils() {
        arqLog = new File(getPathExe() + "/logError.txt");
        SO = System.getProperty("os.name");
    }
    
    public boolean isWindows() {
        if(SO.contentEquals("windows"))
            return true;
        else
            return false;
    }
    
    public void addErrorLog(String erro){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(arqLog, true));
            out.write(Calendar.getInstance().getTime() + " ERRO: " + erro);
            out.newLine();
            out.close();
        } catch (IOException e) {
        }
    }
    
    public String getPathExe() 
    {
        String path;
        try{
            path = new File(".").getCanonicalPath();
        }catch(Exception e){
            path = "";
            addErrorLog(e.getMessage());
        }
        return path;
    }
    
      public String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }
}
