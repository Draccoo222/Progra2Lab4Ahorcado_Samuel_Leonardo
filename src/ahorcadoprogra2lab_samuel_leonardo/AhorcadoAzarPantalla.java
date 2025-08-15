/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcadoprogra2lab_samuel_leonardo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author hnleo
 */
public class AhorcadoAzarPantalla extends JFrame {
    private JuegoAhorcadoAzar jogo = new JuegoAhorcadoAzar();
    
    private JLabel lblPalabra;
    private JLabel lblIntentos;
    private JLabel lblLetrasUsadas;
    private JLabel lblMensaje;
    private JTextField txtLetra;
    private JButton btnProbar;
    private JButton btnReiniciar;
    private JButton btnSalir;
    private JPanel panelFigura;
    
    public AhorcadoAzarPantalla() {
        initComponents();
        actualizarPantalla();
    }
    
    private void initComponents() {
        setTitle("AHORCADO AL AZAR");
        setSize(600, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Label palabra actual
        lblPalabra = new JLabel("", SwingConstants.CENTER);
        lblPalabra.setFont(new Font("Arial", Font.BOLD, 24));
        lblPalabra.setBounds(50, 20, 500, 30);
        add(lblPalabra);

        // Label letras usadas
        lblLetrasUsadas = new JLabel("Letras usadas: ");
        lblLetrasUsadas.setBounds(50, 60, 500, 20);
        add(lblLetrasUsadas);

        // Label intentos
        lblIntentos = new JLabel("Intentos restantes: 6");
        lblIntentos.setBounds(50, 90, 200, 20);
        add(lblIntentos);

        // Label mensaje
        lblMensaje = new JLabel("¡Adivina la palabra secreta!");
        lblMensaje.setBounds(50, 120, 400, 20);
        add(lblMensaje);

        // Campo para ingresar letra
        JLabel lblIngreso = new JLabel("Letra:");
        lblIngreso.setBounds(50, 150, 50, 25);
        add(lblIngreso);
        
        txtLetra = new JTextField();
        txtLetra.setBounds(100, 150, 50, 25);
        add(txtLetra);

        // Botón probar letra
        btnProbar = new JButton("Probar");
        btnProbar.setBounds(170, 150, 100, 25);
        add(btnProbar);

        btnProbar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                probarLetra();
            }
        });

        // Panel para la figura del ahorcado
        panelFigura = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarAhorcado(g);
            }
        };
        panelFigura.setBounds(300, 200, 250, 250);
        panelFigura.setBackground(Color.WHITE);
        panelFigura.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(panelFigura);

        // Botón reiniciar
        btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.setBounds(50, 500, 100, 30);
        add(btnReiniciar);
        
        btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jogo.reiniciarJuego();
                actualizarPantalla();
                lblMensaje.setText("¡Nueva palabra! ¡Adivina la palabra secreta!");
            }
        });

        // Botón salir
        btnSalir = new JButton("Salir");
        btnSalir.setBounds(200, 500, 100, 30);
        add(btnSalir);
        
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PantallaMenu().setVisible(true);
            }
        });

        setVisible(true);
    }
    
    private void probarLetra() {
        String input = txtLetra.getText().trim().toUpperCase();
        
        if (input.length() != 1) {
            lblMensaje.setText("Solo se permite una letra.");
            return;
        }
        
        char letra = input.charAt(0);
        
        if (!Character.isLetter(letra)) {
            lblMensaje.setText("Solo se permiten letras.");
            return;
        }
        
        if (jogo.getLetrasUsadas().contains(letra)) {
            lblMensaje.setText("Esta letra ya ha sido usada.");
            txtLetra.setText("");
            return;
        }
        
        boolean acierto = jogo.verificarLetra(letra);
        
        actualizarPantalla();
        
        if (jogo.hasGanado()) {
            lblMensaje.setText("¡Ganaste! La palabra era: " + jogo.getPalabraSecreta());
            btnProbar.setEnabled(false);
        } else if (jogo.getIntentos() >= 6) {
            lblMensaje.setText("¡Perdiste! La palabra era: " + jogo.getPalabraSecreta());
            btnProbar.setEnabled(false);
        } else {
            lblMensaje.setText(acierto ? "¡Bien! Letra correcta." : "Letra incorrecta.");
        }

        txtLetra.setText("");
    }
    
    private void actualizarPantalla() {
        lblPalabra.setText(formatearPalabra(jogo.getPalabraActual()));
        lblIntentos.setText("Intentos restantes: " + (6 - jogo.getIntentos()));
        lblLetrasUsadas.setText("Letras usadas: " + jogo.getLetrasUsadas().toString());
        panelFigura.repaint();
        btnProbar.setEnabled(true);
    }
    
    private String formatearPalabra(String palabra) {
        StringBuilder sb = new StringBuilder();
        for (char c : palabra.toCharArray()) {
            sb.append(c).append(" ");
        }
        return sb.toString().trim();
    }
    
    private void dibujarAhorcado(Graphics g) {
        // Dibujar la base del ahorcado
        g.setColor(Color.BLACK);
        g.drawLine(50, 200, 150, 200); // suelo
        g.drawLine(100, 200, 100, 50); // poste vertical
        g.drawLine(100, 50, 200, 50);  // barra superior
        g.drawLine(200, 50, 200, 80);  // cuerda

        // Dibujar las partes según los errores
        int errores = jogo.getIntentos();
        switch (errores) {
            case 6: g.drawLine(200, 180, 220, 210); // pierna derecha
            case 5: g.drawLine(200, 180, 180, 210); // pierna izquierda
            case 4: g.drawLine(200, 120, 220, 160); // brazo derecho
            case 3: g.drawLine(200, 120, 180, 160); // brazo izquierdo
            case 2: g.drawLine(200, 100, 200, 180); // cuerpo
            case 1: g.drawOval(180, 80, 40, 40);    // cabeza
                break;
            default:
                break;
        }
    }
}