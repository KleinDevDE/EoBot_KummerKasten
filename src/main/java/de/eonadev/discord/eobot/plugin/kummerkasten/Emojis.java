package de.eonadev.discord.eobot.plugin.kummerkasten;

public enum Emojis {
	A(":one:"),
	B(":two:"),
	C(":three:"),
	D(":four:"),
	E(":five:"),
	F(":six:"),
	G(":seven:"),
	H(":eight:"),
	I(":nine:"),
	DETECTIVE(":detective:"),
	MAN(":man:"),
	X(":x:");
		
	String unicode;
	
	private Emojis(String unicode) {
		this.unicode = unicode;
	}
	
	public String getUnicode() {
		return unicode;
	}
}
