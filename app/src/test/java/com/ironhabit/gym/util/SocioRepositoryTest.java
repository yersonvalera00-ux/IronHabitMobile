package com.ironhabit.gym.data;

import com.ironhabit.gym.model.Socio;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SocioRepositoryTest {

    private SocioRepository repo;

    @Before
    public void setUp() {
        repo = new SocioRepository(); // instancia nueva para cada prueba
    }

    @Test
    public void listar_alInicio_tieneDatosSemilla() {
        assertEquals(2, repo.listar().size());
    }

    @Test
    public void guardar_agregaSocioConIdSecuencial() {
        Socio s = repo.guardar("Carlos Ruiz", "carlos@correo.com", "3111234567",
                "Plan Anual VIP", 80, 180);
        assertEquals(3, s.getId());
        assertEquals(3, repo.listar().size());
    }

    @Test
    public void getIniciales_devuelveDosLetras() {
        Socio s = new Socio(1, "yerson valera", "a@b.com", "3000000", "x", 70, 170);
        assertEquals("YV", s.getIniciales());
    }
}