package org.bot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bot.commands.JoinCommand;
import org.bot.commands.LeaveCommand;
import org.bot.commands.PlayCommand;
import org.bot.commands.StopCommand;

public class Main {
    public static void main(String[] args) {
        String token = Util.getToken();
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_VOICE_STATES);
        builder.addEventListeners(new Listener());
        builder.addEventListeners(new JoinCommand(), new PlayCommand(), new StopCommand(), new LeaveCommand());

        try {
            builder.build().awaitReady();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}