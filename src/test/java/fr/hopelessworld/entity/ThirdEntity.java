package fr.hopelessworld.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * The Class FirstEntity.
 */
@Entity
public class ThirdEntity {

	/** The id. */
	@Id
	@Column
	@GeneratedValue
	private Long id;

	/** The name. */
	@Column
	private String name;

	/** The first entity. */
	@OneToMany
	private List<FirstEntity> firstEntities;

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
	 * Gets the first entities.
	 *
	 * @return the first entities
	 */
	public List<FirstEntity> getFirstEntities() {
		return firstEntities;
	}

	/**
	 * Sets the first entities.
	 *
	 * @param firstEntities
	 *            the new first entities
	 */
	public void setFirstEntities(List<FirstEntity> firstEntities) {
		this.firstEntities = firstEntities;
	}

}
