package fr.flobanai.mguhc;

import fr.flobanai.mguhc.roles.Aphrodite;
import fr.flobanai.mguhc.roles.Apollon;
import fr.flobanai.mguhc.roles.Ares;
import fr.flobanai.mguhc.roles.Artemis;
import fr.flobanai.mguhc.roles.Cronos;
import fr.flobanai.mguhc.roles.DemonF;
import fr.flobanai.mguhc.roles.DemonM;
import fr.flobanai.mguhc.roles.Hades;
import fr.flobanai.mguhc.roles.Hera;
import fr.flobanai.mguhc.roles.Hermes;
import fr.flobanai.mguhc.roles.Minotaure;
import fr.flobanai.mguhc.roles.Nyx;
import fr.flobanai.mguhc.roles.Poseidon;
import fr.flobanai.mguhc.roles.Role;
import fr.flobanai.mguhc.roles.Thesee;
import fr.flobanai.mguhc.roles.Zeus;

/**
 * Cette classe remplace ton ancien Enum. 
 * Son seul but est de convertir le texte tapé dans la commande 
 * en un véritable Objet Rôle.
 */
public class RoleFactory {

    public static Role getRoleFromString(String roleName) {
        switch (roleName.toUpperCase()) {
            case "Zeus":
                return new Zeus();
            case "Poseidon":
                return new Poseidon();
            case "Hades":
                return new Hades();
            case "Hermes":
                return new Hermes();
            case "Hera":
                return new Hera();
            case "Apollon":
                return new Apollon();
            case "Artemis":
                return new Artemis();
            case "Ares":
                return new Ares();
            case "Aphrodite":
                return new Aphrodite();
            case "Thesee":
                return new Thesee();
            case "Cronos":
                return new Cronos();
            case "Minotaure":
                return new Minotaure();
            case "Demon_femelle":
                return new DemonF();
            case "Demon_male":
                return new DemonM();
            case "Nyx":
                return new Nyx();
            default:
                return null; // Rôle introuvable
        }
    }
}