package org.whitten.MOO.type;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */

public class StrType implements Type {
    private String value;

    public StrType() {
        this.value = "";
    }

    public StrType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        if(value instanceof String) {
            this.value = (String)value;
        } else {
            throw new IllegalArgumentException("Type error");
        }
    }
    
}
