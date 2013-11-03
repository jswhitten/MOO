package org.whitten.MOO.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.whitten.MOO.Database;
import org.whitten.MOO.exceptions.InvalidDatabaseException;
import org.whitten.MOO.exceptions.InvalidObjectException;
import org.whitten.MOO.object.MooObject;
import org.whitten.MOO.type.ObjType;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public class DatabaseImporter {
    private final static Logger LOGGER = Logger.getLogger(DatabaseImporter.class.getName());
    
    public Database read(File file) throws FileNotFoundException, InvalidDatabaseException {
        Scanner s = new Scanner(file);
        Database db = null;
        
        LOGGER.info("Reading " + file.getName());
    
        // Intro block
        String header = s.nextLine();
        try {
            db = new Database(parseHeader(header));
        } catch (IllegalArgumentException e) {
            throw new InvalidDatabaseException("Database header invalid");
        }
        
        int numObjects = s.nextInt();
        int numVerbs = s.nextInt();
        s.nextInt(); // dummy
        int numPlayers = s.nextInt();
        
        String[] dbIntro = 
                {Integer.toString(numObjects), 
                Integer.toString(numVerbs), 
                Integer.toString(numPlayers)};
        LOGGER.log(Level.INFO, "Database version {0} - {1} objects, {2} verbs, {3} players", dbIntro);
        
        // Player block
        List<ObjType> players = new ArrayList<>();
        LOGGER.info("Scanning players");
        for(int i = 0; i < numPlayers; i++) {
            players.add(new ObjType(s.nextInt()));
        }
        db.setPlayers(players);
        
        // Object block
        LOGGER.info("Scanning objects");
        scanObjects(db, s, numObjects);
        
        // Verb block
        LOGGER.info("Scanning verb code");
        scanVerbs(db, s, numVerbs);
        
        // Status block
        // TODO
        LOGGER.info("Skipping status block");
        
        LOGGER.info("Done");
        
        return db;
    }

    private Integer parseHeader(String header) {
        Pattern p = Pattern.compile("\\*\\* LambdaMOO Database, Format Version (\\d+) \\*\\*");
        Matcher m = p.matcher(header);
        
        if(m.find()) {
            return Integer.parseInt(m.group(1));
        }
        
        throw new IllegalArgumentException("Not a valid LambdaMOO db header");
    }

    private void scanObjects(Database db, Scanner s, Integer numObjects) throws InvalidDatabaseException {
        for(int i = 0; i < numObjects; i++) {
            String objNumLine = s.nextLine();
            Pattern p = Pattern.compile("#(\\d+)");
            Matcher m = p.matcher(objNumLine);
            if(m.find()) {
                ObjType objNum = new ObjType(Integer.parseInt(m.group(1)));
                if(objNumLine.contains("recycled")) {
                    // Recycled object
                    MooObject obj = new MooObject(db, objNum);
                    try {
                        db.addObject(obj);
                    } catch(InvalidObjectException e) {
                        throw new InvalidDatabaseException(e);
                    }
                } else {
                    // Not recycled
                    String name = s.nextLine();
                    s.nextLine(); // dummy
                    ObjType flags = new ObjType(s.nextInt());
                    ObjType owner = new ObjType(s.nextInt());
                    ObjType location = new ObjType(s.nextInt());
                    s.nextLine(); // first object in contents
                    s.nextLine(); // next object in location's contents
                    ObjType parent = new ObjType(s.nextInt());
                    s.nextLine(); // first child object
                    s.nextLine(); // next child of object's parent
                    
                    MooObject obj = new MooObject(db, objNum, name, owner, parent);
                    try {
                        db.addObject(obj);
                    } catch(InvalidObjectException e) {
                        throw new InvalidDatabaseException(e);
                    }
                }
            } else {
                throw new InvalidDatabaseException("Error reading objects");
            }
        }
    }

    private void scanVerbs(Database db, Scanner s, Integer numVerbs) {
        for(int i = 0; i < numVerbs; i++) {
            
        }
    }
}
