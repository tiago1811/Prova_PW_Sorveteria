package tads.ufrn.sorveteria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import tads.ufrn.sorveteria.models.Usuario;
import tads.ufrn.sorveteria.services.SorveteService;
import tads.ufrn.sorveteria.services.UsuarioService;

import java.util.List;

@Controller
public class UsuarioController {

    UsuarioService service;
    SorveteService sorvService;


    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/cadastrarUsuario")
    public String doCadastrarUsuario(Model model){

        Usuario u = new Usuario();
        model.addAttribute("usuario", u);

        return "cadastrarUsuario";
    }

    @PostMapping("/doSalvarUsuario")
    public String doSalvarUsuario(@ModelAttribute Usuario u){
        service.create(u);

        return "redirect:/";
    }

    @GetMapping("/listUsuarios")
    public String listAll(){
        List<Usuario> usuarios = service.listAll();
        for (Usuario u : usuarios){
            System.out.println(u);
        }

        return "listUsuarios";
    }
}
