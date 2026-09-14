package fr.flobanai.mguhc.roles;

import org.bukkit.ChatColor;

public enum Team {
    OLYMPE("Olympe", ChatColor.YELLOW),
    TARTARE("Tartare", ChatColor.RED),
    NYX("Nyx", ChatColor.DARK_PURPLE);

    private final String displayName;
    private final ChatColor chatColor;

    Team(String displayName, ChatColor chatColor) {
        this.displayName = displayName;
        this.chatColor = chatColor;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }
}
