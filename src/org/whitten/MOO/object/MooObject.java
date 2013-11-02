package org.whitten.MOO.object;

import java.util.ArrayList;
import java.util.List;
import org.whitten.MOO.Named;
import org.whitten.MOO.Owned;
import org.whitten.MOO.Permissioned;
import org.whitten.MOO.Permissions;
import org.whitten.MOO.exceptions.*;
import org.whitten.MOO.property.Property;
import org.whitten.MOO.verb.Verb;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public class MooObject implements Named, Permissioned, Owned {
    private Integer objectNumber = null;
    private Boolean recycled = false;
    private MooObject parent = null;
    private MooObject owner = null;
    private MooObject location = null;
    private List<MooObject> contents = null;
    private Permissions permissions = null;
    private String name = null;
    private List<String> aliases;
    private List<Verb> verbs;
    private List<Property> properties;
    
    public MooObject(Integer objectNumber) {
        this.objectNumber = objectNumber;
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
        this.objectNumber = objectNumber;
        this.name = aliases.get(0);
        this.aliases = aliases;
        this.owner = owner;
        
        this.verbs = new ArrayList<>();
        this.properties = new ArrayList<>();
        this.permissions = new Permissions();
    }

    public Integer getObjectNumber() {
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

    public List<Property> getProperties() {
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
        return getProperty(name, false);
    }
    
    public Property getProperty(String name, Boolean inherited) throws PropertyNotFoundException {
        for(Property property : properties) {
            if(property.getName().equals(name)) {
                return property;
            } else if(inherited && parent != null) {
                return parent.getProperty(name, true);
            }
        }
        throw new PropertyNotFoundException("#" + objectNumber + "." + name + " does not exist.");
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
