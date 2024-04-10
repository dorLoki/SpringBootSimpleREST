package net.heydel.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Datenbank für die Entität User.
 */
public interface UserRepository extends JpaRepository<User, Integer>{
	/**
	 * Sucht einen Kunden anhand seines Benutzernamens.
	 *
	 * @param username Der Benutzername des Kunden.
	 * @return Der gefundene Kunde oder {@code null}, wenn kein Kunde mit dem
	 *         angegebenen Benutzernamen gefunden wurde.
	 */
	User findByLoginName(String username);
}
