package fr.hopelessworld.plugin.strategy;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import fr.hopelessworld.plugin.analyzer.AnalizedEntity;

public interface GeneratorStrategy {

    /**
     * Generate.
     *
     * @param file the output file
     * @param entities list of entities found
     * @throws IOException 
     */
    void generate(File file, Collection<AnalizedEntity> entities) throws IOException;
    
}
