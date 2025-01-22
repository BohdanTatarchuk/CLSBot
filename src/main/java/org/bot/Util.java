package org.bot;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.List;
import java.util.ResourceBundle;

public class Util {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("application");

    public static String getToken() {
        return bundle.getString("api.token");
    }

    public static boolean checkObject(Object object, SlashCommandInteractionEvent event) {
        if (object == null) {
            event.reply("Sorry mate, some issue occurred").queue();
            return true;
        }

        return false;
    }

    public static Boolean checkIfBotIsInChannel(SlashCommandInteractionEvent event, String message) {
        SelfUser bot = event.getJDA().getSelfUser();
        String botId = bot.getId();

        Member member = event.getMember();

        if (checkObject(member, event)) return null;
        assert member != null;

        GuildVoiceState voiceState = member.getVoiceState();
        if (checkObject(voiceState, event)) return null;
        assert voiceState != null;

        if (!voiceState.inAudioChannel()) {
            if (!message.isEmpty()) event.reply("You are not in this chat").queue();
            return false;
        }

        AudioChannelUnion voiceChannel = voiceState.getChannel();
        assert voiceChannel != null;

        List<Member> members = voiceChannel.getMembers();

        for (Member m : members) {
            if (m.getId().equals(botId)) {
                if (!message.isEmpty()) event.reply(message).queue();
                return true;
            }
        }

        return false;
    }
}
