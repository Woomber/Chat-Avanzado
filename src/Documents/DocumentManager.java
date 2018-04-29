/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Documents;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class DocumentManager {
    private static final String username = System.getProperty("user.name");
    private static final String rutaContactos = "C:\\Users\\" + username + "\\Documents\\Coco Chat\\Messages\\Contactos\\";
    private static final String rutaGrupos = "C:\\Users\\" + username + "\\Documents\\Coco Chat\\Messages\\Grupos\\";
    
    public static boolean SaveMessage(String conversationName, String remitent, String message,  boolean isGroup){        
        boolean EverythingIsFine = true;
        FileWriter writer = null;
        BufferedWriter bw;
        message = message.replace("\n", " ").replace("\r", " ");
        try {
            File file = new File(
                    (isGroup? rutaGrupos : rutaContactos) +
                            conversationName + ".txt");
            file.getParentFile().mkdirs();
            writer = new FileWriter(file,true);
            bw = new BufferedWriter(writer);
            bw.write(
                    "[" + remitent.trim() + "] |==> " + message.trim() + "\r\n"
            );
            bw.close();
        } catch (IOException ex) {
            EverythingIsFine = false;
            System.out.println(ex.getMessage());
        } finally {
            try {
                writer.close();
            } catch (IOException | NullPointerException ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }
        return EverythingIsFine;
    }
    
    public static ArrayList<String> GetLastNumberMessages(String conversationName, int number, boolean isGroup){
        ArrayList<String> Messages = new ArrayList<String>();
        String line;
        FileReader reader = null;
        BufferedReader br;
        try {
            File file = new File(
                    (isGroup? rutaGrupos : rutaContactos) +
                            conversationName + ".txt");
            file.getParentFile().mkdirs();
            reader = new FileReader(file);
            br = new BufferedReader(reader);
            while((line = br.readLine()) != null) Messages.add(line);
            br.close();
        } catch (IOException ex) {
            Messages = null;
            System.out.println(ex.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException | NullPointerException ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }
        if(Messages.size() == 0) Messages = null;
        else if(Messages.size() > number) Messages = new ArrayList
                (Messages.subList(
                        Messages.size() - number, Messages.size()
                ));
        return Messages;
    }
    
    
}
