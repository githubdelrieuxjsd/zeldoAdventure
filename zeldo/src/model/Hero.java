package model;

import pkinterface.Life;

public class Hero extends Unit implements Move, Life {
	
	/**
	 * A hero must be alone and defined with Cordonnee 
	 */

	public Hero(Coordonnee c) {
		this.setNom("Hero");
		this.setImageURL("hyrule/link/beat/D1.png");
		this.setCoordonnee(c);
		this.setLifeMax(5);
		this.setLife(5);
		this.setDirection(new Direction("left"));
	}
	/**
	 * Hero disapear from his initial case
	 * Set hero on the next case
	 */
	public void move(Board board) {
		Case nextCase = board.getCaseDirection(this);
		board.getBoard()[this.getCoordonnee().getX()][this.getCoordonnee().getY()].setUnit(new UnitVoid());
		this.setCoordonnee(nextCase.getCoordonnee());
		board.getBoard()[nextCase.getCoordonnee().getX()][nextCase.getCoordonnee().getY()].setUnit(this);
	}
	
	/**
	 *Attacking is done in front of the hero
	 */
	public void attack(Board board) {
		Case caseFront = board.getCaseDirection(this);
		if (caseFront.getUnit().isMonster()) {
			((Monster) caseFront.getUnit()).loseLife(1);
		}

	}

	public String toString() {
		return "Le Hero est positionne en (x,y)=("+this.getCoordonnee().getX()+","+this.getCoordonnee().getY()+"), \n et sa vie est à "+this.getLife() ;	
	}

	@Override
		public void gainLife(int nbVie) {
		if(nbVie-this.getLifeMax() > 0) {

			this.setLife(this.getLifeMax());
		} else {
			this.setLife(this.getLife() + nbVie);
		}
	}

	@Override

	public void loseLife(int damage) {
		this.setLife(this.getLife()-damage);
		if(this.getLife()<=0) {
			this.setLife(this.getLifeMax());
		}

	}

	public String getImageURL() {
		return "hyrule/link/beat/" + this.getDirection().getDirection().substring(0, 1).toUpperCase() + "1.png";

	}


}
