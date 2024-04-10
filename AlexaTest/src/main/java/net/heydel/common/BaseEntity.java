package net.heydel.common;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Die BaseEntity-Klasse ist eine abstrakte, annotierte Superklasse, die eine
 * gemeinsame Grundlage für alle Entitätsklassen bereitstellt. Sie enthält eine
 * ID, die von den abgeleiteten Klassen verwendet wird.
 */
@MappedSuperclass
public abstract class BaseEntity {
	/**
	 * Die eindeutige Identifikationsnummer (ID) der Entität.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
