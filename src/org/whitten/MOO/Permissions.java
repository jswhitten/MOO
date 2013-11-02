package org.whitten.MOO;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public class Permissions {
    private Boolean readable = false;
    private Boolean writable = false;

    public Permissions() {
    }

    public Boolean getReadable() {
        return readable;
    }

    public void setReadable(Boolean readable) {
        this.readable = readable;
    }

    public Boolean getWritable() {
        return writable;
    }

    public void setWritable(Boolean writable) {
        this.writable = writable;
    }
}
