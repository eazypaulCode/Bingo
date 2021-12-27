package de.eazypaulcode.commands;

import de.eazypaulcode.Bingo;
import de.eazypaulcode.util.StringDefaults;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.lang.reflect.FieldUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.util.FileUtil;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author eazypaul
 * @since 25.12.2021
 */
public class SaveWorldCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("bingo.admin")) return true;
        if (args.length == 0) {
            sender.sendMessage(StringDefaults.ERROR("SaveWorld") + "§7Bitte benutze§8: §9/saveworld <Welt>");
            return true;
        }
        String outputName = "ERROR";
        switch (args[0].toUpperCase(Locale.ROOT)) {
            case "OVERWORLD":
            case "WORLD":
                sender.sendMessage(StringDefaults.NORMAL("SaveWorld") + "§7Es wird versucht, die Welt §9" + "world" + " §7zu speichern.");
                World overworld = Bukkit.getWorld("world");
                if (overworld == null) {
                    sender.sendMessage(StringDefaults.ERROR("SaveWorld") + "§7Konnte die Welt nicht speichern. §8[World not existing]");
                    return true;
                }
                File outputFile;
                try {
                    outputFile = zipFile("world");
                    outputName = outputFile.getName();
                } catch (IOException e) {
                    sender.sendMessage(StringDefaults.ERROR("SaveWorld") + "§7Konnte die Welt nicht speichern. §8[IOException]");
                    sender.sendMessage(StringDefaults.NORMAL("SaveWorld") + "§7Genauen Fehler in die Console gesendet.");
                    e.printStackTrace();
                    return true;
                }
                BaseComponent worldComponent = new TextComponent(TextComponent.fromLegacyText(StringDefaults.SUCCESS("SaveWorld") + "§7Welt §9" + "world" + " §7erfolgreich kopiert und gespeichert. §8Schau in ~/savedWorlds/" + outputName + " nach."));
                worldComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Weltenname§8: §9world\n§7Weltentyp§8: §9" + overworld.getWorldType() + "\n§7Zeitpunkt§8: §9" + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()) + "\n§7Genauer Pfad§8: §9" + outputFile.getAbsolutePath())));
                sender.spigot().sendMessage(worldComponent);
                return true;
            case "NETHER":
            case "WORLD_NETHER":
                sender.sendMessage(StringDefaults.NORMAL("SaveWorld") + "§7Es wird versucht, die Welt §9" + "world_nether" + " §7zu speichern.");
                World nether = Bukkit.getWorld("world_nether");
                if (nether == null) {
                    sender.sendMessage(StringDefaults.ERROR("SaveWorld") + "§7Konnte die Welt nicht speichern.");
                    return true;
                }
                try {
                    outputName = zipFile("world_nether").getName();
                } catch (IOException e) {
                    sender.sendMessage(StringDefaults.ERROR("SaveWorld") + "§7Konnte die Welt nicht speichern. §8[IOException]");
                    sender.sendMessage(StringDefaults.NORMAL("SaveWorld") + "§7Genauen Fehler in die Console gesendet.");
                    e.printStackTrace();
                    return true;
                }
                sender.sendMessage(StringDefaults.SUCCESS("SaveWorld") + "§7Welt §9" + "world_nether" + " §7erfolgreich kopiert und gespeichert. §8Schau in ~/savedWorlds/" + outputName + " nach.");
                return true;
            case "END":
            case "WORLD_THE_END":
                sender.sendMessage(StringDefaults.NORMAL("SaveWorld") + "§7Es wird versucht, die Welt §9" + "world_the_end" + " §7zu speichern.");
                World end = Bukkit.getWorld("world_the_end");
                if (end == null) {
                    sender.sendMessage(StringDefaults.ERROR("SaveWorld") + "§7Konnte die Welt nicht speichern.");
                    return true;
                }
                try {
                    outputName = zipFile("world_the_end").getName();
                } catch (IOException e) {
                    sender.sendMessage(StringDefaults.ERROR("SaveWorld") + "§7Konnte die Welt nicht speichern. §8[IOException]");
                    sender.sendMessage(StringDefaults.NORMAL("SaveWorld") + "§7Genauen Fehler in die Console gesendet.");
                    e.printStackTrace();
                    return true;
                }
                sender.sendMessage(StringDefaults.SUCCESS("SaveWorld") + "§7Welt §9" + "world_the_end" + " §7erfolgreich kopiert und gespeichert. §8Schau in ~/savedWorlds/" + outputName + " nach.");
                return true;
            case "ALL":
                sender.sendMessage(StringDefaults.NORMAL("SaveWorld") + "§7Es wird versucht, die §9alle Welten §7zu speichern.");
                World allOverworld = Bukkit.getWorld("world");
                World allNether = Bukkit.getWorld("world_nether");
                World allEnd = Bukkit.getWorld("world_the_end");
                if (allOverworld == null || allNether == null || allEnd == null) {
                    sender.sendMessage(StringDefaults.ERROR("SaveWorld") + "§7Konnte die Welten nicht speichern. §8[World not existing]");
                    return true;
                }
                String outputNameOverworld;
                String outputNameNether;
                String outputNameEnd;
                try {
                    outputNameOverworld = zipFile("world").getName();
                    outputNameNether = zipFile("world_nether").getName();
                    outputNameEnd = zipFile("world_the_end").getName();
                } catch (IOException e) {
                    sender.sendMessage(StringDefaults.ERROR("SaveWorld") + "§7Konnte die Welten nicht speichern. §8[IOException]");
                    sender.sendMessage(StringDefaults.NORMAL("SaveWorld") + "§7Genauen Fehler in die Console gesendet.");
                    e.printStackTrace();
                    return true;
                }
                sender.sendMessage(StringDefaults.SUCCESS("SaveWorld") + "§7Welten erfolgreich kopiert und gespeichert. Gespeicherte Welten§8:");
                sender.sendMessage(StringDefaults.NORMAL("SaveWorld") + "§aOverworld§8: §9" + outputNameOverworld);
                sender.sendMessage(StringDefaults.NORMAL("SaveWorld") + "§cNether§8: §9" + outputNameNether);
                sender.sendMessage(StringDefaults.NORMAL("SaveWorld") + "§9End§8: §9" + outputNameEnd);
                return true;
            case "REMOVEALL":
                File directory = new File("./savedWorlds/");
                if (!directory.exists()) {
                    sender.sendMessage(StringDefaults.ERROR("SaveWorld") + "§7Es konnten keine gespeicherten Welten gefunden werden.");
                    return true;
                }
                int amount = 0;
                for (File file : directory.listFiles()) {
                    amount++;
                    file.delete();
                }
                directory.delete();
                if (amount == 0) {
                    sender.sendMessage(StringDefaults.ERROR("SaveWorld") + "§7Es konnten keine gespeicherten Welten gelöscht werden.");
                    return true;
                }
                sender.sendMessage(StringDefaults.SUCCESS("SaveWorld") + "§7Es wurden §9" + amount + " §7gespeicherte Welten gelöscht.");
                return true;
            default:
                sender.sendMessage(StringDefaults.ERROR("SaveWorld") + "§9" + args[0] + " §7ist keine gültige Welt.");
                return true;

        }
    }

    private File zipFile(String worldName) throws IOException {
        File filePath = new File("./savedWorlds/");
        String zipName = worldName + "-" + System.currentTimeMillis() + ".zip";
        File file = new File(filePath, zipName);
        if (!filePath.exists()) filePath.mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }
        Path p = Paths.get(file.getAbsolutePath());
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
            Path pp = Paths.get(worldName);
            Files.walk(pp).filter(path -> !Files.isDirectory(path)).forEach(path -> {
                ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                try {
                    zs.putNextEntry(zipEntry);
                    Files.copy(path, zs);
                    zs.closeEntry();
                } catch (IOException e) {
                    return;
                }
            });
        }
        return file;
    }

}
