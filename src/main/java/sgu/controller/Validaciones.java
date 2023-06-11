/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sgu.controller;


public class Validaciones {
    
    public boolean checkDNI(String DNI){
        if (DNI.length() != 8) return false;
        return true;
    }
    
    public boolean checkDNILetras(String DNI){
        try {
            Integer.parseInt(DNI);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean checkTelefono(String tel) {
        if (tel.length() < 12) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean checkNota(double nota) {
        if (nota >= 1.0 && nota <= 10.0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkNotaLetras(String nota) {
        try {
            Double.parseDouble(nota);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean checkNombreNumeros(String nombre){
        for (char c : nombre.toCharArray()) {
        if (Character.isDigit(c)) {
            return true;
        }
    }
    return false;
    }
}
