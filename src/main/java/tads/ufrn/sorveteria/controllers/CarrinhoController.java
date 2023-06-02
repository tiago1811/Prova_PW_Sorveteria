package tads.ufrn.sorveteria.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tads.ufrn.sorveteria.models.Sorvete;
import tads.ufrn.sorveteria.services.SorveteService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CarrinhoController {
    SorveteService sorveteService;


    public CarrinhoController(SorveteService sorveteService) {
        this.sorveteService = sorveteService;
    }

    @GetMapping("/verCarrinho")
    public String verCarrinho(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            List<Sorvete> carrinho = (List<Sorvete>) session.getAttribute("carrinho");

            if (carrinho != null && !carrinho.isEmpty()) {
                model.addAttribute("carrinho", carrinho);
            } else {
                model.addAttribute("mensagem", "Carrinho vazio");
            }
        } else {
            model.addAttribute("mensagem", "Erro de sessão");
        }

        return "carrinho";
    }

    @GetMapping("/adicionarCarrinho")
    public void adicionarCarrinho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Optional<Sorvete> sorveteOptional = sorveteService.buscarPorId(id);

        if (sorveteOptional.isPresent()) {
            Sorvete sorvete = sorveteOptional.get();
            HttpSession session = request.getSession(true);

            List<Sorvete> carrinho = (List<Sorvete>) session.getAttribute("carrinho");
            if (carrinho == null) {
                carrinho = new ArrayList<>();
                session.setAttribute("carrinho", carrinho);
            }

            carrinho.add(sorvete);

            int valorCarrinho = carrinho.size();
            Cookie cookie = new Cookie("visita", String.valueOf(valorCarrinho));

            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
        }

        response.sendRedirect("/index");
    }

}
