package fr.hopelessworld.plugin.analyzer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import fr.hopelessworld.plugin.strategy.GeneratorStrategy;

public class TestStrategy implements GeneratorStrategy {

	@Override
	public void generate(File file, Collection<AnalizedEntity> entities) throws IOException {
		assertNotNull("No entity Found", entities);
		assertEquals("Not only 3 entities found", 3, entities.size());

		AnalizedEntity firstEntity = null;
		AnalizedEntity secondtEntity = null;
		AnalizedEntity thirdEntity = null;
		AnalizedEntity[] array = entities.toArray(new AnalizedEntity[0]);

		for (AnalizedEntity analizedEntity : array) {
			if (analizedEntity.getSimpleName().equals("FirstEntity")) {
				firstEntity = analizedEntity;
			} else if (analizedEntity.getSimpleName().equals("SecondEntity")) {
				secondtEntity = analizedEntity;
			} else {
				thirdEntity = analizedEntity;
			}
		}

		assertEquals("Name FirstEntity", "FirstEntity", firstEntity.getSimpleName());
		assertEquals("Name SecondEntity", "SecondEntity", secondtEntity.getSimpleName());
		assertNotNull("thirdEntity is null", thirdEntity);
		assertEquals("Name ThirdEntity", "ThirdEntity", thirdEntity.getSimpleName());

		// Second entity doit avoir un champ firstEntity
		Field firstEntityOfSecond = null;
		for (Field field : secondtEntity.getFields()) {
			if (StringUtils.equals(field.getSimpleName(), "firstEntity")) {
				firstEntityOfSecond = field;
			}
		}
		assertNotNull("firstEntity not Found", firstEntityOfSecond);
		assertNotNull("No entity attached", firstEntityOfSecond.asAnalyzedEntity());
		assertEquals("Entity attached is not First", firstEntity, firstEntityOfSecond.asAnalyzedEntity());

		// Third entity doit avoir un champ firstEntities
		Field firstEntityOfThird = null;
		Field listInteger = null;
		for (Field field : thirdEntity.getFields()) {
			if (StringUtils.equals(field.getSimpleName(), "firstEntities")) {
				firstEntityOfThird = field;
			} else if (StringUtils.equals(field.getSimpleName(), "integers")) {
				listInteger = field;
			}
		}

		assertNotNull("firstEntities not Found", firstEntityOfThird);
		assertNotNull("No entity attached for firstEntities", firstEntityOfThird.asAnalyzedEntity());
		assertEquals("Entity attached is not First", firstEntity, firstEntityOfThird.asAnalyzedEntity());

		assertNotNull("integers not Found", listInteger);
		assertNull("Entity attached for integers", listInteger.asAnalyzedEntity());

	}
}
