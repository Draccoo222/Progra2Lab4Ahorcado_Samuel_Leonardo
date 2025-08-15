/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcadoprogra2lab_samuel_leonardo;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author unwir
 */
public class JuegoAhorcadoAzar extends JuegoAhorcadoBase {
    private AdminPalabrasSecretas adm = AdminPalabrasSecretas.getInstance();
    private ArrayList<String> palabrasSecretas;

    public JuegoAhorcadoAzar() {
        // Inicializar las listas
        this.letrasUsadas = new ArrayList<>();
        this.figuraAhorcado = new ArrayList<>();
        this.intentos = 0;
        
        // Obtener las palabras del administrador
        palabrasSecretas = adm.getPalabrasSecretas();
        
        // Inicializar el juego con una palabra aleatoria
        inicializarPalabrasSecretas();
    }

    @Override
    public void actualizarPalabraActual(char letra) {
        char[] letraActual = palabraActual.toCharArray();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (Character.toUpperCase(palabraSecreta.charAt(i)) == Character.toUpperCase(letra)) {
                letraActual[i] = Character.toUpperCase(letra);
            }
        }
        palabraActual = new String(letraActual);
    }

    @Override
    public boolean verificarLetra(char letra) {
        letra = Character.toUpperCase(letra);
        
        if (letrasUsadas.contains(letra)) {
            return false; // No mostrar mensaje aquí para que la pantalla lo maneje
        }
        
        letrasUsadas.add(letra);
        
        if (palabraSecreta.toUpperCase().contains(String.valueOf(letra))) {
            actualizarPalabraActual(letra);
            return true;
        } else {
            intentos++;
            return false;
        }
    }

    @Override
    public boolean hasGanado() {
        return palabraActual.equals(palabraSecreta.toUpperCase());
    }

    @Override
    public void inicializarPalabrasSecretas() {
        if (palabrasSecretas != null && !palabrasSecretas.isEmpty()) {
            Random random = new Random();
            palabraSecreta = palabrasSecretas.get(random.nextInt(palabrasSecretas.size()));
            palabraActual = "_".repeat(palabraSecreta.length());
        }
    }

    @Override
    public void jugar() {
        // Método requerido por la interfaz, implementación vacía para el juego con GUI
    }
    
    // Métodos getter adicionales para la interfaz
    public String getPalabraSecreta() {
        return palabraSecreta;
    }
    
    public String getPalabraActual() {
        return palabraActual;
    }
    
    public int getIntentos() {
        return intentos;
    }
    
    public ArrayList<Character> getLetrasUsadas() {
        return letrasUsadas;
    }
    
    public void reiniciarJuego() {
        this.letrasUsadas.clear();
        this.figuraAhorcado.clear();
        this.intentos = 0;
        inicializarPalabrasSecretas();
    }
}