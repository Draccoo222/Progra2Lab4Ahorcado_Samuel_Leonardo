package ahorcadoprogra2lab_samuel_leonardo;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author hnleo
 */
public class JuegoAhorcadoFijo extends JuegoAhorcadoBase {
    private AdminPalabrasSecretas gestion = AdminPalabrasSecretas.getInstance();
    private ArrayList<String> palabrasSecretas;

    public JuegoAhorcadoFijo() {
        this.intentos = 0;
        this.letrasUsadas = new ArrayList<>(); 
        this.figuraAhorcado = new ArrayList<>(); 
        palabrasSecretas = gestion.getPalabrasSecretas();
    }
    
    public String getPalabraActual() {
        return palabraActual != null ? palabraActual : "";
    }
    
    public int getIntentos() {
        return intentos;
    }
    
    public ArrayList<String> getPalabrasSecretas() {
        return palabrasSecretas;
    }

    @Override
    public void actualizarPalabraActual(char letra) {
        if (palabraSecreta == null || palabraActual == null) return;
        
        char[] letraActual = palabraActual.toCharArray();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                letraActual[i] = letra;
            }
        }
        palabraActual = new String(letraActual);
    }

    @Override
    public boolean verificarLetra(char letra) {
        letra = Character.toLowerCase(letra); // CORRECCIÓN: Usar toLowerCase para consistencia
        
        // Validar que sea una letra
        if (!Character.isLetter(letra)) {
            return false;
        }
        
        if (letrasUsadas.contains(letra)) {
            System.out.println("Esta letra ya ha sido usada.");
            return false;
        }
        
        letrasUsadas.add(letra); // CORRECCIÓN: Agregar la letra a las usadas siempre
        
        if (palabraSecreta.toLowerCase().contains(String.valueOf(letra))) {
            actualizarPalabraActual(Character.toLowerCase(letra));
            System.out.println("¡Letra correcta!");
            return true;
        } else {
            System.out.println("Esta letra no existe en la palabra secreta.");
            intentos++;
            return false;
        }
    }
    
    public ArrayList<Character> getLetrasUsadas() {
        return new ArrayList<>(letrasUsadas); // Retorna copia
    }
     
    public String getPalabraSecreta() {
        return palabraSecreta != null ? palabraSecreta : "";
    }

    @Override
    public boolean hasGanado() {
        if (palabraActual == null || palabraSecreta == null) return false;
        return palabraActual.toLowerCase().equals(palabraSecreta.toLowerCase());
    }

    @Override
    public void inicializarPalabrasSecretas() {
//        if (!gestion.hayPalabrasDisponibles()) {
//            JOptionPane.showMessageDialog(null, 
//                "No hay palabras disponibles. Se inicializarán palabras por defecto.", 
//                "Advertencia", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
        
        String[] palabrasArray = palabrasSecretas.toArray(new String[0]);
        String seleccionPalabra = (String) JOptionPane.showInputDialog(
            null, 
            "Selecciona la palabra:", 
            "Elegir Palabra", 
            JOptionPane.QUESTION_MESSAGE,
            null,
            palabrasArray,
            palabrasArray[0]
        );
        
        if (seleccionPalabra != null) {
            seleccionarPalabra(seleccionPalabra);
        } else {
            JOptionPane.showMessageDialog(null, 
                "No se seleccionó una palabra", 
                "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
   
    public void seleccionarPalabra(String palabra) {
        if (palabra == null || palabra.trim().isEmpty()) {
            throw new IllegalArgumentException("La palabra no puede estar vacía");
        }
        
        palabraSecreta = palabra.toLowerCase().trim();
        palabraActual = "_".repeat(palabraSecreta.length());
        intentos = 0;
        letrasUsadas.clear();
        figuraAhorcado.clear();
    }
    
    public void selecPalabra(String palabra) {
        seleccionarPalabra(palabra);
    }

    public void reiniciarJuego() {
        if (palabraSecreta != null) {
            palabraActual = "_".repeat(palabraSecreta.length());
            intentos = 0;
            letrasUsadas.clear();
            figuraAhorcado.clear();
        }
    }
 
    public boolean juegoTerminado() {
        return hasGanado() || intentos >= limiteIntento;
    }
    
    public boolean haPerdido() {
        return intentos >= limiteIntento;
    }
    
    @Override
    public void jugar() {
        if (palabraSecreta == null) {
            inicializarPalabrasSecretas();
        }
    }
}