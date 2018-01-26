/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_cps;

import impl.FightChar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ghersa_sofiane
 */
public class Time implements Runnable{

    private Thread my;
    int temps ;
    FightChar Player;
    
    public Time(FightChar Player ,  int temps){
        this.Player = Player;
        this.temps = temps;
        
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
       Player.End_Time();
    }
    
}
