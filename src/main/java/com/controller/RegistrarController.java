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
import com.util.Cifrar;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author jorge
 */
@Controller
@SessionAttributes({"userSession"})
public class RegistrarController {

    HttpSession sesion;
    String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public HttpSession getSesion() {
        return sesion;
    }

    public void setSesion(HttpSession sesion) {
        this.sesion = sesion;
    }

    @RequestMapping("registrar.htm")
    public ModelAndView Registrar() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("usuarios/registrar");
        //  mav.addObject("usuario", new Users());
        return mav;
    }

    @RequestMapping(value = "validarRegistro.htm", method = RequestMethod.POST)
    public ModelAndView ValidarRegistro(HttpServletRequest request) {
        String mensaje = null;
        sesion = request.getSession();
        ModelAndView mav = new ModelAndView();
        Usuarios usuario = new Usuarios();
        UsuariosDao dao = new UsuariosDao();
        try {
            //cargamos los datos en un objeto usuario
            usuario.setCodigoArea(Integer.parseInt(request.getParameter("codigo")));
            usuario.setTelefono(request.getParameter("telefono"));
            usuario.setStatus(request.getParameter("status"));
            usuario.setPais(request.getParameter("pais"));
            usuario.setNombre(request.getParameter("nombre"));
            Cifrar varCifrar = new Cifrar();
            String pass = varCifrar.Encriptar(request.getParameter("password"));
            usuario.setPassword(pass);

            //verificamos si se crea el usuario
            if (dao.createUsuarios(usuario) == true) {
                String sesUser = usuario.getCodigoArea() + usuario.getTelefono();
                sesion.setAttribute("usuario", sesUser);;
                mensaje = "Bienvenido";
                //mav.setViewName("usuarios/indexu");
                mav.setViewName("usuarios/confirmPhone");
            } else {

                mensaje = "LOS DATOS NO SON CORRECTOS";
                mav.setViewName("login/login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mav;
    }
    public void createCodigo()
    {
        this.setCodigo("456789");
    }
            

    @RequestMapping("confirmPhone.htm")
    public ModelAndView getConfirm() {
        ModelAndView mav = new ModelAndView();
       String mensaje = "Ingrese el codigo que recibio en su telefono";
        mav.addObject("mensaje", mensaje);
        mav.setViewName("usuarios/confirmPhone");
        return mav;
    }

    @RequestMapping(value = "validarPhone.htm", method = RequestMethod.POST)
    public ModelAndView ValidarPhone(HttpServletRequest request) {
        String mensaje = null;
        sesion = request.getSession();
        ModelAndView mav = new ModelAndView();
        String codigo2 = request.getParameter("codigo");
        try {
            if (this.getCodigo().compareTo(codigo2) == 0) {
                
                mav.setViewName("panel/panel");
            } else {

                mensaje = "El codigo ingreado no es correcto, por favor intente de nuevo";
                mav.addObject("mensaje", mensaje);
                mav.setViewName("usuarios/confirmPhone");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mav;
    }

    @ModelAttribute("codigo")
    public String obtenerCodigo() {
        this.createCodigo();
        return this.getCodigo();
    }

    @ModelAttribute("listaCodigos")
    public List<String> getAllCodigos() {
        List<String> lista = new ArrayList<String>();
        lista.add("1");//EEUU
        lista.add("1");//canada
        lista.add("51");//Peru
        lista.add("52");//Mexico
        lista.add("53");//Cuba
        lista.add("56");//Chile
        lista.add("1");//canada
        lista.add("502");//Guatemala
        lista.add("503");//El Salvador
        lista.add("504");//Honduras
        lista.add("505");//Nicaragua
        lista.add("506");//Costa Rica
        lista.add("507");//panama   
        return lista;
    }

}
