/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

import Negocio.Curso;
import Negocio.Inscripcion;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jorge
 */
public class ControlInscripcion {

    private Inscripcion modelo;

    public ControlInscripcion(Inscripcion modelo) {
        this.modelo = modelo;
    }

    public Map<String, List<Curso>> iniciarInscripcion() {
        return modelo.obtenerListasCursos();
    }

    public boolean seleccionarCurso(int numeroCurso) {
        if (!modelo.validarCurso(numeroCurso)) {
            return false;
        }
        return modelo.inscribirCurso(numeroCurso);
    }

    public String finalizarInscripcion() {
        return modelo.finalizarInscripcion();
    }
    
    

}
