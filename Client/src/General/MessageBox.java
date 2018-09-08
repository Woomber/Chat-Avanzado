/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class MessageBox {
    /**
     * muestra un mensaje en la pantalla
     * @param title 
     * @param content 
     */
    public static void Show(String title, String content){
        JOptionPane.showMessageDialog(
                     null, 
                     content, 
                     title,
                     JOptionPane.INFORMATION_MESSAGE);
    }
}
