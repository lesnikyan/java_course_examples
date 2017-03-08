/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplestwebserver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author less
 */
public class SimplestWebServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Server server = new Server(100);
        try {
            server.start();
        } catch (IOException ex) {
            System.out.println("server error: " + ex.getMessage());
        }
    }
    
}
