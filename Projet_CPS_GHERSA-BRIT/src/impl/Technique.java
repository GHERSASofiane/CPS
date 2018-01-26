package impl;

import service.HitboxService;

public class Technique {

	public int damage;  //  le cout de la technique 
	public int hstun;  //  duree de perdre le controle de l'adverse 
	public int bstun;  //  duree de protege 
	public int sframe = 0;  //  duree de preparation 0
	public int hframe;  //  duree de tech 
	public int rframe = 0;  //  duree de recup de tech 0 
	public HitboxService hitbox;  //  les dim de la hitbox
        

	
	public void init(int d, int hstun, int bstun, int hframe, HitboxService hitbox) {
		this.damage = d;
		this.hstun = hstun;
		this.bstun = bstun;
		this.hframe = hframe;
		this.hitbox = hitbox;
	}
	


}
