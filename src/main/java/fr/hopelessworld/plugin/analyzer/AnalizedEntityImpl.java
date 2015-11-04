package fr.hopelessworld.plugin.analyzer;

import java.util.Collection;

import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;

/**
 * The Class AnalizedEntityImpl.
 */
public class AnalizedEntityImpl implements AnalizedEntity {

	/** The simple name. */
	private String simpleName;

	/** The field. */
	private Collection<Field> fields;

	/** The e. */
	private Element element;

	/**
	 * Instantiates a new analized entity impl.
	 *
	 * @param element
	 *            the element
	 */
	public AnalizedEntityImpl(Element element) {
		super();
		this.element = element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see demo.plugin.analyzer.AnalizedEntity#getSimpleName()
	 */
	public String getSimpleName() {
		return simpleName;
	}

	/**
	 * Sets the simple name.
	 *
	 * @param simpleName
	 *            the new simple name
	 */
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see demo.plugin.analyzer.AnalizedEntity#getFields()
	 */
	public Collection<Field> getFields() {
		return fields;
	}

	/**
	 * Sets the fields.
	 *
	 * @param fields
	 *            the new fields
	 */
	public void setFields(Collection<Field> fields) {
		this.fields = fields;
	}

	@Override
	public Element advanced() {
		return this.element;
	}

	@Override
	public CharSequence getPackageName() {
		PackageElement packageElement = (PackageElement) this.element.getEnclosingElement();
		return packageElement.getQualifiedName();
	}

}
