/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.entitys.Usuarios;
import com.dao.UsuariosDao;
import java.util.List;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author jorge
 */
@Controller
@SessionAttributes({"userSession"})
public class UsuariosController {
    
    
    
     //creando un metodo para guaradar el login
    
    // metodo que devuele la vista index de los usuarios, en donde se muestran los usuarios registrados
    @RequestMapping("indexu.htm")
    public ModelAndView getUsuarios() {
        ModelAndView mav = new ModelAndView();
        UsuariosDao userHelper = new UsuariosDao();
        String mensaje =null;
           mav.setViewName("usuarios/indexu");
           mav.addObject("mensaje", mensaje);
        
        return mav; 
    }
    
    //al llamar a la vista index para mostrar el usuario, esta requerira de la lista de usuarios 
    // la cual se almacenan en este atributo

    @ModelAttribute("usuariosl")
    public List<Usuarios> listaUsuarios() {

        //Data referencing for web framework checkboxes
        ModelAndView mav = new ModelAndView();
        UsuariosDao userHelper = new UsuariosDao();
        List<Usuarios> usuariosl = userHelper.getAllUsuarios();
        return usuariosl;

    }
    
   
    
// este metodo devolvera la vista del login vacia 

    @RequestMapping("login.htm")
    public ModelAndView Login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("usuarios/login");
        return mav;

    }

// este metodo sirve para validar el login

    @RequestMapping(value = "validarLogin.htm", method = RequestMethod.POST)
    public ModelAndView Validarlogin(HttpServletRequest request) {
        // creamos los objetos a utilizar
        ModelAndView mav = new ModelAndView();
        Usuarios usuario = new Usuarios();
        Usuarios user2 = new Usuarios();
        UsuariosDao dao = new UsuariosDao();
        String mensaje=null;
        try {

            // recogemos los parametros
            usuario.setCodigoArea(Integer.parseInt(request.getParameter("codigo")));
            usuario.setTelefono(request.getParameter("telefono"));
            usuario.setPassword(request.getParameter("password"));

            user2 = dao.getUsuario(usuario.getTelefono());
            String area = String.valueOf(user2.getCodigoArea());
            String area2 = String.valueOf(usuario.getCodigoArea());

            if ((usuario.getTelefono().compareTo(user2.getTelefono()) == 0) && (area.compareTo(area2) == 0) && (user2.getPassword().compareTo(usuario.getPassword()) == 0)) {
               
                mensaje = "Bienvenido";
                mav.setViewName("usuarios/indexu");
            } else {
                System.out.println("ha ocurrido un error");
                mensaje ="LOS DATOS NO SON CORRECTOS";
                mav.setViewName("usuarios/login");
            }

        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("ha ocurrido un error");
            mensaje = "Ocurrio un error en la validacion de sus datos";
            mav.setViewName("usuarios/login");
        }
        mav.addObject("mensaje", mensaje);

        return mav;
    }
    // este metodo devolvera la vista para registrar a un usuario. tambien vacia

    @RequestMapping("registrar.htm")
    public ModelAndView Registrar() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("usuarios/registrar");
        //  mav.addObject("usuario", new Users());
        return mav;
    }

    @RequestMapping(value = "validarRegistro.htm", method = RequestMethod.POST)
    public ModelAndView ValidarRegistro(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        Usuarios usuario = new Usuarios();
        usuario.setCodigoArea(Integer.parseInt(request.getParameter("codigo")));
        usuario.setTelefono(request.getParameter("telefono"));
        usuario.setPassword(request.getParameter("password"));
        usuario.setStatus(request.getParameter("status"));
        usuario.setPais(request.getParameter("pais"));
        usuario.setNombre(request.getParameter("nombre"));

        UsuariosDao dao = new UsuariosDao();
        if (dao.createUsuarios(usuario)) {
            System.out.println("Se inserto con exito");
        } else {
            System.out.println("ha ocurrido un error");
        }

        mav.setViewName("usuarios/validarRegistro");
        return mav;
    }
    // este medoto recibe el numero del telefono del usuario, para mostrar su configuracion.

    @RequestMapping(value = "editar.htm", method = RequestMethod.GET)
    public ModelAndView editar(HttpServletRequest request) {
        Usuarios usuario = new Usuarios();
        UsuariosDao dao = new UsuariosDao();
        usuario.setTelefono(request.getParameter("telefono"));
        usuario = dao.getUsuario(usuario.getTelefono());
        ModelAndView mav = new ModelAndView();
        mav.addObject("usuario", usuario);
        mav.setViewName("usuarios/editar");
        return mav;
    }

}
