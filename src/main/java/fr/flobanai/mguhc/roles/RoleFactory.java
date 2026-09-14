package fr.flobanai.mguhc.roles;

public class RoleFactory {

    public static Role getRoleFromString(String roleName) {
        try {
            return RoleType.valueOf(roleName.toUpperCase()).create();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
