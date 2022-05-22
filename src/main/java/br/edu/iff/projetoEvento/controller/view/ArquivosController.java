
package br.edu.iff.projetoEvento.controller.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path = "/pdf")
public class ArquivosController {
@GetMapping(path = "/pdf/{nome}")
    public void getPdfFile(@PathVariable("nome") String nome, HttpServletResponse response){
        Path caminho = Paths.get("uploads", nome);
        
        if(Files.exists(caminho)){
            response.addHeader("Content-type", MediaType.APPLICATION_PDF_VALUE);
            
            try {
                Files.copy(caminho, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                throw new RuntimeException("Falha ao carregar o arquivo.");
            }            
        }else{
            throw new RuntimeException("Arquivo não existe.");
        }
    }
}
