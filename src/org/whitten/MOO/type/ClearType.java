package org.whitten.MOO.type;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */

public class ClearType implements Type {

    @Override
    public Integer getValue() {
        return null;
    }

    @Override
    public void setValue(Object value) {
        throw new IllegalArgumentException("Type error");
    }
    
}
