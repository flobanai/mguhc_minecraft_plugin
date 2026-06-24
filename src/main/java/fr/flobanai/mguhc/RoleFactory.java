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
            case "ZEUS":
                return new Zeus();
            case "POSEIDON":
                return new Poseidon();
            case "HADES":
                return new Hades();
            case "HERMES":
                return new Hermes();
            case "HERA":
                return new Hera();
            case "APOLLON":
                return new Apollon();
            case "ARTEMIS":
                return new Artemis();
            case "ARES":
                return new Ares();
            case "APHRODITE":
                return new Aphrodite();
            case "THESEE":
                return new Thesee();
            case "CRONOS":
                return new Cronos();
            case "MINOTAURE":
                return new Minotaure();
            case "DEMON_FEMELLE":
                return new DemonF();
            case "DEMON_MALE":
                return new DemonM();
            case "NYX":
                return new Nyx();
            default:
                return null; // Rôle introuvable
        }
    }
}