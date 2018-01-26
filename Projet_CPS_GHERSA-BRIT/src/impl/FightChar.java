/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import java.awt.Label;
import javax.swing.JLabel;
import static projet_cps.Login.Bar_J_1;
import projet_cps.Time;
import service.HitboxService;

/**
 *
 * @author ghersa_sofiane
 */
public class FightChar extends Character{
    
public static boolean isBlocking = false;
public boolean isBlockstunned = false;
public boolean isHitstunned = false;
public boolean isTeching = false;
public boolean techFrame = false;
public boolean techHasAlreadyHit = false;
public String name;
public JLabel My_Label ;
public FightChar autre ;

public FightChar(int l, int s,boolean f, HitboxService hb , JLabel Bar_J_1 , String nm){
    super.init(l, s, f, hb);
    My_Label = Bar_J_1;
    name = nm;
}
  
public void startTech(Technique Tech , Engine eng , JLabel lab){
    if(Is_libre()){
        set_Occup();//*** bloque les deux player
        
        /*** recuperer l'adversaire autre.hitbox.collidesWith(Tech.hitbox)  */
        if (eng.getChar(1).equals(this)) {
            autre = eng.getChar(2);
        }else{
            autre = eng.getChar(1);
        }
        /****  faire le traitement sur life  */
        if (is_c(autre.My_Label, Tech.hitbox)) {
            autre.life = autre.life - Tech.damage ;
            if (autre.isDead()) {
                 lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Player/x.gif")));
            }
              
        }
        new Time(this, Tech.hstun);
   }  
}
public boolean is_c(JLabel A , HitboxService B){
    boolean tmp = false;
    if ( (A.getX()>=B.getPositionX()) && (A.getX()<=B.getPositionX()+B.getWidth()) ) {
       tmp = true;  
    }else if((B.getPositionX()>= A.getX()) && (B.getPositionX()<= A.getX()+A.getWidth()) ){
       tmp = true;   
    }
   
    return tmp;
}
public boolean Is_libre(){
    boolean is_l = true;
    
    if (isBlocking) { is_l = false; }
    if (isBlockstunned) { is_l = false; }
    if (isHitstunned) { is_l = false; }
    if (isTeching) { is_l = false; }
    if (techFrame) { is_l = false; }
    
    return is_l;
}

public void set_Occup(){
    
    this.isBlocking  = true;
    autre.isBlocking = true;
}

public void set_Liber(){
    
    this.isBlocking  = false;
    autre.isBlocking = false;
    
}
public void End_Time(){
    
     My_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Player/"+name+"/Deb.gif")));
     My_Label.setBounds(My_Label.getX(), My_Label.getY(), 120, My_Label.getHeight());
     this.set_Liber();
}

@Override
	public void moveUp() { }
	@Override
	public void moveDown() { }

	@Override
	public void moveLeft() { 
            super.moveLeft() ;
	}
        
	
	@Override
	public void moveUpLeft() {  }
	@Override
	public void moveDownLeft() { }

	@Override
	public void moveRight() { super.moveRight()  ; }
        
	@Override
	public void moveUpRight() { }
	@Override
	public void moveDownRight() { }


	/** on change de face **/
	@Override
	public void switchSide() { }

	@Override
	public void step(Commande com) { super.step(com)  ; }
        
	public void step(Commande com, FightChar adv , Engine eng) { 
            
            switch(com){
                case LEFT:// a gauche
                   
                    if ((this.getPositionX() > adv.getPositionX())&&(this.getPositionX()-this.getSpeed()) <= (adv.getPositionX()+adv.getCharBox().getWidth())) {
                        this.PositionX = adv.getPositionX()+adv.getCharBox().getWidth()+5 ;
                    }else if (this.getPositionX()-this.getSpeed() <= eng.PositionX) {
                        this.PositionX = eng.PositionX+5; 
                    }else{
                        this.moveLeft();
                    }
			break;
                        
                case RIGHT://  a droite 
			
                    if ((this.getPositionX() < adv.getPositionX())&&(this.getPositionX()+this.getSpeed()+this.getCharBox().getWidth()) >= (adv.getPositionX())) {
                        this.PositionX = adv.getPositionX()-5- this.getCharBox().getWidth() ;
                    }else if (this.getPositionX()+this.getCharBox().getWidth()+this.getSpeed() >= eng.PositionX + eng.getWidth()) {
                        this.PositionX = eng.PositionX + eng.getWidth() - 5 - this.getCharBox().getWidth(); 
                    }else{
                        this.moveRight();
                    }
			break;
                        
                default:
			System.out.println("Mauvaise commande"); 
			break;
            }
          
        }

	/** Observateurs **/
	@Override
	public int getPositionX() { return super.getPositionX()  ; }

	@Override
	public int getPositionY() { return super.getPositionY()  ; }


	@Override
	public HitboxService getCharBox() { return super.getCharBox()  ; }

	@Override
	public int getLife() { return super.getLife()  ; }

	@Override
	public int getSpeed() { return super.getSpeed()  ; }


	@Override
	public boolean isDead() { return super.isDead()  ; }

}
