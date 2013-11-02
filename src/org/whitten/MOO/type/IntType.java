package org.whitten.MOO.type;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */

public class IntType implements Type {
    private Integer value;

    public IntType() {
        this.value = 0;
    }

    public IntType(Integer value) {
        this.value = value;
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
    
}
