/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcadoprogra2lab_samuel_leonardo;

/**
 *
 * @author unwir
 */
public class AdminPalabrasSecretas {
    private static AdminPalabrasSecretas instance;
    
    public static AdminPalabrasSecretas getInstance(){
        if(instance == null){
            instance = new AdminPalabrasSecretas();
        }
        return instance;
    }
    
}
