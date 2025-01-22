package org.bot.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

import static org.bot.Util.checkIfBotIsInChannel;

public class LeaveCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        if (!event.getName().equals("leave")) return;

        Boolean status = checkIfBotIsInChannel(event, "bye bye!");

        if (status == null) return;

        if (status) {
            String guildId = event.getGuild().getId();
            Guild currentGuild = event.getJDA().getGuildById(guildId);
            AudioManager audioManager = currentGuild.getAudioManager();
            audioManager.closeAudioConnection();
        } else {
            event.reply("No!").queue();
        }
    }
}
