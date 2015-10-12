package fr.hopelessworld.plugin.strategy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.lang3.Validate;

import fr.hopelessworld.plugin.analyzer.AnalizedEntity;

public abstract class AbstractUniqueFileGeneratorStrategy implements GeneratorStrategy {

    public abstract CharSequence generate(Collection<AnalizedEntity> entities);

    public void generate(File file, Collection<AnalizedEntity> entities) throws IOException {
        Validate.notNull(file, "File need to be passed");
        Validate.isTrue(!file.isDirectory(), "File is a directory");

        CharSequence output = this.generate(entities);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileWriter fs = new FileWriter(file);
        fs.append(output);
        fs.close();
    }

}
