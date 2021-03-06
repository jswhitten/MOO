package org.whitten.MOO.property;

import org.whitten.MOO.Owned;
import org.whitten.MOO.Permissioned;
import org.whitten.MOO.Permissions;
import org.whitten.MOO.object.MooObject;
import org.whitten.MOO.type.Type;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public class Property implements Permissioned, Owned {
    private String name;
    private PropertyPermissions permissions;
    private MooObject owner;
    private Type value;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public PropertyPermissions getPermissions() {
        return this.permissions;
    }

    @Override
    public void setPermissions(Permissions permissions) {
        this.permissions = (PropertyPermissions)permissions;
    }

    @Override
    public MooObject getOwner() {
        return this.owner;
    }

    @Override
    public void setOwner(MooObject owner) {
        this.owner = owner;
    }

    public Type getValue() {
        return value;
    }

    public void setValue(Type value) {
        this.value = value;
    }
}
