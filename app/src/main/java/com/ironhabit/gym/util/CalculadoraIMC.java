package com.ironhabit.gym.util;

/** IMC = peso(kg) / estatura(m)^2. Sin dependencias de Android para poder probarla en JVM. */
public final class CalculadoraIMC {

    private CalculadoraIMC() { }

    public static double calcular(double pesoKg, double estaturaCm) {
        if (pesoKg <= 0 || estaturaCm <= 0) {
            throw new IllegalArgumentException("Peso y estatura deben ser mayores que cero");
        }
        double metros = estaturaCm / 100.0;
        return pesoKg / (metros * metros);
    }

    public static String clasificar(double imc) {
        if (imc < 18.5) return "Bajo peso";
        if (imc < 25)   return "Normal";
        if (imc < 30)   return "Sobrepeso";
        return "Obesidad";
    }
}