package org.whitten.MOO.type;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */

public class ObjType implements Type {
    private Integer value = null;

    public ObjType() {
    }

    public ObjType(Integer value) {
        if(value >= 0) {
            this.value = value;
        } else {
            this.value = null;
        }
            
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        if(value instanceof Integer) {
            this.value = (Integer)value;
        } else {
            throw new IllegalArgumentException("Type error");
        }
    }
    
    @Override
    public String toString() {
        if(value == null) {
            return "#-1";
        } else {
            return "#" + value;
        }
    }
    
}
