/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

import Negocio.Curso;
import Negocio.Inscripcion;
import Negocio.ObservadorInscripcion;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Jorge
 */
public class PantallaInscripcion implements ObservadorInscripcion {

    private ControlInscripcion controlador;
    private Inscripcion modelo;

    public PantallaInscripcion(ControlInscripcion controlador, Inscripcion modelo) {
        this.controlador = controlador;
        this.modelo = modelo;
    }

    @Override
    public void actualizar() {
        actualizarVista();
    }

    public void actualizarVista() {
        mostrarListasCursos();
    }

    public void mostrarListasCursos() {
        Map<String, List<Curso>> listas = controlador.iniciarInscripcion();
        List<Curso> disponibles = listas.get("disponibles");
        List<Curso> inscritos = listas.get("inscritos");
        System.out.println("\nCursos disponibles                 Cursos inscritos");
        int filas = Math.max(disponibles.size(), inscritos.size());
        for (int i = 0; i < filas; i++) {
            String disponible = "";
            String inscrito = "";
            if (i < disponibles.size()) {
                Curso curso = disponibles.get(i);
                disponible = (i + 1) + ". " + curso.getNombre() + " $" + curso.getPrecio();
            }
            if (i < inscritos.size()) {
                Curso curso = inscritos.get(i);
                inscrito = (i + 1) + ". " + curso.getNombre() + " $" + curso.getPrecio();
            }
            System.out.printf("%-35s %s%n", disponible, inscrito);
        }
        if (inscritos.isEmpty()) {
            System.out.println("                                   Ninguno");
        }

        System.out.println("                                   Total: $" + modelo.getTotal());
    }

    public void seleccionarCurso(int numeroCurso) {
        boolean inscrito = controlador.seleccionarCurso(numeroCurso);
        if (!inscrito) {
            mostrarAlertaCursoInvalido();
        }
    }

    public void mostrarAlertaCursoInvalido() {
        System.out.println("Error: El curso seleccionado no es valido.");
    }

    public void finalizarInscripcion() {
        String fichaPago = controlador.finalizarInscripcion();
        mostrarFichaPago(fichaPago);
    }

    public void mostrarFichaPago(String fichaPago) {
        System.out.println(fichaPago);
    }

    public void iniciarInscripcion() {
        Scanner teclado = new Scanner(System.in);

        mostrarListasCursos();

        int opcion = -1;

        while (opcion != 0) {
            System.out.print("Selecciona un curso (0 para finalizar): ");

            if (teclado.hasNextInt()) {
                opcion = teclado.nextInt();

                if (opcion == 0) {
                    finalizarInscripcion();
                } else {
                    seleccionarCurso(opcion);
                }
            } else {
                teclado.next();
                mostrarAlertaCursoInvalido();
            }
        }
    }

}
