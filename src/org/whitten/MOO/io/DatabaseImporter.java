package org.whitten.MOO.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.whitten.MOO.Database;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public class DatabaseImporter {
    private final static Logger LOGGER = Logger.getLogger(DatabaseImporter.class.getName());
    
    public Database read(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        LOGGER.info("Reading " + file.getName());
    
        // Intro block
        String header = s.nextLine();
        Database db = new Database(parseHeader(header));
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
        List players = new ArrayList();
        LOGGER.info("Scanning players");
        for(int i = 0; i < numPlayers; i++) {
            players.add(s.nextInt());
        }
        
        
        // Object block
        LOGGER.info("Scanning objects");
        scanObjects(db, s);
        
        // Verb block
        LOGGER.info("Scanning verb code");
        scanVerbs(db, s);
        
        // Status block
        // TODO
        LOGGER.info("Skipping status block");
        
        LOGGER.info("Done");
        
        return db;
    }

    private Integer parseHeader(String header) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void scanObjects(Database db, Scanner s) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void scanVerbs(Database db, Scanner s) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
