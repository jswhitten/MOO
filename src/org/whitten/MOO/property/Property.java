package org.whitten.MOO.property;

import java.util.List;
import org.whitten.MOO.Named;
import org.whitten.MOO.Owned;
import org.whitten.MOO.Permissioned;
import org.whitten.MOO.Permissions;
import org.whitten.MOO.object.MooObject;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public class Property implements Named, Permissioned, Owned {
    private String name;
    private Integer type;
    private PropertyPermissions permissions;
    private MooObject owner;
    private String value; // TODO: typed values

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if(!getAliases().contains(name)) {
            getAliases().add(name);
        }
        this.name = name;
    }

    @Override
    public List<String> getAliases() {
        throw new UnsupportedOperationException("Properties cannot have aliases.");
    }

    @Override
    public void setAliases(List<String> aliases) {
        throw new UnsupportedOperationException("Properties cannot have aliases.");
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
