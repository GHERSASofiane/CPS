/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_cps;

import javax.swing.JLabel;

/**
 *
 * @author ghersa_sofiane
 */
public class Time_g  implements Runnable{
    private Thread my;
    int temps ;
    Gest_Commande Player;
    JLabel Bar_J_1 ;
    String player_1;
    String action;
    
     public Time_g(Gest_Commande Player ,  int temps , JLabel Bar_J_1 , String player_1, String action){
        this.Player = Player;
        this.temps = temps;
        this.Bar_J_1  =  Bar_J_1;
        this.player_1  =  player_1;
        this.action =   action;
        
        my = new Thread(this);
	my.start();
    } 
      
    @Override
    public void run() {
      for (int i = 0; i < temps; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) { }
        }
       Player.End_Time(Bar_J_1,player_1 , action);
    }
    
}
