package fr.hopelessworld.plugin.analyzer;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * The Class FieldImpl.
 */
public class FieldImpl implements Field {

	/** The variable element. */
	private VariableElement variableElement;

	/** The analized entity. */
	private AnalizedEntity analizedEntity;

	/**
	 * Instantiates a new field impl.
	 *
	 * @param variableElement
	 *            the variable element
	 */
	public FieldImpl(VariableElement variableElement) {
		super();
		this.variableElement = variableElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.hopelessworld.plugin.analyzer.Field#getSimpleName()
	 */
	public CharSequence getSimpleName() {
		return variableElement.getSimpleName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.hopelessworld.plugin.analyzer.Field#getAnnotations()
	 */
	public Collection<Annotation> getAnnotations() {
		Collection<Annotation> annotations = new ArrayList<Annotation>();

		for (AnnotationMirror anotMirror : this.variableElement.getAnnotationMirrors()) {
			try {
				Class<Annotation> anot = (Class<Annotation>) Class.forName(anotMirror.getAnnotationType().toString());
				annotations.add(this.variableElement.getAnnotation(anot));
			} catch (ClassNotFoundException e1) {
				throw new IllegalArgumentException(e1);
			}
		}
		return annotations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.hopelessworld.plugin.analyzer.Field#getAnnotation(java.lang.Class)
	 */
	public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
		return this.variableElement.getAnnotation(annotationType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.hopelessworld.plugin.analyzer.Field#asType()
	 */
	public TypeMirror asType() {
		return this.variableElement.asType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.hopelessworld.plugin.analyzer.Field#asAnalyzedEntity()
	 */
	@Override
	public AnalizedEntity asAnalyzedEntity() {
		return this.analizedEntity;
	}

	/**
	 * Sets the analized entity.
	 *
	 * @param analizedEntity
	 *            the new analized entity
	 */
	public void setAnalizedEntity(AnalizedEntity analizedEntity) {
		this.analizedEntity = analizedEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.hopelessworld.plugin.analyzer.Field#advanced()
	 */
	@Override
	public VariableElement advanced() {
		return this.variableElement;
	}

}
