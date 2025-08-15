package ahorcadoprogra2lab_samuel_leonardo;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author unwir
 */
public class AdminPalabrasSecretas {

    private static AdminPalabrasSecretas instance;
    private ArrayList<String> palabrasSecretas;

    private String palabraFijaSeleccionada;
    private boolean palabraFijaYaElegida;

    private AdminPalabrasSecretas() {
        this.palabrasSecretas = new ArrayList<>();
        this.palabraFijaSeleccionada = null;
        this.palabraFijaYaElegida = false;
        inicializarPalabrasDefault();
    }

    public static AdminPalabrasSecretas getInstance() {
        if (instance == null) {
            instance = new AdminPalabrasSecretas();
        }
        return instance;
    }

    private void inicializarPalabrasDefault() {
        palabrasSecretas.clear();
        palabrasSecretas.add("java");
        palabrasSecretas.add("programacion");
        palabrasSecretas.add("computadora");
        palabrasSecretas.add("ingeniero");
        palabrasSecretas.add("samuel");
        palabrasSecretas.add("leonardo");
        palabrasSecretas.add("codigo");
        palabrasSecretas.add("loco");
        palabrasSecretas.add("aplicacion");
        palabrasSecretas.add("sistema");
    }

    public void crearPalabras() {
        ArrayList<String> palabrasNuevas = new ArrayList<>();
        boolean continuarCreando = true;
        int contador = 1;

        while (continuarCreando && contador <= 10) {
            String palabra = null;
            boolean palabraValida = false;

            while (!palabraValida) {
                palabra = JOptionPane.showInputDialog(
                        null,
                        "Ingrese la palabra " + contador + " de 10",
                        "Crear Lista de Palabras",
                        JOptionPane.QUESTION_MESSAGE
                );

                if (palabra == null) {
                    int opcion = JOptionPane.showConfirmDialog(
                            null,
                            "¿Desea continuar creando palabras?",
                            "Cancelar",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (opcion == JOptionPane.NO_OPTION) {
                        continuarCreando = false;
                        break;
                    }
                    continue;
                }

                palabra = palabra.trim().toLowerCase();

                if (palabra.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No puedes dejar la casilla vacía.");
                } else if (palabrasNuevas.contains(palabra)) {
                    JOptionPane.showMessageDialog(null, "Esta palabra ya fue ingresada.");
                } else {
                    palabraValida = true;
                    palabrasNuevas.add(palabra);
                    contador++;
                }
            }
        }

        if (!palabrasNuevas.isEmpty()) {
            if (palabraFijaYaElegida) {
                JOptionPane.showMessageDialog(null,
                        "Se han cambiado las palabras.\n",
                        "Palabras Nuevas",
                        JOptionPane.INFORMATION_MESSAGE);

                resetearPalabraFija();
            }

            palabrasSecretas.clear();
            palabrasSecretas.addAll(palabrasNuevas);

            JOptionPane.showMessageDialog(null,
                    "Se crearon las palabras exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null,
                    "No creaste nuevas palabras");
        }
    }

    public ArrayList<String> getPalabrasSecretas() {
        return new ArrayList<>(palabrasSecretas);
    }

    public ArrayList<String> getPalabrasUsuario() {
        return getPalabrasSecretas();
    }

    public boolean seleccionarPalabraFija() {
        if (palabraFijaYaElegida) {
            JOptionPane.showMessageDialog(null, "No se puede cambiar en esta sesión.", "Palabra Fija Ya Seleccionada", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }

        if (!hayPalabrasDisponibles()) {
            JOptionPane.showMessageDialog(null,
                    "No hay palabras disponibles para seleccionar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String[] palabrasArray = palabrasSecretas.toArray(new String[0]);
        String seleccionPalabra = (String) JOptionPane.showInputDialog(
                null, "Selecciona la palabra FIJA para el modo Ahorcado Fijo:\n",
                "Seleccionar Palabra Fija",
                JOptionPane.QUESTION_MESSAGE,
                null,
                palabrasArray,
                palabrasArray[0]
        );

        if (seleccionPalabra != null) {
            palabraFijaSeleccionada = seleccionPalabra.toLowerCase().trim();
            palabraFijaYaElegida = true;

            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se seleccionó ninguna palabra. Debes seleccionar una para jugar.",
                    "Selección Cancelada", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public String getPalabraFija() {
        return palabraFijaSeleccionada;
    }

    public boolean isPalabraFijaYaElegida() {
        return palabraFijaYaElegida;
    }

    public void resetearPalabraFija() {
        palabraFijaSeleccionada = null;
        palabraFijaYaElegida = false;
    }

    public String elegirPalabraRandom(ArrayList<String> pals) {
        if (pals == null || pals.isEmpty()) {
            throw new IllegalArgumentException("La lista de palabras no puede estar vacía");
        }
        Random rand = new Random();
        return pals.get(rand.nextInt(pals.size()));
    }

    public String elegirPalabraRandom() {
        return elegirPalabraRandom(this.palabrasSecretas);
    }

    public boolean hayPalabrasDisponibles() {
        return !palabrasSecretas.isEmpty();
    }

    public int getCantidadPalabras() {
        return palabrasSecretas.size();
    }
}
