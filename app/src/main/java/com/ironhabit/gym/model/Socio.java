package com.ironhabit.gym.model;

/** Entidad Socio. Equivale a la tabla `socios` de iron_habit_database. */
public class Socio {

    private final int id;
    private final String nombre;
    private final String email;
    private final String telefono;
    private final String membresia;
    private final double peso;        // kg; 0 si no hay ficha
    private final double estaturaCm;  // cm; 0 si no hay ficha

    public Socio(int id, String nombre, String email, String telefono,
                 String membresia, double peso, double estaturaCm) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.membresia = membresia;
        this.peso = peso;
        this.estaturaCm = estaturaCm;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public String getMembresia() { return membresia; }
    public double getPeso() { return peso; }
    public double getEstaturaCm() { return estaturaCm; }

    /** Devuelve hasta dos iniciales del nombre, por ejemplo "YV" para "Yerson Valera". */
    public String getIniciales() {
        String[] partes = nombre.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(2, partes.length); i++) {
            sb.append(Character.toUpperCase(partes[i].charAt(0)));
        }
        return sb.toString();
    }
}