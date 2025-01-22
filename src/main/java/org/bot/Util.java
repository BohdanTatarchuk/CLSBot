package org.bot;

import net.dv8tion.jda.api.entities.Member;
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

    public static boolean checkIfBotIsInChannel(String botId, List<Member> members, SlashCommandInteractionEvent event, String message) {
        for (Member m : members) {
            if (m.getId().equals(botId)) {
                event.reply(message).queue();
                return true;
            }
        }
        return false;
    }
}
