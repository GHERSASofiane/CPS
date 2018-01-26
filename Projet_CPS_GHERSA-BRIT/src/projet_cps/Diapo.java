/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_cps;

/**
 *
 * @author ghersa_sofiane
 */
public class Diapo implements Runnable {

    private Thread exec ;
    private javax.swing.JLabel label  ;
    private String  src ;
    private String  image ;
            
    public Diapo( javax.swing.JLabel lab , String src , String img){
        label = lab;
        this.src = src;
        image = img;
	exec = new Thread(this);
	exec.start();
    }
    
    @Override
    public void run() {
        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Player/"+src+"/"+image)));
         try {Thread.sleep(3000);} catch (InterruptedException ex) {}
         label.setBounds((int) (label.getX()+20), 330, 120, 130);
        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Player/"+src+"/Deb.gif")));
          
    }
    
}
