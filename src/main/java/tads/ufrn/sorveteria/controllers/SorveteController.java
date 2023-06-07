package tads.ufrn.sorveteria.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tads.ufrn.sorveteria.models.Sorvete;
import tads.ufrn.sorveteria.services.FileStorageService;
import tads.ufrn.sorveteria.services.SorveteService;

import java.util.List;
import java.util.Optional;

@Controller
public class SorveteController {
    SorveteService sorveteService;
    private final FileStorageService fileStorageService;


    public SorveteController(SorveteService sorveteService, FileStorageService fileStorageService) {
        this.sorveteService = sorveteService;
        this.fileStorageService = fileStorageService;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String getIndex(Model model) {

        List<Sorvete> sorvete = sorveteService.listar();

        model.addAttribute("listar", sorvete);

        return "index";
    }

    @GetMapping("/cadastro")
    public String getCadastrarPage(Model model) {
        Sorvete sorvete = new Sorvete();
        model.addAttribute("sorvete", sorvete);
        return "cadastroSorvete";
    }

    @PostMapping("/salvar")
    public String doSalvar(@ModelAttribute @Valid Sorvete s, Errors errors, @RequestParam(name = "file") MultipartFile file, RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return "cadastroSorvete";
        } else {

            s.setImagemUri(file.getOriginalFilename());
            sorveteService.editar(s);
            fileStorageService.save(file);

            redirectAttributes.addFlashAttribute("mensagem", "Ação concluída com sucesso.");
            sorveteService.salvar(s);
            return "redirect:/admin";
        }
    }

    @GetMapping("/sobre")
    public String getSobrePage(Model model) {
        return "sobre";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        List<Sorvete> sorvete = sorveteService.listar();
        model.addAttribute("listar", sorvete);
        return "gerenciarSorvetes";
    }

    @GetMapping(value = "/deletar/{id}")
    public String deletarFruta(@PathVariable long id, RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("mensagem", "O sorvete excluído com sucesso.");

        sorveteService.deletar(id);

        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String getEditarPage(@PathVariable(name = "id") Long id, Model model){

        Optional<Sorvete> f = sorveteService.buscarPorId(id);

        if (f.isPresent()){
            model.addAttribute("sorvete", f.get());
        }else{
            return "redirect:/admin";
        }

        return "editarSorvete";
    }
}
