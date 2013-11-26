package org.whitten.MOO.property;

import org.whitten.MOO.Permissions;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public class PropertyPermissions extends Permissions {

    private Boolean chown = false;
    
    public PropertyPermissions() {
    }

    public PropertyPermissions(Boolean readable, Boolean writable, Boolean chown) {
        super(readable, writable);
        this.chown = chown;
    }

    public Boolean getChown() {
        return chown;
    }

    public void setChown(Boolean chown) {
        this.chown = chown;
    }
}
