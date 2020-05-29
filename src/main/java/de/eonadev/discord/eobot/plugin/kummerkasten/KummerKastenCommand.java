package de.eonadev.discord.eobot.plugin.kummerkasten;

import java.util.HashMap;

import org.javacord.api.entity.channel.ChannelType;
import org.javacord.api.entity.channel.PrivateChannel;
import org.javacord.api.entity.message.Message;


import de.eonadev.discord.eobot.api.commands.base.Command;
import de.eonadev.discord.eobot.api.commands.base.CommandInfo;
import de.eonadev.discord.eobot.api.commands.base.Response;
import de.eonadev.discord.eobot.api.commands.base.ResponseTemplate;

@CommandInfo(cmd="kummerkasten")
public class KummerKastenCommand extends Command {
	public static HashMap<Long, SuggestionBoxItem> items = new HashMap<Long, SuggestionBoxItem>();

	@Override
	public void run(Message message, ChannelType channelType, String messageContent, String[] args) {	
		if (channelType.equals(ChannelType.PRIVATE_CHANNEL)) {
			if(args.length == 0) {
				Response response = ResponseTemplate.help(message);
				response.setText("!kummerkasten <Nachricht>");
				response.setDeletionTime(-1L);
				response.send(false);
				return;
			}
			Response response = new Response(message);
			response.setText(
					"Vielen Dank für dein Vertrauen in Eona, bitte wähle nun aus, an wen dein Anliegen gesendet werden soll.\n"
							+ "\n"
							+ Emojis.A.getUnicode() + " Alle Teamler\n"
							+ Emojis.B.getUnicode() + " Alle Comas (Titan, Mastero & Blood)\n" 
							+ Emojis.C.getUnicode() + " Titan (alambe94)\n"
							+ Emojis.D.getUnicode() + " Mastero (SirMastero)\n"
							+ Emojis.E.getUnicode() + " Blood (Bloodrayne1995)\n"
							+ Emojis.F.getUnicode() + " Siven (Siven4)\n"
			 				+ Emojis.X.getUnicode() + " Abbrechen");
			response.setDeletionTime(-1);
			response.addReaction(Emojis.A.getUnicode(), Emojis.B.getUnicode(), Emojis.C.getUnicode(), Emojis.D.getUnicode(), Emojis.E.getUnicode(), Emojis.F.getUnicode(), Emojis.X.getUnicode());
			response.send(false);
			items.put(message.getChannel().getId(), new SuggestionBoxItem(message));
		} else {
			message.delete();
			Response response = new Response(message);
			response.setText("Bitte schreibe mir dein Anliegen privat.\nIch habe dich bereits angeschrieben");
			response.setDeletionTime(5);
			PrivateChannel pc = message.getAuthor().asUser().get().openPrivateChannel().join();
			Response pcr = new Response(pc, message.getAuthor().asUser().get());
			pcr.setText(
					"Hey, du möchtest etwas los werden..?\nHier nochmals die Nachricht welche du mir geschrieben hast, falls du diese kopieren willst.");
			pcr.setDeletionTime(-1);
			pcr.send(false);
			response.send(false);
			pc.sendMessage(message.getContent());
		}

	}

}
