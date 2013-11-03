package org.whitten.MOO;

import org.whitten.MOO.object.MooObject;
import org.whitten.MOO.type.ObjType;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public interface Owned {
    public ObjType getOwner();
    public void setOwner(ObjType owner);
}
