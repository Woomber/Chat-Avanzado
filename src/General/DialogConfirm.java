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
public class DialogConfirm {

    public static boolean Show(String title, String message) {
        int i = JOptionPane.showConfirmDialog(null, message,
                title, JOptionPane.OK_CANCEL_OPTION);
        
        return (i == JOptionPane.OK_OPTION);
    }
}
