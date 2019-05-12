/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Agenda;
import modelo.Persona;

/**
 *
 * @author valen
 */

public class TablaController {

    JTable tablaAgenda;

    public TablaController(JTable tabla) {

        tablaAgenda = tabla;

    }

    public void actualizaTabla() {
        Agenda agendaModel = leerArchivo();
        if (agendaModel == null) {
            System.out.println("La tabla no contiene registros");
        } else {

            DefaultTableModel dtm = (DefaultTableModel) tablaAgenda.getModel();

            for (int i = 0; i < agendaModel.getContactos().size(); i++) {
                Persona temp = agendaModel.getContactos().get(i);
                String v[] = {temp.getNombre(), "" + temp.getEdad(), temp.getRut()};
                dtm.addRow(v);

            }

        }

    }

    public Agenda leerArchivo() {
        try {
            FileInputStream archivo = new FileInputStream("test.txt");
            ObjectInputStream grafo = new ObjectInputStream(archivo);

            Agenda agenda = (Agenda) grafo.readObject();

            grafo.close();
            archivo.close();

            return agenda;

        } catch (Exception e) {
            System.out.println("error");

            return null;
        }

    }

}
