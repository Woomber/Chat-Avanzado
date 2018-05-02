/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Documents;

import General.MessageBox;
import Models.Usuario;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class DocumentManager {

    private static final String username = System.getProperty("user.name");
    private static final String rutaContactos = "Messages\\Contactos\\";
    private static final String rutaGrupos = "Messages\\Grupos\\";

    public static boolean SaveMessage(String conversationName, String remitent, String message, boolean isGroup) {
        boolean EverythingIsFine = true;
        FileWriter writer = null;
        BufferedWriter bw;
        message = message.replace("\n", " ").replace("\r", " ");
        try {
            File file = new File(
                    (isGroup ? rutaGrupos : rutaContactos)
                    + Usuario.emisor.getId_usuario() + ";" + conversationName + ".txt");
            file.getParentFile().mkdirs();
            writer = new FileWriter(file, true);
            bw = new BufferedWriter(writer);
            bw.write(
                    "[" + remitent.trim() + "] l==> " + message.trim() + "\r\n"
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

    public static ArrayList<String> GetLastNumberMessages(String conversationName, int number, boolean isGroup) {
        ArrayList<String> Messages = new ArrayList<String>();
        String line;
        FileReader reader = null;
        BufferedReader br = null;
        try {
            File file = new File(
                    (isGroup ? rutaGrupos : rutaContactos)
                    + Usuario.emisor.getId_usuario() + ";" + conversationName + ".txt");
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write("");
            bw.close();
            writer.close();
            reader = new FileReader(file);
            br = new BufferedReader(reader);
            while ((line = br.readLine()) != null) {
                Messages.add(line);
            }
            br.close();
        } catch (IOException ex) {
            Messages = null;
            MessageBox.Show("", ex.getMessage());
            System.out.println(ex.getMessage());

        } finally {
            try {
                reader.close();

            } catch (IOException | NullPointerException ex) {
                System.out.println(ex.getMessage());
                MessageBox.Show("", ex.getMessage());
                return null;
            }
        }
        if (Messages == null) {
            return null;
        }
        if (Messages.size() == 0) {
            Messages = null;
        } else if (Messages.size() > number) {
            Messages = new ArrayList(Messages.subList(
                    Messages.size() - number, Messages.size()
            ));
        }
        return Messages;
    }

    public static int GetNumberLines(String conversationName, boolean isGroup) {
        int count = 0;
        try {
            InputStream is = new BufferedInputStream(new FileInputStream((isGroup ? rutaGrupos : rutaContactos)
                    + Usuario.emisor.getId_usuario() + ";" + conversationName + ".txt"));
            byte[] c = new byte[1024];
            count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            is.close();
            return (count == 0 && !empty) ? 1 : count;
        } catch(Exception e){
        
        }finally {
           return count;
        }
    }

}
