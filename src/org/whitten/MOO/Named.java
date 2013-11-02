package org.whitten.MOO;

import java.util.List;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public interface Named {
    public String getName();
    public void setName(String name);
    
    public List<String> getAliases();
    public void setAliases(List<String> aliases);
}
