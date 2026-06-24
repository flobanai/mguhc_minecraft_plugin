package fr.flobanai.mguhc;

import fr.flobanai.mguhc.roles.Aphrodite;
import fr.flobanai.mguhc.roles.Apollon;
import fr.flobanai.mguhc.roles.Ares;
import fr.flobanai.mguhc.roles.Artemis;
import fr.flobanai.mguhc.roles.Cronos;
import fr.flobanai.mguhc.roles.DemonF;
import fr.flobanai.mguhc.roles.DemonM;
import fr.flobanai.mguhc.roles.Hades;
import fr.flobanai.mguhc.roles.Hephaistos;
import fr.flobanai.mguhc.roles.Hera;
import fr.flobanai.mguhc.roles.Hermes;
import fr.flobanai.mguhc.roles.Minotaure;
import fr.flobanai.mguhc.roles.Nyx;
import fr.flobanai.mguhc.roles.Poseidon;
import fr.flobanai.mguhc.roles.Role;
import fr.flobanai.mguhc.roles.Thesee;
import fr.flobanai.mguhc.roles.Zeus;

public class RoleFactory {

    public static Role getRoleFromString(String roleName) {
        switch (roleName.toUpperCase()) {
            case "APHRODITE":
                return new Aphrodite();
            case "APOLLON":
                return new Apollon();
            case "ARES":
                return new Ares();
            case "ARTEMIS":
                return new Artemis();
            case "CRONOS":
                return new Cronos();
            case "DEMONF":
                return new DemonF();
            case "DEMONM":
                return new DemonM();
            case "HADES":
                return new Hades();
            case "HEPHAISTOS":
                return new Hephaistos();
            case "HERA":
                return new Hera();
            case "HERMES":
                return new Hermes();
            case "MINOTAURE":
                return new Minotaure();
            case "NYX":
                return new Nyx();
            case "POSEIDON":
                return new Poseidon();
            case "THESEE":
                return new Thesee();
            case "ZEUS":
                return new Zeus();
            default:
                return null; 
        }
    }
}