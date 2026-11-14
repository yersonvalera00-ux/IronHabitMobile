package com.ironhabit.gym.data;

/**
 * DEMO: credenciales fijas, igual que el administrador de la web.
 * En producción la validación debe hacerse en el servidor con password_verify().
 */
public class AuthRepository {

    private static final String ADMIN_EMAIL = "yerson@control.com";
    private static final String ADMIN_PASS = "admin123";

    public boolean autenticar(String email, String pass) {
        if (email == null || pass == null) return false;
        return ADMIN_EMAIL.equalsIgnoreCase(email.trim()) && ADMIN_PASS.equals(pass);
    }
}