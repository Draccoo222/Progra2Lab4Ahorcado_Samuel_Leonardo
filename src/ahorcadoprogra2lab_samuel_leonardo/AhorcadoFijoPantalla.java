package ahorcadoprogra2lab_samuel_leonardo;
import ahorcadoprogra2lab_samuel_leonardo.JuegoAhorcadoFijo;
import ahorcadoprogra2lab_samuel_leonardo.MenuPrincipal;
import ahorcadoprogra2lab_samuel_leonardo.PantallaMenu;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author hnleo
 */

public class AhorcadoFijoPantalla extends JFrame {

    private JComboBox<String> comboPalabras;
    private JLabel lblPalabraActual, lblLetrasUsadas, lblIntentos, lblMensaje;
    private JTextField txtLetra;
    private JButton btnProbar, btnReiniciar, btnSalir, btnSeleccionar;
    private JPanel panelFigura;

    private JuegoAhorcadoFijo juegoFijo;

    public AhorcadoFijoPantalla() {
        setTitle("AHORCADO FIJO");
        setSize(600, 600);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        juegoFijo = new JuegoAhorcadoFijo();

        comboPalabras = new JComboBox<>();
        for (String palabra : juegoFijo.getPalabrasSecretas()) {
            comboPalabras.addItem(palabra);
        }
        comboPalabras.setBounds(50, 20, 200, 25);
        add(comboPalabras);

        btnSeleccionar = new JButton("Seleccionar Palabra");
        btnSeleccionar.setBounds(270, 20, 160, 25);
        add(btnSeleccionar);

        btnSeleccionar.addActionListener(e -> {
            String seleccion = (String) comboPalabras.getSelectedItem();
            if (seleccion != null) {
                juegoFijo.selecPalabra(seleccion);
                lblPalabraActual.setText(formatearPalabra(juegoFijo.getPalabraActual()));
                lblLetrasUsadas.setText("Letras usadas: ");
                lblIntentos.setText("Intentos restantes: 6");
                lblMensaje.setText("¡Comienza el juego!");
                panelFigura.repaint();
            }
        });

        // Label palabra actual
        lblPalabraActual = new JLabel("_ _ _ _ _");
        lblPalabraActual.setFont(new Font("Arial", Font.BOLD, 24));
        lblPalabraActual.setBounds(50, 60, 500, 30);
        add(lblPalabraActual);

        // Label letras usadas
        lblLetrasUsadas = new JLabel("Letras usadas: ");
        lblLetrasUsadas.setBounds(50, 100, 500, 20);
        add(lblLetrasUsadas);

        // Label intentos restantes
        lblIntentos = new JLabel("Intentos restantes: 6");
        lblIntentos.setBounds(50, 130, 200, 20);
        add(lblIntentos);

        // Label mensaje
        lblMensaje = new JLabel("Selecciona una palabra para comenzar.");
        lblMensaje.setBounds(50, 160, 400, 20);
        add(lblMensaje);

        // Campo para ingresar letra
        txtLetra = new JTextField();
        txtLetra.setBounds(50, 190, 50, 25);
        add(txtLetra);

        // Botón probar letra
        btnProbar = new JButton("Probar");
        btnProbar.setBounds(120, 190, 100, 25);
        add(btnProbar);

        // Panel para la figura del ahorcado
        panelFigura = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                g.drawLine(50, 200, 150, 200); // suelo
                g.drawLine(100, 200, 100, 50); // poste vertical
                g.drawLine(100, 50, 200, 50);  // barra superior
                g.drawLine(200, 50, 200, 80);  // cuerda

                int errores = juegoFijo.getIntentos();
                switch (errores) {
                    case 6: g.drawLine(200, 180, 220, 210); // pierna derecha
                    case 5: g.drawLine(200, 180, 180, 210); // pierna izquierda
                    case 4: g.drawLine(200, 120, 220, 160); // brazo derecho
                    case 3: g.drawLine(200, 120, 180, 160); // brazo izquierdo
                    case 2: g.drawLine(200, 80, 200, 180);   // cuerpo
                    case 1: g.drawOval(180, 50, 40, 40);     // cabeza
                        break;
                    default:
                        break;
                }
            }
        };
        panelFigura.setBounds(300, 200, 250, 250);
        panelFigura.setBackground(Color.WHITE);
        add(panelFigura);

        
        btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.setBounds(50, 500, 100, 30);
        add(btnReiniciar);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(200, 500, 100, 30);
        add(btnSalir);

        btnSalir.addActionListener(e -> {
            new PantallaMenu().setVisible(true);
        });

        btnReiniciar.addActionListener(e -> {
            juegoFijo = new JuegoAhorcadoFijo();
            comboPalabras.removeAllItems();
            for (String palabra : juegoFijo.getPalabrasSecretas()) {
                comboPalabras.addItem(palabra);
            }
            lblPalabraActual.setText("_ _ _ _ _");
            lblLetrasUsadas.setText("Letras usadas: ");
            lblIntentos.setText("Intentos restantes: 6");
            lblMensaje.setText("Selecciona una palabra para comenzar.");
            panelFigura.repaint();
        });

        btnProbar.addActionListener(e -> {
            String letraTexto = txtLetra.getText();
            if (letraTexto.length() != 1) {
                lblMensaje.setText("Ingresa solo una letra.");
                return;
            }
            char letra = letraTexto.charAt(0);
            boolean acierto = juegoFijo.verificarLetra(letra);
            lblPalabraActual.setText(formatearPalabra(juegoFijo.getPalabraActual()));
            lblLetrasUsadas.setText("Letras usadas: " + juegoFijo.getLetrasUsadas());
            lblIntentos.setText("Intentos restantes: " + (6 - juegoFijo.getIntentos()));

            if (juegoFijo.hasGanado()) {
                lblMensaje.setText("¡Ganaste!");
            } else if (juegoFijo.getIntentos() >= 6) {
                lblMensaje.setText("¡Perdiste! La palabra era: " + juegoFijo.getPalabraSecreta());
            } else {
                lblMensaje.setText(acierto ? "¡Bien!" : "Letra incorrecta.");
            }

            panelFigura.repaint();
            txtLetra.setText("");
        });

        setVisible(true);
    }

    private String formatearPalabra(String palabra) {
        StringBuilder sb = new StringBuilder();
        for (char c : palabra.toCharArray()) {
            sb.append(c).append(" ");
        }
        return sb.toString();
    }

}