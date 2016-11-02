package com.bebabo10.sendtospawn;

import com.bebabo10.sendtospawn.Commands.STSExecutor;
import com.google.inject.Inject;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent.Join;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.World;

@Plugin(
        id = "sendtospawn",
        name = "SendToSpawn",
        description = "Sends player to spawn when they log in ",
        authors = {"Bebabo10"}
)
public class SendToSpawn {
    @Inject
    private Logger logger;
    @Inject
    Game game;

    public SendToSpawn() {
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        this.logger.info("Started Sending Potato\'s to spawn");
    }

    @Listener
    public void onPlayerJoin(Join event, @Getter("getTargetEntity") Player player) {
        this.STS(player);
    }

    @Listener
    public void onInit(GameInitializationEvent event) {
        CommandSpec CmdSTS = CommandSpec.builder()
                .description(Text.of("Sends player to spawn on there next login attempt"))
                .permission("SendToSpawn.admin")
                .arguments(GenericArguments.user(Text.of("target")))
                .executor(new STSExecutor())
                .build();
        this.game.getCommandManager().register(this, CmdSTS, "STS", "SendToSpawn");
    }





    public void STS(Player player) {
        List STSL = STSExecutor.STSList;
        if(STSL.contains(player.getName())) {
            Optional test = Sponge.getServer().getWorld(Sponge.getServer().getDefaultWorldName());
            if(test.isPresent()) {
                player.setLocation(((World)test.get()).getSpawnLocation());
                STSExecutor.STSList.remove(player.getName());
            }
        }

    }
}