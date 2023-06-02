package tads.ufrn.sorveteria;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tads.ufrn.sorveteria.models.Usuario;
import tads.ufrn.sorveteria.repositorys.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SorveteriaApplication implements WebMvcConfigurer {

    @Bean
    CommandLineRunner commandLineRunner(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        return args -> {
            List<Usuario> users = Stream.of(
                    new Usuario(1L, "user", "323.456.789-10", "admin", encoder.encode("admin"), true),
                    new Usuario(2L, "Tiago", "472.456.789-10", "tiago", encoder.encode("tiago123"), false),
                    new Usuario(3L, "Israel", "845.456.789-10", "israel", encoder.encode("israel123"), false)//não é admin, não pode alterar no produto
            ).collect(Collectors.toList());

            for (var e : users) {
                System.out.println(e);
            }
            usuarioRepository.saveAll(users);
        };
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Register resource handler for images
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/");
    }

    public static void main(String[] args) {
        SpringApplication.run(SorveteriaApplication.class, args);
    }

}