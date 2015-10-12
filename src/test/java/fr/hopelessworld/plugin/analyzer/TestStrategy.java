package fr.hopelessworld.plugin.analyzer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import fr.hopelessworld.plugin.strategy.GeneratorStrategy;

public class TestStrategy implements GeneratorStrategy {

    @Override
    public void generate(File file, Collection<AnalizedEntity> entities) throws IOException {
        assertNotNull("No entity Found", entities);
        assertEquals("Not only 2 entities found", 2, entities.size());

        AnalizedEntity firstEntity;
        AnalizedEntity secondtEntity;
        AnalizedEntity[] array = entities.toArray(new AnalizedEntity[0]);

        if (array[0].getSimpleName().equals("FirstEntity")) {
            firstEntity = array[0];
            secondtEntity = array[1];
        } else {
            firstEntity = array[1];
            secondtEntity = array[0];
        }

        assertEquals("Name FirstEntity", "FirstEntity", firstEntity.getSimpleName());
        assertEquals("Name SecondEntity", "SecondEntity", secondtEntity.getSimpleName());

        // Second entity doit avoir un champ firstEntity
        Field firstEntityOfSecond = null;
        for (Field field : secondtEntity.getFields()) {
            if (StringUtils.equals(field.getSimpleName(), "firstEntity")) {
                firstEntityOfSecond = field;
            }
        }
        assertNotNull("firstEntity not Found", firstEntityOfSecond);
        assertNotNull("No entity attached", firstEntityOfSecond.asAnalyzedEntity());

    }
}
