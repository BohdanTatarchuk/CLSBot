package org.bot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bot.commands.JoinCommand;
import org.bot.commands.PlayCommand;

public class Main {

    public static void main(String[] args) {
        String token = ConfigUtil.getToken();
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.addEventListeners(new Listener());
        builder.addEventListeners(new JoinCommand(), new PlayCommand());
        builder.build();
    }
}