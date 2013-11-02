package org.whitten.MOO.type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */

public class ListType implements Type {
    private List<Type> value;

    public ListType() {
        this.value = new ArrayList<>();
    }

    public ListType(List<Type> value) {
        this.value = new ArrayList<>(value);
    }

    @Override
    public List<Type> getValue() {
        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setValue(Object value) {
        if(value instanceof List) {
            this.value = (ArrayList<Type>)value;
        } else {
            throw new IllegalArgumentException("Type error");
        }
    }
    
}
