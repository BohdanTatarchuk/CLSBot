package org.bot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import static org.bot.Util.checkIfBotIsInChannel;

public class PlayCommand extends ListenerAdapter {
    private Queue<String> songQueue = new LinkedList<>();

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        if(!event.getName().equals("play")) return;

        Boolean status = checkIfBotIsInChannel(event, "");

        if (status == null) return;

        String audioName = Objects.requireNonNull(event.getOption("audio")).getAsString();
        songQueue.add(audioName);
        System.out.println("Song queue: " + songQueue);

        if (!status) {
            JoinCommand joinCommand = new JoinCommand();
            joinCommand.joinChannel(event);
            event.getChannel().sendMessage("Audio \"" + audioName + "\" added to queue").queue();
        } else {
            event.reply("Audio \"" + audioName + "\" added to queue").queue();
        }
        

    }
}
