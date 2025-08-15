/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcadoprogra2lab_samuel_leonardo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JOptionPane;
/**
 *
 * @author unwir
 */
public class AdminPalabrasSecretas {
    private static AdminPalabrasSecretas instance;
   
    
    ArrayList<String> palabrasSecretas;
    
    private AdminPalabrasSecretas(){
      palabrasSecretas = new ArrayList<>(Arrays.asList(
                "Leon", "Secreto",
                "Mago", "Filtro",
                "Proyecto", "Cable",
                "Puntos", "Deidad",
                "Artificial", "Loco"));
    }
    
    public static AdminPalabrasSecretas getInstance(){
        if(instance == null){
            instance = new AdminPalabrasSecretas();
        }
        return instance;
    }
    
    public void crearPalabras() {
        ArrayList<String> palabras = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String palabra;
            do {
                palabra = JOptionPane.showInputDialog(
                        null,
                        "Ingrese la palabra " + i + " de 10: ",
                        "Crear Lista de Palabras",
                        JOptionPane.QUESTION_MESSAGE
                );
                if (palabra == null) {
                    JOptionPane.showMessageDialog(null, "Operacion cancelada.");
                    break;
                }
                palabra = palabra.trim().toLowerCase();
                if (palabra.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No puedes dejar la palabra vacia.");
                }
            } while (palabra.isEmpty());
            palabras.add(palabra);
        }
        palabrasSecretas.clear();
        palabrasSecretas.addAll(palabras); 
    }

    public ArrayList<String> getPalabrasSecretas(){
        return palabrasSecretas;
    }
   
    public ArrayList<String> getPalabrasUsuario(){
        return palabrasSecretas;
    }
 
    
    
    public String elegirPalabraRandom(ArrayList<String> pals){
        Random rand = new Random();
        return pals.get(rand.nextInt(pals.size()));
    }
    
   
   
    
}
