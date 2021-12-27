package de.eazypaulcode.commands;

import de.eazypaulcode.util.StringDefaults;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

/**
 * @author eazypaul
 * @since 23.12.2021
 */
public class StartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("bingo.admin")) return true;
        if (args.length == 0) {
            sender.sendMessage(StringDefaults.SUCCESS("Bingo") + "§7Zeit bis zum Start der Runde auf §910 Sekunden §7gesetzt.");
            for (Player target : Bukkit.getOnlinePlayers()) {
                boolean flagged = false;
                target.closeInventory();
                if (target.isFlying() || target.getAllowFlight() || target.isGliding()) {
                    flagged = true;
                    target.setGliding(false);
                    target.setAllowFlight(false);
                    target.setFlying(false);
                    target.sendMessage(StringDefaults.WARNING("Bingo") + "§7Dein Flug-/Gleitmodus wurde deaktiviert.");
                }
                if (target.getGameMode() != GameMode.SURVIVAL) {
                    flagged = true;
                    target.setGameMode(GameMode.SURVIVAL);
                    target.sendMessage(StringDefaults.WARNING("Bingo") + "§7Du bist nun im Überlebensmodus.");
                }
                if (flagged) {
                    target.sendMessage(StringDefaults.NORMAL("Bingo") + "§7Grund§8: §7Bingo startet gleich.");
                }
            }
            // start in 10 seconds
            return true;
        }
        int startIn = 10;
        int time = 0;
        try {
            int length = args[0].length();
            time = Integer.parseInt(args[0].substring(0, length - 1));
            if (!args[0].toLowerCase(Locale.ROOT).endsWith("s") && !args[0].toLowerCase(Locale.ROOT).endsWith("m")) {
                sender.sendMessage(StringDefaults.ERROR("Bingo") + "§7Bitte benutze§8: §9/start [§9Zeit + s/m]");
                return true;
            }
            if (args[0].toLowerCase(Locale.ROOT).endsWith("s")) {
                startIn = time;
            } else if (args[0].toLowerCase(Locale.ROOT).endsWith("m")) {
                startIn = time * 60;
            }
        } catch (NumberFormatException e) {
            sender.sendMessage(StringDefaults.ERROR("Bingo") + "§7Bitte benutze§8: §9/start [§9Zeit + s/m]");
            return true;
        }
        if (startIn < 3) {
            sender.sendMessage(StringDefaults.ERROR("Bingo") + "§7Bitte benutze einen Wert, der größer als §93 Sekunden §7ist.");
            return true;
        }
        if (startIn > 30 * 60) {
            sender.sendMessage(StringDefaults.ERROR("Bingo") + "§7Bitte benutze einen Wert, der kleiner als §930 Minuten §7ist.");
            return true;
        }
        if (args[0].toLowerCase(Locale.ROOT).endsWith("s")) {
            sender.sendMessage(StringDefaults.SUCCESS("Bingo") + "§7Zeit bis zum Start der Runde auf §9" + time + " Sekunden §7gesetzt.");
        } else if (args[0].toLowerCase(Locale.ROOT).endsWith("m")) {
            sender.sendMessage(StringDefaults.SUCCESS("Bingo") + "§7Zeit bis zum Start der Runde auf §9" + time + " Minuten §7gesetzt.");
        } else {
            sender.sendMessage(StringDefaults.ERROR("Bingo") + "§7Konnte Zeit bis zum Start der Runde nicht setzen.");
            return true;
        }
        // start in time seconds
        return false;
    }
}
