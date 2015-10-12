package fr.hopelessworld.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The Class FirstEntity.
 */
@Entity
public class SecondEntity {

    /** The id. */
    @Id
    @Column
    @GeneratedValue
    private Long id;

    /** The name. */
    @Column
    private String name;

    /** The first entity. */
    @ManyToOne
    @JoinColumn(name = "idFirstEntity", nullable = true)
    private FirstEntity firstEntity;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name
     *            the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the first entity.
     *
     * @return the first entity
     */
    public FirstEntity getFirstEntity() {
        return firstEntity;
    }

    /**
     * Sets the first entity.
     *
     * @param firstEntity
     *            the new first entity
     */
    public void setFirstEntity(FirstEntity firstEntity) {
        this.firstEntity = firstEntity;
    }

}
