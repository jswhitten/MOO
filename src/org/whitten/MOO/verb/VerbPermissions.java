package org.whitten.MOO.verb;

import org.whitten.MOO.Permissions;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public class VerbPermissions extends Permissions {

    private Boolean executable = false;
    
    public VerbPermissions() {
    }

    public VerbPermissions(Boolean readable, Boolean writable, Boolean executable) {
        this.setReadable(readable);
        this.setWritable(writable);
        this.executable = executable;
    }

    public Boolean getExecutable() {
        return executable;
    }

    public void setExecutable(Boolean executable) {
        this.executable = executable;
    }
}
