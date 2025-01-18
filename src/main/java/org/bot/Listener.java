package org.bot;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Listener extends ListenerAdapter {
    private static final Logger log = LoggerFactory.getLogger(Listener.class);

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        super.onReady(event);

        Guild guild = event.getJDA().getGuildById("1240382645360267405");
        assert guild != null;

        guild.upsertCommand("join", "Bot joins the voice channel, in which the user currently is").queue();
        guild.upsertCommand("play", "Plays a song, if it is found online").
                addOptions(new OptionData(OptionType.STRING, "audio", "song name", true)).queue();
    }
}
