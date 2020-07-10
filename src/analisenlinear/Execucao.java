/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analisenlinear;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author jc
 */
public class Execucao {
    public ArrayList<String> execute(String cmd, boolean isSaidaArq)
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
            e.printStackTrace();
        }
        return result;
    }
}
