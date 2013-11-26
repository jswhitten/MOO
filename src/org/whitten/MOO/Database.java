package org.whitten.MOO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.whitten.MOO.exceptions.InvalidObjectException;
import org.whitten.MOO.object.MooObject;
import org.whitten.MOO.type.ObjType;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public class Database {
    private final Integer version;
    private final Map<Integer, MooObject> objects;
    private List<ObjType> players;
    
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
    
    public MooObject getObject(ObjType objNum) {
        if(objNum.getValue() == null) {
            return null;
        }
        if(!objects.containsKey(objNum.getValue())) {
            try {
                addObject(new MooObject(objNum));
            } catch (InvalidObjectException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return objects.get(objNum.getValue());
    }
    
    private void addObject(MooObject obj) throws InvalidObjectException {
        ObjType objNum = obj.getObjectNumber();
        if(!objects.containsKey(objNum.getValue())) {
            objects.put(objNum.getValue(), obj);
        } else {
            throw new InvalidObjectException("Object " + objNum.toString() + " already exists");
        }
    }

    public List<ObjType> getPlayers() {
        return players;
    }
    
    public List<MooObject> getPlayerObjects() {
        List<MooObject> playerObjects = new ArrayList<>();
        for(ObjType player : players) {
            playerObjects.add(objects.get(player.getValue()));
        }
        return playerObjects;
    }

    public void setPlayers(List<ObjType> players) {
        this.players = players;
    }
}
