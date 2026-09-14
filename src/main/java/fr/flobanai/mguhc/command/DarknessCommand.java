package fr.flobanai.mguhc.command;

import fr.flobanai.mguhc.roles.Role;
import fr.flobanai.mguhc.roles.nyx.Nyx;

public class DarknessCommand extends RoleCommand {

    @Override
    protected Class<? extends Role> requiredRole() {
        return Nyx.class;
    }

    @Override
    protected String powerDeniedMessage() {
        return "§cVous n'avez pas la capacité d'utiliser les Ténèbres.";
    }
}
