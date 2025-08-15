/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcadoprogra2lab_samuel_leonardo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author hnleo
 */
public class JuegoAhorcadoFijo extends JuegoAhorcadoBase {

    private ArrayList<String> palabrasSecretas;

    public JuegoAhorcadoFijo() {
        this.intentos = 0;
        this.letrasUsadas.clear();
        this.figuraAhorcado.clear();
        palabrasSecretas = new ArrayList<>(Arrays.asList(
                "Leon", "Secreto",
                "Mago", "Filtro",
                "Proyecto", "Cable",
                "Puntos", "Deidad",
                "Artificial", "Loco"));

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
          System.out.println("Esta letra ya ha sido colocada.");
          return false;
      }
      
      if(palabraSecreta.contains(String.valueOf(letra)))
      {
          actualizarPalabraActual(letra);
          System.out.println("Se actualzo la letra en la palabra");
          return true;
      }else{
          System.out.println("Esta letra no existe en la palabra secreta.");
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
