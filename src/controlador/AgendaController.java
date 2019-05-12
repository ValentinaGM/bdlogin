/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.table.DefaultTableModel;
import modelo.Agenda;
import modelo.Persona;
import modelo.dao.PersonaDao;
import vista.InicioAgenda;
import vista.VentanaFormulario;

/**
 *
 * @author valen
 */

//actionlistener es para cuando se apretan botones. 
public class AgendaController implements ActionListener {

    private VentanaFormulario vf; //de aqui saco la info
    private InicioAgenda va; //aqui coloco la info

    public AgendaController(VentanaFormulario vf, InicioAgenda va) {
        this.vf = vf;
        this.va = va;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String nombre = vf.getjTextField1().getText();
        int edad = Integer.parseInt(vf.getjTextField2().getText());
        String rut = vf.getjTextField3().getText();

        Persona p = new Persona(nombre, edad, rut);
        PersonaDao pDao = new PersonaDao();
        pDao.insertar(p);

        va.actualizaTabla();
        vf.dispose();

        /*  DefaultTableModel dtm= (DefaultTableModel) va.getjTable1().getModel();
//          System.out.println(nombre + " "+ edad+ " "+rut);
           String v[]= {nombre,""+edad,rut}; 
          dtm.addRow(v); */
//        System.out.println("HOLA SOY EL CONTROLADOR Y SE HA APRETADO EL BOTON");
//        System.out.println("LA ivana es entera chora");
    }

    public boolean guardarArchivo(Agenda a) {

        try {
            FileOutputStream archivo = new FileOutputStream("test.txt");
            ObjectOutputStream grafo = new ObjectOutputStream(archivo);

            grafo.writeObject(a);
            grafo.close();
            archivo.close();
            return true;
        } catch (Exception e) {
            System.out.println("El contacto se esta guardando");

            return false;
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
