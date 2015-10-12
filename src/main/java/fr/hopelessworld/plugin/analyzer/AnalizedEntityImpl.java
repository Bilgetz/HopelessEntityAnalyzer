package fr.hopelessworld.plugin.analyzer;

import java.util.Collection;

/**
 * The Class AnalizedEntityImpl.
 */
public class AnalizedEntityImpl implements AnalizedEntity {

    /** The simple name. */
    private String simpleName;
    
    
    /** The field. */
    private Collection<Field> fields;
    

    /* (non-Javadoc)
     * @see demo.plugin.analyzer.AnalizedEntity#getSimpleName()
     */
    public String getSimpleName() {
        return simpleName;
    }

    /**
     * Sets the simple name.
     *
     * @param simpleName the new simple name
     */
    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    /* (non-Javadoc)
     * @see demo.plugin.analyzer.AnalizedEntity#getFields()
     */
    public Collection<Field> getFields() {
        return fields;
    }

    /**
     * Sets the fields.
     *
     * @param fields the new fields
     */
    public void setFields(Collection<Field> fields) {
        this.fields = fields;
    }


    
}
