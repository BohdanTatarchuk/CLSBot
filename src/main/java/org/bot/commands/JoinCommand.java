package org.bot.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

public class JoinCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        if(!event.getName().equals("join")) return;

        SelfUser bot = event.getJDA().getSelfUser();

        Member member = event.getMember();
        if (checkObject(member, event)) return;
        assert member != null;

        GuildVoiceState voiceState = member.getVoiceState();
        if (checkObject(voiceState, event)) return;
        assert voiceState != null;

        if (!voiceState.inAudioChannel()) {
            event.reply("Sorry, you are not in voice channel").queue();
            return;
        }

        AudioChannelUnion voiceChannel = voiceState.getChannel();

        String guildId = member.getGuild().getId();
        Guild currentGuild = bot.getJDA().getGuildById(guildId);

        if (checkObject(currentGuild, event)) return;
        assert currentGuild != null;

        AudioManager audioManager = currentGuild.getAudioManager();
        audioManager.openAudioConnection(voiceChannel);
        event.reply("Hi!").queue();
    }

    private boolean checkObject(Object object, SlashCommandInteractionEvent event) {
        if (object == null) {
            event.reply("Sorry mate, some issue occurred").queue();
            return true;
        }
        return false;
    }
}
