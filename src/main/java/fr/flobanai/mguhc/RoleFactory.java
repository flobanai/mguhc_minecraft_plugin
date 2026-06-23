package fr.flobanai.mguhc;

import fr.flobanai.mguhc.roles.Ares;
import fr.flobanai.mguhc.roles.Role;
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
            case "Ares":
                return new Ares();
            // TODO: Ajoute tes autres rôles ici au fur et à mesure (HADES, HERMES...)
            // case "HADES": return new Hades();
            default:
                return null; // Rôle introuvable
        }
    }
}