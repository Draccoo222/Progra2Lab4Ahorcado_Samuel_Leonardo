/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcadoprogra2lab_samuel_leonardo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author hnleo
 */
public class JuegoAhorcadoFijo extends JuegoAhorcadoBase{
    private AdminPalabrasSecretas adm = AdminPalabrasSecretas.getInstance();
    char[] palabraActual;
 
    
    @Override
    void actualizarPalabraActual(char letra) {
        boolean acierto = false;
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if(palabraSecreta.charAt(i) == letra && palabraActual[i] == '_'){}
            
        }
        
        
    }
    
    

    @Override
    void verificarLetra(char letra) {
      
    }
    
    
    

    @Override
    boolean hasGanado() {
       
    }

    @Override
    public void inicializarPalabrasSecretas() {
        ArrayList<String> palabras = adm.getPalabrasSecretas();
         palabraSecreta = adm.elegirPalabraRandom(palabras);
        char[] palabraActual = new char[palabraSecreta.length()];
        
        Arrays.fill(palabraActual, '_');
        
        
    }

    @Override
    public void jugar() {
 
    }
    
}
