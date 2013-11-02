package org.whitten.MOO.object;

import org.whitten.MOO.Permissions;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public class ObjectPermissions extends Permissions {
    private Boolean fertile = false;

    public ObjectPermissions() {
    }
    
    public ObjectPermissions(Boolean readable, Boolean writable, Boolean fertile) {
        this.setReadable(readable);
        this.setWritable(writable);
        this.fertile = fertile;
    }

    public Boolean getFertile() {
        return fertile;
    }

    public void setFertile(Boolean fertile) {
        this.fertile = fertile;
    }
}
