/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_alegra.entities.vo.siigo;

/**
 * 
 * Rutina para capturar numero de consecutivo interno y numero de historia para log
 * del servidor
 *
 * @author Brayan Bernal
 * @since 10 Julio 2025 10:31 PM
 */
public class ValoresInternosUsuarioVO {
    
    private String numero_consecutivo_interno;
    private String numero_historia_usuario;

    public String getNumero_consecutivo_interno() {
        return numero_consecutivo_interno;
    }

    public void setNumero_consecutivo_interno(String numero_consecutivo_interno) {
        this.numero_consecutivo_interno = numero_consecutivo_interno;
    }

    public String getNumero_historia_usuario() {
        return numero_historia_usuario;
    }

    public void setNumero_historia_usuario(String numero_historia_usuario) {
        this.numero_historia_usuario = numero_historia_usuario;
    }
    
    
}
