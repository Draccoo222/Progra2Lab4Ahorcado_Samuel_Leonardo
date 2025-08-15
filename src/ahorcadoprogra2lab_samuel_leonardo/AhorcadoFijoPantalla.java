package ahorcadoprogra2lab_samuel_leonardo;

import javax.swing.*;
import java.awt.*;

/**
 * 
 * 
 * @author hnleo
 */
public class AhorcadoFijoPantalla extends JFrame {

    private JLabel lblPalabraActual, lblLetrasUsadas, lblIntentos, lblMensaje, lblPalabraFija;
    private JTextField txtLetra;
    private JButton btnProbar, btnReiniciar, btnSalir;
    private JPanel panelFigura;

    private JuegoAhorcadoFijo juegoFijo;
    private AdminPalabrasSecretas adminPalabras;

    public AhorcadoFijoPantalla() {
        adminPalabras = AdminPalabrasSecretas.getInstance();
        
        if (!adminPalabras.isPalabraFijaYaElegida()) {
            boolean palabraSeleccionada = adminPalabras.seleccionarPalabraFija();
            if (!palabraSeleccionada) {
                this.dispose();
                JOptionPane.showMessageDialog(null, 
                    "Debes seleccionar una palabra para jugar en modo fijo.", 
                    "Palabra Requerida", JOptionPane.WARNING_MESSAGE);
                new PantallaMenu().setVisible(true);
                return;
            }
        }
        
        setTitle("AHORCADO FIJO");
        setSize(600, 650);
        setLayout(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); 
        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                volverAlMenu();
            }
        });

        juegoFijo = new JuegoAhorcadoFijo();
        juegoFijo.seleccionarPalabra(adminPalabras.getPalabraFija());

        inicializarComponentes();
        actualizarInterfaz();
        
        setVisible(true);
    }
    
    private void inicializarComponentes() {
        
        

        lblPalabraActual = new JLabel("_ _ _ _ _");
        lblPalabraActual.setFont(new Font("Arial", Font.BOLD, 24));
        lblPalabraActual.setBounds(50, 70, 500, 30);
        add(lblPalabraActual);

        lblLetrasUsadas = new JLabel("Letras usadas: ");
        lblLetrasUsadas.setBounds(50, 110, 500, 20);
        add(lblLetrasUsadas);

        
        lblIntentos = new JLabel("Intentos restantes: 6");
        lblIntentos.setBounds(50, 140, 200, 20);
        add(lblIntentos);

        lblMensaje = new JLabel("¡Adivina la palabra secreta!");
        lblMensaje.setBounds(50, 170, 400, 20);
        add(lblMensaje);

        txtLetra = new JTextField();
        txtLetra.setBounds(50, 200, 50, 25);
        txtLetra.setFont(new Font("Arial", Font.BOLD, 16));
        add(txtLetra);

        btnProbar = new JButton("Probar");
        btnProbar.setBounds(120, 200, 100, 25);
        add(btnProbar);

        panelFigura = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarAhorcado(g);
            }
        };
        panelFigura.setBounds(300, 250, 250, 250);
        panelFigura.setBackground(Color.WHITE);
        panelFigura.setBorder(BorderFactory.createTitledBorder("El Ahorcado"));
        add(panelFigura);

        btnReiniciar = new JButton("Reiniciar Intento");
        btnReiniciar.setBounds(50, 550, 140, 30);
        add(btnReiniciar);

        btnSalir = new JButton("Menú Principal");
        btnSalir.setBounds(220, 550, 130, 30);
        add(btnSalir);
        btnSalir.addActionListener(e -> volverAlMenu());

        btnReiniciar.addActionListener(e -> {
            int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Estás seguro de que quieres reiniciar el intento?\n", "Confirmar Reinicio", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                juegoFijo.reiniciarJuego();
                actualizarInterfaz();
                lblMensaje.setText("Intento reiniciado.");
                txtLetra.requestFocus();
            }
        });

        btnProbar.addActionListener(e -> probarLetra());
        txtLetra.addActionListener(e -> probarLetra());
        txtLetra.requestFocus();
    }

    private void probarLetra() {
        String letraTexto = txtLetra.getText().trim();
        
        if (letraTexto.isEmpty()) {
            lblMensaje.setText("Ingresa una letra.");
            return;
        }
        
        if (letraTexto.length() != 1) {
            lblMensaje.setText("Ingresa solo una letra.");
            txtLetra.setText("");
            return;
        }
        
        char letra = letraTexto.toLowerCase().charAt(0);
        
        if (!Character.isLetter(letra)) {
            lblMensaje.setText("Solo se permiten letras.");
            txtLetra.setText("");
            return;
        }
        
        if (juegoFijo.juegoTerminado()) {
            lblMensaje.setText("El juego acabó.");
            txtLetra.setText("");
            return;
        }
        
        boolean acierto = juegoFijo.verificarLetra(letra);
        actualizarInterfaz();
        
        if (juegoFijo.hasGanado()) {
            lblMensaje.setText("ADIVINASTE LA PALABRA, FELICIDADES!!!");
            mostrarMensajeVictoria();
        } else if (juegoFijo.haPerdido()) {
            lblMensaje.setText("Perdiste, La palabra era: " + juegoFijo.getPalabraSecreta().toUpperCase());
            mostrarMensajeDerrota();
        } else {
            lblMensaje.setText(acierto ? "Letra correcta." : "Letra incorrecta.");
        }
        
        txtLetra.setText("");
        
        if (!juegoFijo.juegoTerminado()) {
            txtLetra.requestFocus();
        }
    }
    
    private void mostrarMensajeVictoria() {
        JOptionPane.showMessageDialog(this, 
            "Felicidades\n\n" +
            "Adivinaste la palabra: " + juegoFijo.getPalabraSecreta().toUpperCase() + "\n" + "Intentos utilizados: " + juegoFijo.getIntentos() + " de 6\n", "Victoria", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarMensajeDerrota() {
        JOptionPane.showMessageDialog(this, 
            "Perdiste\n\n" +
            "La palabra era: " + juegoFijo.getPalabraSecreta().toUpperCase() + "\n",
            "Juego Terminado", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void actualizarInterfaz() {
        lblPalabraActual.setText(formatearPalabra(juegoFijo.getPalabraActual()));
        
        StringBuilder letrasUsadas = new StringBuilder("Letras usadas: ");
        for (Character letra : juegoFijo.getLetrasUsadas()) {
            letrasUsadas.append(letra.toString().toUpperCase()).append(" ");
        }
        lblLetrasUsadas.setText(letrasUsadas.toString());
        
        int intentosRestantes = Math.max(0, 6 - juegoFijo.getIntentos());
        lblIntentos.setText("Intentos restantes: " + intentosRestantes);
        
        panelFigura.repaint();
    }

    private void dibujarAhorcado(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.BLACK);
        
        g2d.drawLine(50, 200, 150, 200); 
        g2d.drawLine(100, 200, 100, 50); 
        g2d.drawLine(100, 50, 180, 50);  
        g2d.drawLine(180, 50, 180, 80); 

        
        int errores = juegoFijo.getIntentos();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        
        if (errores >= 1) { 
            g2d.drawOval(165, 80, 30, 30);
        }
        if (errores >= 2) { 
            g2d.drawLine(180, 110, 180, 160);
        }
        if (errores >= 3) { 
            g2d.drawLine(180, 130, 160, 150);
        }
        if (errores >= 4) { 
            g2d.drawLine(180, 130, 200, 150);
        }
        if (errores >= 5) { 
            g2d.drawLine(180, 160, 160, 190);
        }
        if (errores >= 6) { 
            g2d.drawLine(180, 160, 200, 190);
           
        }
    }

    private String formatearPalabra(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        for (char c : palabra.toCharArray()) {
            sb.append(Character.toUpperCase(c)).append(" ");
        }
        return sb.toString().trim();
    }
    
    private void volverAlMenu() {
        this.dispose();
        new PantallaMenu().setVisible(true);
    }
}