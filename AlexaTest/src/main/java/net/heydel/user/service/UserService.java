package net.heydel.user.service;

import java.util.List;
import java.util.Optional;

import javax.naming.AuthenticationException;

import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.heydel.user.domain.User;

/**
 * Eine Schnittstelle, die Methoden für die Verwaltung von Usern bereitstellt.
 */
public interface UserService {
	/**
	 * Gibt eine Liste aller User zurück.
	 *
	 * @return Liste aller User.
	 */
	List<User> getAllUsers();

	/**
	 * Fügt einen neuen User hinzu.
	 *
	 * @param user Der hinzuzufügende Kunde.
	 */
	void addUser(@NonNull User user);

	/**
	 * Sucht einen User anhand seiner eindeutigen ID.
	 *
	 * @param userId Die eindeutige ID des User.
	 * @return Ein Optional, das den gefundenen User enthält oder leer ist, wenn
	 *         kein Kunde mit der angegebenen ID gefunden wurde.
	 */
	Optional<User> getUserById(@NonNull Integer userId);

	/**
	 * Aktualisiert die Informationen eines vorhandenen User.
	 *
	 * @param user Der zu aktualisierende user.
	 */
	void updateUser(@NonNull User user);

	/**
	 * Sucht einen User anhand seines Login-Namens.
	 *
	 * @param name Der Login-Name des user.
	 * @return Der gefundene User oder null, wenn kein User mit dem angegebenen
	 *         Login-Namen gefunden wurde.
	 */
	User findByLoginName(@NonNull String name);

	/**
	 * Registriert einen neuen User.
	 *
	 * @param user Der zu registrierende User.
	 */
	void registerUser(@NonNull User user) throws IllegalArgumentException;

	public User getAuthenticatedUser() throws AuthenticationException, UsernameNotFoundException;
}
