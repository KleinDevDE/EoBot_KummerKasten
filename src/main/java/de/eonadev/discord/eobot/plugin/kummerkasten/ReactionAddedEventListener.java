package de.eonadev.discord.eobot.plugin.kummerkasten;

import com.vdurmont.emoji.EmojiParser;

import de.eonadev.discord.eobot.api.events.discord.channels.reaction.ReactionAddedEvent;
import de.eonadev.discord.eobot.api.listeners.EventHandler;
import de.eonadev.discord.eobot.api.listeners.Listener;
import de.eonadev.discord.eobot.utils.mappings.Members;

public class ReactionAddedEventListener implements Listener{	
	@EventHandler
	public void onReactionAdd(ReactionAddedEvent e) {
		if(!e.getMessage().isPresent())
			return;
		if(e.getUser().isBot() || !KummerKastenCommand.items.containsKey(e.getMessage().get().getChannel().getId()))
			return;		
		SuggestionBoxItem item = KummerKastenCommand.items.get(e.getMessage().get().getChannel().getId());
		
		
		if (e.getEmoji().equalsEmoji(EmojiParser.parseToUnicode(Emojis.X.getUnicode()))) { // Abbrechen
			e.getMessage().get().getChannel().sendMessage("Deine Anfrage wurde abgebrochen.");
			KummerKastenCommand.items.remove(e.getMessage().get().getChannel().getId());
			return;
		}
		
		if (item.getState() == 1) {
			if (e.getEmoji().equalsEmoji(EmojiParser.parseToUnicode(Emojis.A.getUnicode()))) {// Team
				item.setReceiver(1);
				item.sendQuestion();
				item.setState(2);
			} else if (e.getEmoji().equalsEmoji(EmojiParser.parseToUnicode(Emojis.B.getUnicode()))) { // Coma
				item.setReceiver(2);
				item.sendQuestion();
				item.setState(2);
			} else if (e.getEmoji().equalsEmoji(EmojiParser.parseToUnicode(Emojis.C.getUnicode()))) { // Alex
				item.setReceiver(Members.ALAMBE94.getId());
				item.sendQuestion();
				item.setState(2);
			} else if (e.getEmoji().equalsEmoji(EmojiParser.parseToUnicode(Emojis.D.getUnicode()))) { // Steven
				item.setReceiver(Members.SIRMASTERO.getId());
				item.sendQuestion();
				item.setState(2);
			} else if (e.getEmoji().equalsEmoji(EmojiParser.parseToUnicode(Emojis.E.getUnicode()))) { // Marcel
				item.setReceiver(Members.BLOODRAYNE1995.getId());
				item.sendQuestion();
				item.setState(2);
			} else if (e.getEmoji().equalsEmoji(EmojiParser.parseToUnicode(Emojis.F.getUnicode()))) { // Siven
				item.setReceiver(Members.SIVEN4.getId());
				item.sendQuestion();
				item.setState(2);
			}
		} else if (item.getState() == 2) {
			if (e.getEmoji().equalsEmoji(EmojiParser.parseToUnicode(Emojis.DETECTIVE.getUnicode()))) { // Anonym
				item.send(true);
				KummerKastenCommand.items.remove(e.getMessage().get().getChannel().getId());
			} else if (e.getEmoji().equalsEmoji(EmojiParser.parseToUnicode(Emojis.MAN.getUnicode()))) { // Mit name
				item.send(false);
				KummerKastenCommand.items.remove(e.getMessage().get().getChannel().getId());
			}
		}
		return;
		
	}
}
