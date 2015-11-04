package fr.hopelessworld.plugin.analyzer;

import java.util.Collection;

import javax.lang.model.element.Element;

/**
 * The Interface AnalizedEntity.
 */
public interface AnalizedEntity {

	/**
	 * Gets the simple name.
	 *
	 * @return the simple name
	 */
	String getSimpleName();

	/**
	 * Gets the package name.
	 *
	 * @return the package name
	 */
	CharSequence getPackageName();

	/**
	 * Gets the fields.
	 *
	 * @return the fields
	 */
	Collection<Field> getFields();

	/**
	 * get Element for advanced purpose.
	 *
	 * @return the element
	 */
	Element advanced();

}
