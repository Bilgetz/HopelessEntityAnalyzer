package fr.hopelessworld.plugin.predicate;

import javax.lang.model.type.TypeMirror;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.Validate;

import fr.hopelessworld.plugin.analyzer.Field;

/**
 * The Class SimpleNameFieldPredicate.
 */
public class AnalizedEntityFieldPredicate extends AnalizedEntityStringPredicate {

	/**
	 * Instantiates a new simple name field predicate.
	 *
	 * @param simpleName
	 *            the simple name
	 */
	public AnalizedEntityFieldPredicate(Field field) {
		super(caculateFieldTypeName(field));
	}

	/**
	 * Caculate field type name from a field.
	 *
	 * @param field
	 *            the field
	 * @return the string
	 */
	private static String caculateFieldTypeName(Field field) {
		Validate.notNull(field, "can't be null or blank");

		String fieldTypeName = null;
		TypeMirror typeMirror = field.asType();
		String fullType = null;
		if (field.getAnnotation(ManyToOne.class) != null) {
			fullType = typeMirror.toString();
		} else if (field.getAnnotation(OneToMany.class) != null) {
			String typeString = typeMirror.toString();
			fullType = typeString.substring(typeString.indexOf('<') + 1, typeString.indexOf('>'));
		}
		fieldTypeName = fullType.substring(fullType.lastIndexOf('.') + 1);
		return fieldTypeName;
	}

}
