package fr.hopelessworld.plugin.predicate;

import javax.lang.model.type.TypeMirror;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import fr.hopelessworld.plugin.analyzer.AnalizedEntity;
import fr.hopelessworld.plugin.analyzer.Field;

/**
 * The Class SimpleNameFieldPredicate.
 */
public class AnalizedEntityPredicate implements Predicate<AnalizedEntity> {

    /** The simple name. */
    private final String fieldTypeName;

    /**
     * Instantiates a new simple name field predicate.
     *
     * @param simpleName
     *            the simple name
     */
    public AnalizedEntityPredicate(Field field) {
        super();
        Validate.notNull(field, "can't be null or blank");

        TypeMirror typeMirror = field.asType();
        String fullType = typeMirror.toString();
        this.fieldTypeName = fullType.substring(fullType.lastIndexOf('.') + 1);
    }

    public boolean evaluate(AnalizedEntity object) {
        if (object == null) {
            return false;
        }

        return StringUtils.equals(fieldTypeName, object.getSimpleName());
    }

}
