
package projet_cps;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

 
public class Audio{
      private URL u1;
    private AudioClip s1;
    private String src = "";
    public Audio(String sr) {
        src = sr ;
        
    }
    
/** lance le son */
    public void explosion() {
        u1 = Audio.class.getResource("/SON/"+src);
        s1 = Applet.newAudioClip(u1);
        s1.play();
    }
/** lance le son loop*/
    public void explosionloop() {
         u1 = Audio.class.getResource("/SON/"+src);
        s1 = Applet.newAudioClip(u1);
        s1.loop();
    }
/** arrete le son */
    public void relanceExplosion(String src) {
        s1.stop();
        this.src = src;
        explosion();
    }
/** arrete le son */
    public void relanceExplosionloop(String src) {
        s1.stop();
        this.src = src;
        explosionloop();
    }
/** arrete le son */
    public void stop() {
        s1.stop();
    }
}