/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Delegates;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author PC
 */
public interface TransmitterAction {
    public void Invoke(Socket socket, PrintWriter pw, BufferedReader read);
}
