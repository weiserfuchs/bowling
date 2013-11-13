package gui;

public class Wurf {
	private boolean strike;
	private boolean spare;
	private int summe;
	
	public boolean isStrike() {
		return strike;
	}

	public boolean isSpare() {
		return spare;
	}

	public int getSumme() {
		return summe;
	}

	public Wurf(boolean strike, boolean spare, int summe) {
		this.strike = strike;
		this.spare = spare;
		this.summe = summe;
	}
	
}
