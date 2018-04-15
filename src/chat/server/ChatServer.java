/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server;

import chat.json.JsonParser;
import chat.mensajes.*;
import chat.mensajes.models.*;

/**
 *
 * @author Yael Arturo Chavoya Andalón 14300094
 */
public class ChatServer {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        Mensaje mensaje = JsonParser.JsonToMensaje(
         JsonParser.MensajeToJson(new MensajeLogin("yael", "123"))
       );   
    }
    
}
