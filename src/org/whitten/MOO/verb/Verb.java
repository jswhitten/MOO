package org.whitten.MOO.verb;

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
public class Verb implements Named, Permissioned, Owned {
    // dobj = Arg.values()[1]; --> Arg.ANY
    public static enum Arg {
        NONE("none"),
        ANY("any"),
        THIS("this");
        
        private String value;
        
        private Arg(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return this.value;
        }
    }

    public static enum Prep {
        ANY("any"),
        NONE("none"),
        WITH("with/using"),
        AT("at/to"),
        IN_FRONT_OF("in front of"),
        IN("in/inside/into"),
        ON("on top of/on/onto/upon"),
        FROM("out of/from inside/from"),
        OVER("over"),
        THROUGH("through"),
        UNDER("under/underneath/beneath"),
        BEHIND("behind"),
        BESIDE("beside"),
        FOR("for/about"),
        IS("is"),
        AS("as"),
        OFF("off/off of");

        private String value;
        
        private Prep(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return this.value;
        }
    }

    private Arg dobj;
    private Arg iobj;
    private Prep prep;
    private List<String> code;
    private MooObject owner;
    private String name = null;
    private List<String> aliases;
    private VerbPermissions permissions;
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    @Override
    public VerbPermissions getPermissions() {
        return permissions;
    }

    @Override
    public void setPermissions(Permissions permissions) {
        this.permissions = (VerbPermissions)permissions;
    }

    @Override
    public MooObject getOwner() {
        return owner;
    }

    @Override
    public void setOwner(MooObject owner) {
        this.owner = owner;
    }
    
    public String getArgs() {
        return dobj.getValue() + " " + prep.getValue() + " " + iobj.getValue();
    }
    
    public void setArgs(Arg dobj, Prep prep, Arg iobj) {
        this.dobj = dobj;
        this.iobj = iobj;
        this.prep = prep;
    }
    
    public List<String> getCode() {
        return this.code;
    }
    
    public void setCode(List<String> code) {
        this.code = code;
    }
}
