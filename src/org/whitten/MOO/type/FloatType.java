package org.whitten.MOO.type;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */

public class FloatType implements Type {
    private Double value;

    public FloatType() {
        this.value = 0.0;
    }

    public FloatType(Double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        if(value instanceof Double) {
            this.value = (Double)value;
        } else {
            throw new IllegalArgumentException("Type error");
        }
    }
    
}
