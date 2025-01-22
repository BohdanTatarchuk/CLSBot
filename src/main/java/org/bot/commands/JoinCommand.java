package org.bot.commands;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static org.bot.Util.checkIfBotIsInChannel;
import static org.bot.Util.checkObject;

public class JoinCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        if(!event.getName().equals("join")) return;

        SelfUser bot = event.getJDA().getSelfUser();
        String botId = bot.getId();

        Member member = event.getMember();

        if (checkObject(member, event)) return;
        assert member != null;

        GuildVoiceState voiceState = member.getVoiceState();
        if (checkObject(voiceState, event)) return;
        assert voiceState != null;

        if (!voiceState.inAudioChannel()) {
            event.reply("Sorry mate, you are not in voice channel").queue();
            return;
        }

        AudioChannelUnion voiceChannel = voiceState.getChannel();
        assert voiceChannel != null;

        List<Member> members = voiceChannel.getMembers();
        if (checkIfBotIsInChannel(botId, members, event,"Nah, i'm here")) return;

        String guildId = member.getGuild().getId();
        Guild currentGuild = bot.getJDA().getGuildById(guildId);

        if (checkObject(currentGuild, event)) return;
        assert currentGuild != null;

        AudioManager audioManager = currentGuild.getAudioManager();
        audioManager.openAudioConnection(voiceChannel);
        event.reply("Hi!").queue();
    }
}
