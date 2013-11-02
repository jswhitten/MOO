package org.whitten.MOO.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.whitten.MOO.Named;
import org.whitten.MOO.Owned;
import org.whitten.MOO.Permissioned;
import org.whitten.MOO.Permissions;
import org.whitten.MOO.exceptions.*;
import org.whitten.MOO.property.Property;
import org.whitten.MOO.type.ClearType;
import org.whitten.MOO.type.ObjType;
import org.whitten.MOO.verb.Verb;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public class MooObject implements Named, Permissioned, Owned {
    private ObjType objectNumber = null;
    private Boolean recycled = false;
    private MooObject parent = null;
    private MooObject owner = null;
    private MooObject location = null;
    private List<MooObject> contents = null;
    private Permissions permissions = null;
    private String name = null;
    private List<String> aliases;
    private List<Verb> verbs;
    private Map<String,Property> properties;
    
    public MooObject(Integer objectNumber) {
        this.objectNumber = new ObjType(objectNumber);
        this.recycled = true;
    }

    public MooObject(Integer objectNumber, List<String> aliases, MooObject owner, MooObject parent) {
        this(objectNumber, aliases, owner);
        this.parent = parent;
    }
    
    public MooObject(Integer objectNumber, List<String> aliases, MooObject owner) {
        if(objectNumber == null || aliases == null || owner == null) {
            throw new IllegalArgumentException();
        }
        this.objectNumber = new ObjType(objectNumber);
        this.name = aliases.get(0);
        this.aliases = aliases;
        this.owner = owner;
        
        this.verbs = new ArrayList<>();
        this.properties = new HashMap<>();
        this.permissions = new Permissions();
    }

    public ObjType getObjectNumber() {
        return objectNumber;
    }

    public MooObject getParent() {
        return parent;
    }

    public void setParent(MooObject parent) {
        this.parent = parent;
    }

    @Override
    public MooObject getOwner() {
        return owner;
    }

    @Override
    public void setOwner(MooObject owner) {
        this.owner = owner;
    }

    @Override
    public Permissions getPermissions() {
        return permissions;
    }

    @Override
    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    @Override
    public String getName() {
        return name;
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
        return aliases;
    }

    @Override
    public void setAliases(List<String> aliases) {
        if(!aliases.contains(this.name)) {
            this.name = aliases.get(0);
        }
        
        this.aliases = aliases;
    }

    public List<Verb> getVerbs() {
        return verbs;
    }

    public Map<String, Property> getProperties() {
        return properties;
    }
    
    public Verb getVerb(String alias) throws VerbNotFoundException {
        return getVerb(alias, false);
    }
    
    public Verb getVerb(String alias, Boolean inherited) throws VerbNotFoundException {
        for(Verb verb : verbs) {
            if(verb.getAliases().contains(alias)) {
                return verb;
            } else if(inherited && parent != null) {
                return parent.getVerb(alias, true);
            }
        }
        throw new VerbNotFoundException("#" + objectNumber + ":" + alias + " does not exist.");
    }

    public Property getProperty(String name) throws PropertyNotFoundException {
        // default to inherited because inherited props are on object
        return getProperty(name, true);
    }
    
    public Property getProperty(String name, Boolean inherited) throws PropertyNotFoundException {
        if(properties.containsKey(name)) {
            Property prop = properties.get(name);
            if(prop.getValue() instanceof ClearType && inherited) {
                return parent.getProperty(name);
            } else {
                return prop;
            }
        }
        throw new PropertyNotFoundException(objectNumber.toString() + "." + name + " does not exist.");
    }
    
    public MooObject getLocation() {
        return location;
    }
    
    public void setLocation(MooObject location) {
        if(this.location != null) {
            this.location.contents.remove(this);
        }
        
        this.location = location;

        if(this.location != null) {
            this.location.contents.add(this);
        }
    }

    public Boolean getRecycled() {
        return recycled;
    }

    public void setRecycled(Boolean recycled) {
        this.recycled = recycled;
    }

    public List<MooObject> getContents() {
        return contents;
    }
}
