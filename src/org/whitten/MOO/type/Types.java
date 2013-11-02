/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.whitten.MOO.type;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public enum Types {
    INTEGER(0),
    OBJECT(1),
    STRING(2),
    ERROR(3),
    LIST(4),
    CLEAR(5),
    FLOAT(9);
    
    private Integer typecode;
    
    Types(Integer typecode) {
        this.typecode = typecode;
    }
    
    public Integer getTypecode() {
        return typecode;
    }
    
    public Type getInstance(Types type) {
        switch(type) {
            case INTEGER:
                return new IntType();
            case OBJECT:
                return new ObjType();
            case STRING:
                return new StrType();
            case ERROR:
                return new ErrType();
            case LIST:
                return new ListType();
            case CLEAR:
                return new ClearType();
            case FLOAT:
                return new FloatType();
            default:
                throw new IllegalArgumentException("Invalid type");
        }
    }
    
    public Type getInstance(Integer typecode) {
        for(Types type : Types.values()) {
            if(type.getTypecode() == typecode) {
                return getInstance(type);
            }
        }
        throw new IllegalArgumentException("Invalid typecode: " + typecode);
    }
}
