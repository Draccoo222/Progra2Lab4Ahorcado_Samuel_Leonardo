/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcadoprogra2lab_samuel_leonardo;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author hnleo
 */
public class AhorcadoAzarPantalla extends JFrame{
     private JuegoAhorcadoAzar jogo = new JuegoAhorcadoAzar();
    
    private JLabel lblPalabra;
    private JLabel lblIntentos;
    private JLabel lblLetrasUsadas;
    private JTextField txtLetra;
    private JButton btnProbar;
    
    public AhorcadoAzarPantalla(){
        jogo.inicializarPalabrasSecretas();
        initComponets();
    }
    
    private void initComponets(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(3, 1));

        lblPalabra = new JLabel("", SwingConstants.CENTER);
        lblPalabra.setFont(new Font("Arial", Font.BOLD, 24));
        panelCentro.add(lblPalabra);

        lblIntentos = new JLabel("", SwingConstants.CENTER);
        panelCentro.add(lblIntentos);

        lblLetrasUsadas = new JLabel("", SwingConstants.CENTER);
        panelCentro.add(lblLetrasUsadas);

        add(panelCentro, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        txtLetra = new JTextField(5);
        btnProbar = new JButton("Probar");

        btnProbar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                probarLetra();
            }
        });

        panelInferior.add(new JLabel("Letra:"));
        panelInferior.add(txtLetra);
        panelInferior.add(btnProbar);

        add(panelInferior, BorderLayout.SOUTH);

        setVisible(true);
    
    }
    
    
    public void probarLetra(){
        String input = txtLetra.getText().trim().toUpperCase();
        
        if(input.length() != 1){
            JOptionPane.showMessageDialog(null, "Solo se permite una letra.");
            return;
        }
        
        char letra = input.charAt(0);
        
        if(!Character.isLetter(letra)){
            JOptionPane.showMessageDialog(null, "Solo se permiten letras.");
            return;
        }
        
       jogo.verificarLetra(letra);
       jogo.letrasUsadas.add(letra);
       
       actualizarPantalla();
       
         if (jogo.hasGanado()) {
            JOptionPane.showMessageDialog(this, "¡Ganaste! La palabra era: " + jogo.palabraSecreta);
            this.dispose();
        } else if (jogo.intentos >= 6) {
            JOptionPane.showMessageDialog(this, "Perdiste. La palabra era: " + jogo.palabraSecreta);
            this.dispose();
        }

        txtLetra.setText("");
        
    }
    
    
    
    private void actualizarPantalla() {
        lblPalabra.setText(jogo.palabraActual.replace("", " ").trim());
        lblIntentos.setText("Intentos: " + jogo.intentos + " / 6");
        lblLetrasUsadas.setText("Letras usadas: " + jogo.letrasUsadas.toString());
    }
    
}
