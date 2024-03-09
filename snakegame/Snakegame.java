/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package snakegame;

import javax.swing.*;


public class Snakegame extends JFrame {

    Snakegame(){
        // to put heading or log on the top of frame // super should be on the top on any constructor or any java code
        super("SnackGame");
        add(new Board()); // now this Board constructor becomes panel if we use add() and put the constructor of that class inside the add function

        
        
        pack(); // pack function refresh the frame that we have newly updated and load that new upgraded frame
        //set our location (x) 100 from left to right and  (y) 100 from top to bottom on screen
//        setLocation(100, 100);
         
                
        // function for put in center of the screen
        setLocationRelativeTo(null);
        //to display the interface in the screen
        
        setResizable(false); // we can set the size ot the frame of our game board and here we are fixing the board size


    }
    public static void main(String[] args) {
       new Snakegame().setVisible(true);
        

    }
}
