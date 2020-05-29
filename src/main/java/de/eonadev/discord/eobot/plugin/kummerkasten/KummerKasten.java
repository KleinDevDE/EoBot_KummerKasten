package de.eonadev.discord.eobot.plugin.kummerkasten;

import de.eonadev.discord.eobot.api.EoBot;
import de.eonadev.discord.eobot.api.plugin.EoBotPlugin;

public class KummerKasten extends EoBotPlugin{

	@Override
	public void onEnable() {
		EoBot.getInstance().getPluginManager().registerCommand(new KummerKastenCommand(), this);
		EoBot.getInstance().getPluginManager().registerListener(new ReactionAddedEventListener());
		}

	@Override
	public void onDisable() {
		
	}

}
