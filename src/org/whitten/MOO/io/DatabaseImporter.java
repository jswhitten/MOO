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
    
    private final File file;

    public DatabaseImporter(File file) {
        this.file = file;
    }
    
    public Database read() throws FileNotFoundException, InvalidDatabaseException {
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
                {Integer.toString(db.getVersion()),
                Integer.toString(numObjects), 
                Integer.toString(numVerbs), 
                Integer.toString(numPlayers)};
        LOGGER.log(Level.INFO, "Database version {0} - {1} objects, {2} verbs, {3} players", dbIntro);
        
        // Player block
        List<ObjType> players = new ArrayList<>();
        LOGGER.info("Scanning players");
        for(int i = 0; i < numPlayers; i++) {
            ObjType playerObj = new ObjType(s.nextInt());
            players.add(playerObj);
            LOGGER.info("    Added player " + playerObj);
        }
        db.setPlayers(players);
        s.nextLine();
        
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
            //LOGGER.info("next line: " + objNumLine);
            Pattern p = Pattern.compile("#(\\d+)");
            Matcher m = p.matcher(objNumLine);
            if(m.find()) {
                ObjType objNum = new ObjType(Integer.parseInt(m.group(1)));
                LOGGER.info("    Reading object " + objNum.toString());
                if(objNumLine.contains("recycled")) {
                    // Recycled object
                    MooObject obj = new MooObject(objNum);
                    try {
                        db.addObject(obj);
                    } catch(InvalidObjectException e) {
                        throw new InvalidDatabaseException(e);
                    }
                } else {
                    // Not recycled
                    String name = s.nextLine();
                    s.nextLine(); // dummy
                    ObjType flags = new ObjType(s.nextInt()); // TODO
                    MooObject owner = db.getObject(new ObjType(s.nextInt()));
                    MooObject location = db.getObject(new ObjType(s.nextInt()));
                    s.nextLine(); // first object in contents
                    s.nextLine(); // next object in location's contents
                    MooObject parent = db.getObject(new ObjType(s.nextInt()));
                    s.nextLine(); // first child object
                    s.nextLine(); // next child of object's parent
                    
                    MooObject obj = new MooObject(objNum, name, owner, parent);
                    obj.setLocation(location);
                    
                    try {
                        db.addObject(obj);
                    } catch(InvalidObjectException e) {
                        throw new InvalidDatabaseException(e);
                    }
                    
                    // TODO Verb definitions
                    
                    // TODO Property names
                    
                    // TODO Property definitions
                    // hack
                    while(!s.hasNext("#(.*)") && s.hasNextLine()) {
                        s.nextLine();
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
