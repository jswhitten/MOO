package org.whitten.MOO.verb;

import org.whitten.MOO.Permissions;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public class VerbPermissions extends Permissions {

    private Boolean executable = false;
    private Boolean debug = false;
    
    public VerbPermissions() {
    }

    public VerbPermissions(Boolean readable, Boolean writable, Boolean executable, Boolean debug) {
        super(readable, writable);
        this.executable = executable;
        this.debug = debug;
    }

    public Boolean getExecutable() {
        return executable;
    }

    public void setExecutable(Boolean executable) {
        this.executable = executable;
    }

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

}
