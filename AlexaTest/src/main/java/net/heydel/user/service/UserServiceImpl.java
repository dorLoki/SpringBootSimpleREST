package net.heydel.user.service;

import java.util.List;
import java.util.Optional;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.heydel.user.domain.User;
import net.heydel.user.domain.UserRepository;


/**
 * Die UserServiceImpl-Klasse implementiert die UserService-Schnittstelle und
 * bietet die Logik für die Verwaltung von User an.
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void addUser(@NonNull User user) {
		userRepository.save(user);
	}

	@Override
	public Optional<User> getUserById(@NonNull Integer userId) {
		return userRepository.findById(userId);
	}

	@Override
	public void updateUser(@NonNull User user) {
		userRepository.save(user);
	}

	@Override
	public User findByLoginName(@NonNull String name) {
		return userRepository.findByLoginName(name);
	}

	@Override
	public void registerUser(@NonNull User user) throws IllegalArgumentException {
		// check id for duplicates
		Integer id = user.getId();
		if (id != null && userRepository.existsById(id)) {
			throw new IllegalArgumentException("Benutzer schon vergeben.");
		}

		// check name
		String name = user.getName();
		stringValidation(name, "Benutzername");

		// check login name
		String loginName = user.getLoginName();
		if (userRepository.findByLoginName(loginName) != null) {
			throw new IllegalArgumentException("Anmelde-Name schon vergeben.");
		}
		stringValidation(loginName, "Anmelde-Name");

		// check password
		String password = user.getPassword();
		passwordValidation(password);
		userRepository.save(user);
	}

	private void stringValidation(String string, @NonNull String type) {
		if (string == null || string.isEmpty()) {
			throw new IllegalArgumentException(type + " ist erforderlich.");
		}
		if (string.length() < 3) {
			throw new IllegalArgumentException(type + " muss mindestens 3 Zeichen lang sein.");
		}
		if (string.length() > 64) {
			throw new IllegalArgumentException(type + " darf höchstens 64 Zeichen lang sein.");
		}
		if (string.matches(".*\\s.*")) {
			throw new IllegalArgumentException(type + " darf keine Leerzeichen enthalten.");
		}
	}

	private void passwordValidation(String string) {
		final String type = "Passwort";
		if (string == null || string.isEmpty()) {
			throw new IllegalArgumentException(type + " ist erforderlich.");
		}
		if (string.length() < 8) {
			throw new IllegalArgumentException(type + " muss mindestens 8 Zeichen lang sein.");
		}
		if (string.length() > 64) {
			throw new IllegalArgumentException(type + " darf höchstens 64 Zeichen lang sein.");
		}
		if (string.matches(".*\\s.*")) {
			throw new IllegalArgumentException(type + " darf keine Leerzeichen enthalten.");
		}
		if (string.contains(string.toLowerCase())) {
			throw new IllegalArgumentException(type + " muss mindestens einen Großbuchstaben enthalten");
		}
		if (string.contains(string.toUpperCase())) {
			throw new IllegalArgumentException(type + " muss mindestens einen Kleinbuchstaben enthalten");
		}
	}

	@Override
	public User getAuthenticatedUser() throws AuthenticationException, UsernameNotFoundException{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || !authentication.isAuthenticated()) {
			throw new AuthenticationException("User not authenticated");
		}
		String loginName = authentication.getName();
		User user = userRepository.findByLoginName(loginName);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return user;
	}
}
