package org.whitten.MOO;

import org.whitten.MOO.object.MooObject;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public interface Owned {
    public MooObject getOwner();
    public void setOwner(MooObject owner);
}
