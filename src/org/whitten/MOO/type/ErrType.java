package org.whitten.MOO.type;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */

public class ErrType implements Type {
    private Err value;
    public enum Err {
        E_NONE, 
        E_TYPE, 
        E_DIV, 
        E_PERM, 
        E_PROPNF, 
        E_VERBNF, 
        E_VARNF, 
        E_INVIND, 
        E_RECMOVE, 
        E_MAXREC, 
        E_RANGE, 
        E_ARGS, 
        E_NACC, 
        E_INVARG, 
        E_QUOTA,
        E_FLOAT;
    }

    public ErrType() {
        this.value = Err.E_NONE;
    }

    public ErrType(Err value) {
        this.value = value;
    }

    @Override
    public Err getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = Err.values()[value];
    }    
    
    @Override
    public void setValue(Object value) {
        if(value instanceof Err) {
            this.value = (Err)value;
        } else {
            throw new IllegalArgumentException("Type error");
        }
    }
    
}
