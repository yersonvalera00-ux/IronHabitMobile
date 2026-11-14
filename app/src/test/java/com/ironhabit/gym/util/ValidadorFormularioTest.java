package com.ironhabit.gym.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidadorFormularioTest {

    @Test
    public void esEmailValido_aceptaCorreoCorrecto_yRechazaInvalido() {
        assertTrue(ValidadorFormulario.esEmailValido("yerson@control.com"));
        assertFalse(ValidadorFormulario.esEmailValido("yerson@control"));
        assertFalse(ValidadorFormulario.esEmailValido(""));
        assertFalse(ValidadorFormulario.esEmailValido(null));
    }

    @Test
    public void esTelefonoValido_entreSieteYDiezDigitos() {
        assertTrue(ValidadorFormulario.esTelefonoValido("3001234567"));
        assertFalse(ValidadorFormulario.esTelefonoValido("12345"));
        assertFalse(ValidadorFormulario.esTelefonoValido("30012abc"));
    }

    @Test
    public void esPasswordValida_minimoSeisCaracteres() {
        assertTrue(ValidadorFormulario.esPasswordValida("admin1"));
        assertFalse(ValidadorFormulario.esPasswordValida("123"));
    }

    @Test
    public void parsearPositivo_numeroValido_yTextoInvalido() {
        assertEquals(78.5, ValidadorFormulario.parsearPositivo("78.5"), 0.001);
        assertEquals(-1, ValidadorFormulario.parsearPositivo("abc"), 0.001);
        assertEquals(-1, ValidadorFormulario.parsearPositivo("0"), 0.001);
    }
}