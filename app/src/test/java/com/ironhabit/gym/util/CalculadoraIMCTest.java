package com.ironhabit.gym.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculadoraIMCTest {

    @Test
    public void calcular_pesoYEstaturaValidos_devuelveImcEsperado() {
        // 70 / (1.75^2) = 22.857
        assertEquals(22.857, CalculadoraIMC.calcular(70, 175), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calcular_pesoCero_lanzaExcepcion() {
        CalculadoraIMC.calcular(0, 175);
    }

    @Test
    public void clasificar_rangos_correctos() {
        assertEquals("Bajo peso", CalculadoraIMC.clasificar(17.0));
        assertEquals("Normal", CalculadoraIMC.clasificar(22.0));
        assertEquals("Sobrepeso", CalculadoraIMC.clasificar(27.0));
        assertEquals("Obesidad", CalculadoraIMC.clasificar(32.0));
    }
}