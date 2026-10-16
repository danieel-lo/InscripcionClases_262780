/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Presentacion;

import Negocio.Inscripcion;

/**
 *
 * @author Jorge
 */
public class InscripcionClases_262780 {

    public static void main(String[] args) {
        Inscripcion modelo = new Inscripcion();
        ControlInscripcion controlador = new ControlInscripcion(modelo);
        PantallaInscripcion pantalla = new PantallaInscripcion(controlador, modelo);
        modelo.agregarObservador(pantalla);
        pantalla.iniciarInscripcion();
    }
}
