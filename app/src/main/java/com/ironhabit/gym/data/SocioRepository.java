package com.ironhabit.gym.data;

import com.ironhabit.gym.model.Socio;

import java.util.ArrayList;
import java.util.List;

/** Repositorio en memoria. Se reemplaza por una implementación con Retrofit cuando exista la API. */
public class SocioRepository {

    private static SocioRepository instancia;

    private final List<Socio> socios = new ArrayList<>();
    private int siguienteId = 1;

    public SocioRepository() {
        // Datos semilla para que la lista no aparezca vacía
        socios.add(new Socio(siguienteId++, "Yerson Valera", "yerson@correo.com", "3001234567",
                "Plan Trimestral", 78, 175));
        socios.add(new Socio(siguienteId++, "Ana María Pérez", "ana@correo.com", "3109876543",
                "Plan Mensual General", 60, 165));
    }

    /** Instancia compartida por toda la app (patrón Singleton). */
    public static synchronized SocioRepository getInstancia() {
        if (instancia == null) instancia = new SocioRepository();
        return instancia;
    }

    public synchronized List<Socio> listar() {
        return new ArrayList<>(socios); // copia defensiva
    }

    public synchronized Socio guardar(String nombre, String email, String telefono,
                                      String membresia, double peso, double estatura) {
        Socio nuevo = new Socio(siguienteId++, nombre, email, telefono, membresia, peso, estatura);
        socios.add(nuevo);
        return nuevo;
    }
}