package net.vlands.util.internal.bukkit.ui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.libs.jline.Terminal;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.command.CraftConsoleCommandSender;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Attribute;

import java.awt.*;
import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColouredConsoleSender extends CraftConsoleCommandSender {
    private final Terminal terminal;
    private final Map<ChatColor, String> replacements = new EnumMap<>(ChatColor.class);
    private final ChatColor[] colors = ChatColor.values();
    private final boolean jansiPassthrough;
    private static final char ANSI_ESC_CHAR = '\u001B';
    private static final String RGB_STRING = ANSI_ESC_CHAR + "[38;2;%d;%d;%dm";
    private static final Pattern RBG_TRANSLATE = Pattern.compile(ChatColor.COLOR_CHAR + "x(" + ChatColor.COLOR_CHAR + "[A-F0-9]){6}", Pattern.CASE_INSENSITIVE);

    protected ColouredConsoleSender() {
        super();
        this.terminal = ((CraftServer) getServer()).getReader().getTerminal();
        this.jansiPassthrough = Boolean.getBoolean("jansi.passthrough");

        replacements.put(ChatColor.BLACK, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.BLACK).boldOff().toString());
        replacements.put(ChatColor.DARK_BLUE, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.BLUE).boldOff().toString());
        replacements.put(ChatColor.DARK_GREEN, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.GREEN).boldOff().toString());
        replacements.put(ChatColor.DARK_AQUA, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.CYAN).boldOff().toString());
        replacements.put(ChatColor.DARK_RED, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.RED).boldOff().toString());
        replacements.put(ChatColor.DARK_PURPLE, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.MAGENTA).boldOff().toString());
        replacements.put(ChatColor.GOLD, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.YELLOW).boldOff().toString());
        replacements.put(ChatColor.GRAY, Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.WHITE).boldOff().toString());
        replacements.put(ChatColor.DARK_GRAY, Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.BLACK).bold().toString());
        replacements.put(ChatColor.BLUE, Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.BLUE).bold().toString());
        replacements.put(ChatColor.GREEN, Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.GREEN).bold().toString());
        replacements.put(ChatColor.AQUA, Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.CYAN).bold().toString());
        replacements.put(ChatColor.RED, Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.RED).bold().toString());
        replacements.put(ChatColor.LIGHT_PURPLE, Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.MAGENTA).bold().toString());
        replacements.put(ChatColor.YELLOW, Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.YELLOW).bold().toString());
        replacements.put(ChatColor.WHITE, Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.WHITE).bold().toString());
        replacements.put(ChatColor.MAGIC, Ansi.ansi().a(Attribute.BLINK_SLOW).toString());
        replacements.put(ChatColor.BOLD, Ansi.ansi().a(Attribute.UNDERLINE_DOUBLE).toString());
        replacements.put(ChatColor.STRIKETHROUGH, Ansi.ansi().a(Attribute.STRIKETHROUGH_ON).toString());
        replacements.put(ChatColor.UNDERLINE, Ansi.ansi().a(Attribute.UNDERLINE).toString());
        replacements.put(ChatColor.ITALIC, Ansi.ansi().a(Attribute.ITALIC).toString());
        replacements.put(ChatColor.RESET, Ansi.ansi().a(Attribute.RESET).toString());
    }

    @Override
    public void sendMessage(String message) {
        if (jansiPassthrough || terminal.isAnsiSupported()) {
            if (!conversationTracker.isConversingModaly()) {
                String result = convertRGBColors(message);
                for (ChatColor color : colors) {
                    result = result.replaceAll("(?i)" + color.toString(), replacements.getOrDefault(color, ""));
                }
                System.out.println(result + Ansi.ansi().reset().toString());
            }
        } else {
            super.sendMessage(message);
        }
    }

    private static String convertRGBColors(String input) {
        Matcher matcher = RBG_TRANSLATE.matcher(input);
        StringBuilder buffer = new StringBuilder();
        while (matcher.find()) {
            String s = matcher.group().replace("ยง", "").replace('x', '#');
            Color color = Color.decode(s);
            int red = color.getRed();
            int blue = color.getBlue();
            int green = color.getGreen();
            String replacement = String.format(RGB_STRING, red, green, blue);
            matcher.appendReplacement(buffer, replacement);
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    public static ConsoleCommandSender getInstance() {
        if (Bukkit.getConsoleSender() != null) {
            return Bukkit.getConsoleSender();
        } else {
            return new ColouredConsoleSender();
        }
    }
}