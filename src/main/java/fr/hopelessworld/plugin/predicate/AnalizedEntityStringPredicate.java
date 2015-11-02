package fr.hopelessworld.plugin.predicate;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import fr.hopelessworld.plugin.analyzer.AnalizedEntity;

/**
 * The Class SimpleNameFieldPredicate.
 */
public class AnalizedEntityStringPredicate implements Predicate<AnalizedEntity> {

	/** The simple name. */
	private final String fieldTypeName;

	/**
	 * Instantiates a new simple name field predicate.
	 *
	 * @param simpleName
	 *            the simple name
	 */
	public AnalizedEntityStringPredicate(String fieldName) {
		super();
		Validate.notNull(fieldName, "can't be null or blank");
		this.fieldTypeName = fieldName;
	}

	public boolean evaluate(AnalizedEntity object) {
		if (object == null) {
			return false;
		}

		return StringUtils.equals(fieldTypeName, object.getSimpleName());
	}

}
