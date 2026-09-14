package fr.flobanai.mguhc.command;

import java.util.Collections;
import java.util.List;

import org.bukkit.command.CommandSender;

public interface SubCommand {

    /**
     * @return le noeud de permission requis, ou null si accessible à tous.
     */
    default String permission() {
        return null;
    }

    boolean execute(CommandSender sender, String[] args);

    /**
     * @return les complétions pour cette sous-commande, ou null pour laisser Bukkit
     * proposer la complétion par défaut (ex: liste des joueurs en ligne).
     */
    default List<String> tabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}
