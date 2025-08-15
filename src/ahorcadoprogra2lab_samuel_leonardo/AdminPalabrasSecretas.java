/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcadoprogra2lab_samuel_leonardo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author unwir
 */
public class AdminPalabrasSecretas {
    private static AdminPalabrasSecretas instance;
    private ArrayList<String> palabrasSecretas = new ArrayList<>();
    
    
    public static AdminPalabrasSecretas getInstance(){
        if(instance == null){
            instance = new AdminPalabrasSecretas();
        }
        return instance;
    }
    
    
    public void addPrimerasPalabras(){
        String[] palbarras = {"Leon", "Secreto", "Mago", "Filtro", "Proyecto", 
            "Cable", "Puntos", "Deidad",  "Artificial", "Loco"};
        palabrasSecretas.addAll(Arrays.asList(palbarras));
    }
   
    
}
