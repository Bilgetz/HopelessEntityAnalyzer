package fr.hopelessworld.plugin.analyzer;

import java.lang.annotation.Annotation;
import java.util.Collection;

import javax.lang.model.type.TypeMirror;

public interface Field {

    CharSequence getSimpleName();

    Collection<Annotation> getAnnotations();

    /**
     * Gets the annotation.
     *
     * @see javax.lang.model.element.Element#getAnnotation(Class)
     * @param <A>
     *            the generic type
     * @param annotationType
     *            the annotation type
     * @return the annotation
     */
    <A extends Annotation> A getAnnotation(Class<A> annotationType);

    /**
     * Returns the type defined by this element.
     * 
     * @see javax.lang.model.element.Element#asType()
     * @return the type mirror
     */
    TypeMirror asType();

    /**
     * Return an AnalyzedEnity if the type is know as an entity.
     *
     * @return the analized entity
     */
    AnalizedEntity asAnalyzedEntity();

}
