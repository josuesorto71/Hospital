/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Paciente;
import vista.Emergencia;

/**
 *
 * @author Milton Josué Díaz Sorto
 */
public class EmergenciaController implements ActionListener {

    /**
     * Constructor de la Clase Emergencia Controller
     *
     * @param Emergencia JFrame o Ventana
     * @param Paciente Modelo de datos de tipo paciente
     */
    public EmergenciaController(Emergencia Emergencia, Paciente Paciente) {
        ListaEmergencia = new ArrayList<>();
        ListaBasica = new ArrayList<>();
        this.Emergencia = Emergencia;
        this.Paciente = Paciente;

        Emergencia.btnAgregar.addActionListener(this);
        Emergencia.btnAtender.addActionListener(this);
        Emergencia.btnAtender.setEnabled(false);
        Emergencia.txtNumeroPacientes.setText("Pacientes Actuales: " + NumeroPacientes);
    }

    /**
     * Metodo sobrecargado para capturar eventos de la ventana
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        AgregarListaEspera(actionEvent);
        AtenderPaciente(actionEvent);
        RefrescarPantalla();
    }

    //Metodo para agragr paciente a la lista
    public void AgregarListaEspera(ActionEvent actionEvent) {
        if (actionEvent.getSource() == Emergencia.btnAgregar) { //Verificamos el elemento que produce el evento
            //Leemos los datos del formulario
            int edad = ValidarNumero(Emergencia.txtEdadPaciente.getText());
            String nombre = Emergencia.txtNombrePaciente.getText();
            String padecimiento = Emergencia.txtPadecimientoPaciente.getText();
            String llamada = (String) Emergencia.cbxTipoLlamada.getSelectedItem();

            if (nombre.length() > 0 && padecimiento.length() > 0 && edad >= 0) {
                Paciente = new Paciente(edad, nombre, padecimiento, llamada); //cargamos datos al modelo
                TipoLista(llamada, Paciente); //Verificamos el tipo de llamada y guardamos el paciente
                LimpiarCajas(); //Limpiamos las cajas
            } else {
                JOptionPane.showMessageDialog(null, "Por favor complete todo los campos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Si ocurre algun error al parsear el texto a numero
    public int ValidarNumero(String numero) {
        try {
            return Integer.parseInt(numero);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    //Metodo para atender un paciente
    public void AtenderPaciente(ActionEvent actionEvent) {
        if (actionEvent.getSource() == Emergencia.btnAtender) { //Verifiquemos que elmento produce el evento
            if (ListaEmergencia.isEmpty()) {//Si la lista de emergencia esta vacia atendemos los pacientes basicos
                JOptionPane.showMessageDialog(null, CargarLista(ListaBasica), "Pacientes Basicos Pendientes", JOptionPane.INFORMATION_MESSAGE);
            } else { //si hay pacientes de emergencia los atendemos primero
                JOptionPane.showMessageDialog(null, CargarLista(ListaEmergencia), "Pacientes De Emergencia Pendientes", JOptionPane.INFORMATION_MESSAGE);
            }
            NumeroPacientes--; //descontamos pacientes atendidos
        }
    }

    //Miembro estatico que permite generar una lista de pacientes
    public static String CargarLista(ArrayList<Paciente> listaPacientes) {
        int aux = 0;
        String informacion = "";
        try {
            for (Paciente paciente : listaPacientes) { //Recorremos la lista de pacientes
                aux++;
                informacion
                        += "EXPEDIENTE: " + aux
                        + "\nNOMBRE: " + paciente.getNombrePaciente().toUpperCase()
                        + "\nEDAD: " + paciente.getEdadPaciente()
                        + "\nPADECIMIENTO: " + paciente.getPadecimiento().toUpperCase();
            }
            listaPacientes.remove(0); //Removemos el primer paciente de la linea
        } catch (NullPointerException e) { //Si se produce un null pointer
            informacion += "Fuente de Error desconocida";
        }
        return informacion;
    }

    //Metodo para verificar el tipo de llamada Basica o Emergencia
    public void TipoLista(String tipoLlamada, Paciente paciente) {
        try {
            if (tipoLlamada.equals("Basico")) { //Si es llamda basica agregamos pasiente a lista basica
                ListaBasica.add(paciente);
            } else { //Si es emergencia agregamos paciente a lista de emergencia
                ListaEmergencia.add(paciente);
            }
            NumeroPacientes++; //Contamos un paciente mas en espera
        } catch (NullPointerException e) { //Si las lista Generea un null pointer
            JOptionPane.showMessageDialog(null, "Se a producido un error intente de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo para limpiar cajas de texto de la pantalla
    public void LimpiarCajas() {
        Emergencia.cbxTipoLlamada.setSelectedIndex(0);
        Emergencia.txtNombrePaciente.setText(null);
        Emergencia.txtEdadPaciente.setText(null);
        Emergencia.txtPadecimientoPaciente.setText(null);
    }

    //Metodo para actualizar componentes graficos
    public void RefrescarPantalla() {
        boolean estado = (NumeroPacientes == 0) ? false : true;
        Emergencia.btnAtender.setEnabled(estado);
        Emergencia.txtNumeroPacientes.setText("Pacientes Actuales: " + NumeroPacientes);
    }

    /*
        Se utilizan los siguientes miembros estaticos porque el numero de pacientes en el hostipal
        asi como la lista de pacientes en espera deben de coincidir para todas las personas encargadas
        de atenderlas.
     */
    private static int NumeroPacientes;
    private static ArrayList<Paciente> ListaEmergencia;
    private static ArrayList<Paciente> ListaBasica;
    private Emergencia Emergencia;
    private Paciente Paciente;
}
