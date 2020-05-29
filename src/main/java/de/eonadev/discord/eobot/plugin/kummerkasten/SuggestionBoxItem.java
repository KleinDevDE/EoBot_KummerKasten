package de.eonadev.discord.eobot.plugin.kummerkasten;

import java.awt.Color;

import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import com.vdurmont.emoji.EmojiParser;

import de.eonadev.discord.eobot.api.EoBot;
import de.eonadev.discord.eobot.api.commands.base.Response;
import de.eonadev.discord.eobot.utils.mappings.Members;
import de.eonadev.discord.eobot.utils.mappings.Channels;

public class SuggestionBoxItem {
	public long user = 0L;
	public Message message;
	public long receiver = 0L;
	public int state = 1;
	
	public SuggestionBoxItem(Message message) {
		this.message = message;
	}

	public long getUser() {
		return user;
	}
	
	public Message getMessage() {
		return message;
	}
	
	public long getReceiver() {
		return receiver;
	}
	
	public void setReceiver(long receiver) {
		this.receiver = receiver;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public void send(boolean anonyme) {
		EmbedBuilder embedBuilder = new EmbedBuilder();
		if (!anonyme)
			embedBuilder.setAuthor(message.getAuthor());
		embedBuilder.setColor(Color.PINK);
		embedBuilder.setTitle("Neues Kummerkasten Anliegen");
		embedBuilder.setDescription(message.getContent().replaceFirst("!kummerkasten ", ""));
		
		if (receiver == 1L) {
			//TODO change ServerIDTest to Prod
			//TODO change channelID
			EoBot.getInstance().getApi().getServerById(EoBot.getInstance().getServerID()).get().getTextChannelById(Channels.KUMMERKASTEN.getChannelID()).get().sendMessage(embedBuilder);
		} else if (receiver == 2L) {
			EoBot.getInstance().getApi().getUserById(Members.ALAMBE94.getId()).join().openPrivateChannel().join().sendMessage(embedBuilder);
			EoBot.getInstance().getApi().getUserById(Members.SIRMASTERO.getId()).join().openPrivateChannel().join().sendMessage(embedBuilder);
			EoBot.getInstance().getApi().getUserById(Members.BLOODRAYNE1995.getId()).join().openPrivateChannel().join().sendMessage(embedBuilder);
		} else {
			EoBot.getInstance().getApi().getUserById(receiver).join().openPrivateChannel().join().sendMessage(embedBuilder);
		}
		
		message.getChannel().sendMessage("Deine Nachricht wurde erfolgreich übermittelt! " + EmojiParser.parseToUnicode(":white_check_mark:"));
	}
	
	public void sendQuestion() {
		Response response = new Response(message);
		response.setText(
				"Möchtest du die Nachricht Anonym versenden?\n"
						+ "\n"
						+ Emojis.DETECTIVE.getUnicode() + " Anonym (Dein Name wird nicht weitergeleitet)\n"
						+ Emojis.MAN.getUnicode() + " Offen (Dein Name wird weitergeleitet)\n"
						+ Emojis.X.getUnicode() + " Abbrechen\n");
		response.setDeletionTime(-1);
		response.addReaction(Emojis.DETECTIVE.getUnicode(), Emojis.MAN.getUnicode(), Emojis.X.getUnicode());
		response.send(false);
	}
}
