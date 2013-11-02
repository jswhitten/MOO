package org.whitten.MOO;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public interface Permissioned {
    public Permissions getPermissions();
    public void setPermissions(Permissions permissions);
}
