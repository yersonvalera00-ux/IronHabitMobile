package com.ironhabit.gym.data;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AuthRepositoryTest {

    private final AuthRepository auth = new AuthRepository();

    @Test
    public void autenticar_credencialesCorrectas_devuelveTrue() {
        assertTrue(auth.autenticar("yerson@control.com", "admin123"));
    }

    @Test
    public void autenticar_passwordIncorrecta_devuelveFalse() {
        assertFalse(auth.autenticar("yerson@control.com", "otra"));
    }
}