/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcadoprogra2lab_samuel_leonardo;

import java.util.ArrayList;

/**
 *
 * @author hnleo
 */
public abstract class JuegoAhorcadoBase implements JuegoAhorcado {
    protected String palabraSecreta;
    protected String palabraActual;
    protected int intentos;
    protected int limiteIntento = 6;
    protected ArrayList<Character> letrasUsadas;
    protected ArrayList<String> figuraAhorcado;
    
    abstract void actualizarPalabraActual(char letra);
    abstract boolean verificarLetra(char letra);
    abstract boolean hasGanado();
    
     protected void actualizarFigura() {
        switch(intentos) {
            case 1: figuraAhorcado.add("|o i o|"); break;
            case 2: figuraAhorcado.add("|||||||\n||||||\n|||||"); break;
            case 3: figuraAhorcado.add("||\n||\n||"); break;
            case 4: figuraAhorcado.add("||\n||\n||"); break;
            case 5: figuraAhorcado.add("||\n||\n||"); break;
            case 6: figuraAhorcado.add("||\n||\n||"); break;
        }
    }
}
