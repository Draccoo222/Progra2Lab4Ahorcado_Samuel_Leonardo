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
public class JuegoAhorcadoAzar extends JuegoAhorcadoBase{
    AdminPalabrasSecretas adm = AdminPalabrasSecretas.getInstance();
    ArrayList<String> palabrasSecretas;

    public JuegoAhorcadoAzar() {
        this.intentos = 0;
        if(letrasUsadas != null){
            this.letrasUsadas.clear();
        }
        if(figuraAhorcado != null){
         this.figuraAhorcado.clear();
        }
        palabrasSecretas = adm.getPalabrasSecretas();

        inicializarPalabrasSecretas();
    }

    @Override
    void actualizarPalabraActual(char letra) {
       char[] letraActual = palabraActual.toCharArray();
       for(int i = 0; i< palabraSecreta.length(); i++){
           if(palabraSecreta.charAt(i)==letra){
               letraActual[i] = letra;
           }
       }
           palabraActual = new String(letraActual);
    }

    @Override
    boolean verificarLetra(char letra) {
      letra = Character.toUpperCase(letra);
      if(letrasUsadas.contains(letra)){
          JOptionPane.showMessageDialog(null, "Esta letra ya ha sido colocada.");
          return false;
      }
      
      if(palabraSecreta.contains(String.valueOf(letra)))
      {
          actualizarPalabraActual(letra);
          JOptionPane.showMessageDialog(null,"Se actualzo la letra en la palabra");
          return true;
      }else{
          JOptionPane.showMessageDialog(null, "Esta letra no existe en la palabra secreta.");
          intentos++;
          actualizarFigura();
          return false;
      }
      
    }

    @Override
    boolean hasGanado() {
     return palabraActual.equals(palabraSecreta);
    }

    @Override
    public void inicializarPalabrasSecretas() {

        Random random = new Random();
        palabraSecreta = palabrasSecretas.get(random.nextInt(palabrasSecretas.size()));
        palabraActual = "_".repeat(palabraSecreta.length());

    }

    @Override
    public void jugar() {

    }
    
    
    
}
