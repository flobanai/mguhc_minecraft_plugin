package fr.flobanai.mguhc.roles;

import java.util.function.Supplier;

import fr.flobanai.mguhc.roles.nyx.Nyx;
import fr.flobanai.mguhc.roles.olympe.Aphrodite;
import fr.flobanai.mguhc.roles.olympe.Apollon;
import fr.flobanai.mguhc.roles.olympe.Ares;
import fr.flobanai.mguhc.roles.olympe.Artemis;
import fr.flobanai.mguhc.roles.olympe.Hades;
import fr.flobanai.mguhc.roles.olympe.Hephaistos;
import fr.flobanai.mguhc.roles.olympe.Hera;
import fr.flobanai.mguhc.roles.olympe.Hermes;
import fr.flobanai.mguhc.roles.olympe.Poseidon;
import fr.flobanai.mguhc.roles.olympe.Thesee;
import fr.flobanai.mguhc.roles.olympe.Zeus;
import fr.flobanai.mguhc.roles.tartare.Cronos;
import fr.flobanai.mguhc.roles.tartare.DemonF;
import fr.flobanai.mguhc.roles.tartare.DemonM;
import fr.flobanai.mguhc.roles.tartare.Minotaure;

/**
 * Source unique de vérité pour la liste des rôles : fabrique + libellé de tab-complete.
 * Ajouter un rôle = ajouter une constante ici, rien à dupliquer ailleurs.
 */
public enum RoleType {
    APHRODITE("Aphrodite", Aphrodite::new),
    APOLLON("Apollon", Apollon::new),
    ARES("Ares", Ares::new),
    ARTEMIS("Artemis", Artemis::new),
    CRONOS("Cronos", Cronos::new),
    DEMONF("DemonF", DemonF::new),
    DEMONM("DemonM", DemonM::new),
    HADES("Hades", Hades::new),
    HEPHAISTOS("Hephaistos", Hephaistos::new),
    HERA("Hera", Hera::new),
    HERMES("Hermes", Hermes::new),
    MINOTAURE("Minotaure", Minotaure::new),
    NYX("Nyx", Nyx::new),
    POSEIDON("Poseidon", Poseidon::new),
    THESEE("Thesee", Thesee::new),
    ZEUS("Zeus", Zeus::new);

    private final String label;
    private final Supplier<Role> factory;

    RoleType(String label, Supplier<Role> factory) {
        this.label = label;
        this.factory = factory;
    }

    public String getLabel() {
        return label;
    }

    public Role create() {
        return factory.get();
    }
}
