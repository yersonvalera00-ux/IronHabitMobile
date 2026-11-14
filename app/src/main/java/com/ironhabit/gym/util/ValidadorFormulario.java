package com.ironhabit.gym.util;

import java.util.regex.Pattern;

/** Reglas de validación de formularios. Sin clases de Android para poder probarlas. */
public final class ValidadorFormulario {

    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern TELEFONO = Pattern.compile("^[0-9]{7,10}$");

    private ValidadorFormulario() { }

    public static boolean noVacio(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static boolean esEmailValido(String s) {
        return s != null && EMAIL.matcher(s.trim()).matches();
    }

    public static boolean esTelefonoValido(String s) {
        return s != null && TELEFONO.matcher(s.trim()).matches();
    }

    public static boolean esPasswordValida(String s) {
        return s != null && s.length() >= 6;
    }

    /** Convierte a double y devuelve -1 si el texto no es un número mayor que cero. */
    public static double parsearPositivo(String s) {
        try {
            double v = Double.parseDouble(s.trim());
            return v > 0 ? v : -1;
        } catch (NullPointerException | NumberFormatException e) {
            return -1;
        }
    }
}