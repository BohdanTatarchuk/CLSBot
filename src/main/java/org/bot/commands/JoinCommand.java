package org.bot.commands;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;


import java.util.Objects;

import static org.bot.Util.checkIfBotIsInChannel;
import static org.bot.Util.checkObject;

public class JoinCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        if(!event.getName().equals("join")) return;
        joinChannel(event);
    }

    public void joinChannel(SlashCommandInteractionEvent event) {
        Boolean status = checkIfBotIsInChannel(event,"Nah, i'm here");
        if (status == null || status) return;

        String guildId = Objects.requireNonNull(event.getGuild()).getId();
        Guild currentGuild = event.getJDA().getGuildById(guildId);

        if (checkObject(currentGuild, event)) return;
        assert currentGuild != null;

        AudioManager audioManager = currentGuild.getAudioManager();
        AudioChannel channel = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getVoiceState()).getChannel();

        assert channel != null;
        if (channel.getUserLimit() == channel.getMembers().size()){
            event.reply("You have reached a limit of users!?").queue();
            return;
        }

        audioManager.openAudioConnection(channel);
        event.reply("Hi!").queue();
    }
}
