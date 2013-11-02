package org.whitten.MOO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.whitten.MOO.object.MooObject;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public class Database {
    private Integer version;
    private Map<Integer, MooObject> objects;
    private List<Integer> players;
    
    // TODO - clocks, queued tasks, suspended tasks, active connections

    public Database(Integer version) {
        this.version = version;
        objects = new HashMap<>();
        players = new ArrayList<>();
    }

    public Integer getVersion() {
        return version;
    }
    
    public List<MooObject> getObjects() {
        return new ArrayList<>(objects.values());
    }
    
    public MooObject getObject(Integer objNum) {
        return objects.get(objNum);
    }

    public List<Integer> getPlayers() {
        return players;
    }
    
    public List<MooObject> getPlayerObjects() {
        List<MooObject> playerObjects = new ArrayList<>();
        for(Integer player : players) {
            playerObjects.add(objects.get(player));
        }
        return playerObjects;
    }

    public void setPlayers(List<Integer> players) {
        this.players = players;
    }
}
