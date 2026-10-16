/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jorge
 */
public class Inscripcion implements SujetoInscripcion {

    private List<Curso> cursosDisponibles;
    private List<Curso> cursosInscritos;
    private double total;
    private List<ObservadorInscripcion> observadores;

    public Inscripcion() {
        cursosDisponibles = new ArrayList<>();
        cursosInscritos = new ArrayList<>();
        observadores = new ArrayList<>();
        total = 0.0;
        cursosDisponibles.add(new Curso("Matematicas", 350.00));
        cursosDisponibles.add(new Curso("Programacion", 450.00));
        cursosDisponibles.add(new Curso("Ingles", 300.00));
    }

    public boolean validarCurso(int numeroCurso) {
        return numeroCurso >= 1 && numeroCurso <= cursosDisponibles.size();
    }

    public boolean inscribirCurso(int numeroCurso) {
        if (validarCurso(numeroCurso) == false) {
            return false;
        }
        actualizarListasCursos(numeroCurso);
        calcularTotal();
        notificarObservadores();
        return true;
    }

    private void actualizarListasCursos(int numeroCurso) {
        Curso curso = cursosDisponibles.remove(numeroCurso - 1);
        cursosInscritos.add(curso);
    }

    private double calcularTotal() {
        total = 0.0;
        cursosInscritos.forEach(p -> total += p.getPrecio());
        return total;
    }

    @Override
    public void agregarObservador(ObservadorInscripcion observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    @Override
    public void notificarObservadores() {
        observadores.forEach(p -> p.actualizar());
    }

    public String finalizarInscripcion() {
        return generarFichaPago();
    }

    private String generarFichaPago() {
        StringBuilder ficha = new StringBuilder();
        ficha.append("---------- FICHA DE PAGO ----------\n");
        ficha.append("Cursos inscritos:\n");
        int numero = 1;
        for (Curso curso : cursosInscritos) {
            ficha.append(numero).append(". ").append(curso.getNombre()).append(" - $").append(String.format("%.2f", curso.getPrecio())).append("\n");
            numero++;
        }
        ficha.append("-----------------------------------\n");
        ficha.append("Total a pagar: $")
                .append(String.format("%.2f", total))
                .append("\n");
        ficha.append("-----------------------------------");
        return ficha.toString();
    }

    public Map<String, List<Curso>> obtenerListasCursos() {
        Map<String, List<Curso>> listas = new HashMap<>();
        listas.put("disponibles", cursosDisponibles);
        listas.put("inscritos", cursosInscritos);
        return listas;
    }

    public double getTotal() {
        return total;
    }

}
