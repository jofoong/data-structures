
/**
 * This class is bananas! (B-a-n-a...) now it's stuck in my head
 * 
 * @author Jolene
 *
 */

public class Banana {
	private String colour;
	private int shelfLife;
	private int hoursToRipen; //because we all know how bananas ripen
	
	public Banana() {
		this.hoursToRipen = (int)Math.random() * 10;
		this.colour = "yellow";
		this.shelfLife = (int)Math.random() * 10;
	}
	
	public void setLife(int numDays) {
		if (numDays > shelfLife) {
			System.out.println("Can't extend a banana's life, sorry."
					+ " Eat it now.");
		} else {
			this.shelfLife = numDays;
		}	
	}
	
	public void setHours(int hours) {
		if (hours > this.hoursToRipen) {
			System.out.println("Can't extend a banana's life, sorry."
					+ " Eat it now.");
		} else {
			this.hoursToRipen = hours;
		}
	}
	
	public void setColour(String colour) {
		this.colour = colour;
		System.out.println("Yes, I am the type of person who wants to"
				+ " micromanage a banana's colour.");
	}
	
	public int getHoursToRipen() {
		return hoursToRipen;
	}
	
	public int getShelfLife() {
		return shelfLife;
	}
}
