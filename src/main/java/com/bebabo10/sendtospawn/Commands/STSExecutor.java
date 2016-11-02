package com.bebabo10.sendtospawn.Commands;

import java.util.ArrayList;
import java.util.List;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class STSExecutor implements CommandExecutor {
    public static List<String> STSList = new ArrayList();

    public STSExecutor() {
    }

    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if(!args.hasAny("target")) {
            src.sendMessage(Text.of(new Object[]{TextColors.RED, "Invalid arguments"}));
            return CommandResult.success();
        } else {
            String target = (String)args.getOne("target").get();
            if(STSList.contains(target)) {
                src.sendMessage(Text.of(new Object[]{TextColors.RED, target + " Has been Already been added to the Send To Spawn List"}));
                return CommandResult.success();
            } else {
                STSList.add(target);
                src.sendMessage(Text.of(new Object[]{TextColors.GREEN, target + " Has been added to the Send To Spawn List"}));
                return CommandResult.success();
            }
        }
    }

    public List<String> getSTSList() {
        return STSList;
    }
}