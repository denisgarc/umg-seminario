/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.model;

import app.consultas.dal.UsuarioFacade;
import app.consultas.entities.Usuario;
import javax.ejb.EJB;

/**
 *
 * @author DOxlaj
 */
public class cUsuario {
    @EJB
    private UsuarioFacade usrFacade;
    
    public cUsuario(){
        //usrFacade = new UsuarioFacade();
    }
    
    public Usuario filterUser(int id){
        return usrFacade.find(id);
    }
}
