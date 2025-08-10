/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_alegra.entities.vo.siigo;

import webservice_alegra.util.Utils;

/**
 * Clase que representa la lista de destinatarios para enviar documentos por correo electrónico.
 * Contiene las direcciones principales y de copia.
 *
 * @author Brayan Bernal
 * @since 10 Julio 2025 10:31 PM
 */
public class EmailListOfContactsVO {
    
    private String mail_to;
    private String copy_to;

    public String getMail_to() {
        return mail_to;
    }

    public void setMail_to(String mail_to) {
        this.mail_to = mail_to;
    }

    public String getCopy_to() {
        return copy_to;
    }

    public void setCopy_to(String copy_to) {
        this.copy_to = copy_to;
    }
    
    public String toJson() {
        return Utils.convertObjectToJSON(this);
    }
    
    
}
