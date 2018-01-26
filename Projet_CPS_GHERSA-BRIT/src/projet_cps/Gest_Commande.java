/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_cps;

import impl.FightChar;
import javax.swing.JLabel;
import static projet_cps.Login.My_Engine;
import static projet_cps.Login.My_FightChar1;
import static projet_cps.Login.My_FightChar2;

/**
 *
 * @author ghersa_sofiane
 */
public class Gest_Commande {

    static void Move_Left(JLabel Bar_J_1, FightChar My_app, FightChar aut) {
        
        My_app.step(impl.Commande.LEFT , aut, My_Engine);
        Bar_J_1.setBounds(My_app.PositionX, My_app.PositionY, My_app.getCharBox().getWidth(), My_app.getCharBox().getHeight());

    }

    static void Move_Rignt(JLabel Bar_J_1, FightChar My_app, FightChar aut) {
      
        My_app.step(impl.Commande.RIGHT , aut , My_Engine);
        Bar_J_1.setBounds(My_app.PositionX, My_app.PositionY, My_app.getCharBox().getWidth(), My_app.getCharBox().getHeight());

    }

    public void top(JLabel Bar_J_1 , String player_1 , String action){
        if (action.equals("top")) {
          Bar_J_1.setBounds(Bar_J_1.getX(), Bar_J_1.getY()-40, Bar_J_1.getWidth(), Bar_J_1.getHeight()+40);
          Bar_J_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Player/"+player_1+"/Top.gif")));
          new Time_g(this, 2000 , Bar_J_1 , player_1 ,  action);
        }else
          if (action.equals("bottom")) {
              
          Bar_J_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Player/"+player_1+"/Low.gif")));
          new Time_g(this, 1000 , Bar_J_1 , player_1 ,  action);
        }
                   
    }
    
    public void End_Time( JLabel Bar_J_1 , String player_1, String action){
        if (action.equals("top")) {
           Bar_J_1.setBounds(Bar_J_1.getX(), Bar_J_1.getY()+40, Bar_J_1.getWidth(), Bar_J_1.getHeight()-40); 
        }
        
         
         Bar_J_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Player/"+player_1+"/Deb.gif")));
         
    }
    
 public void affich_gamover(JLabel Bar_J_1){
        
}   
    
    
}


