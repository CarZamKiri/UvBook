package UvBook;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Generar el hash de la contraseña
    public static String hashPassword(String password) {
        return encoder.encode(password);
    }

    // Validar una contraseña ingresada contra el hash almacenado
    public static boolean verifyPassword(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }
}

