/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author valen
 */

//serializable es para cuando quiero agregar datos como archivo. 
public class Agenda implements Serializable {

    ArrayList<Persona> contactos;

    public Agenda(ArrayList<Persona> contactos) {
        this.contactos = contactos;
    }

    public Agenda() {
        contactos = new ArrayList<>();
    }

    public boolean addContacto(Persona p) {
        try {
            contactos.add(p);
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public ArrayList<Persona> getContactos() {
        return contactos;
    }

}
