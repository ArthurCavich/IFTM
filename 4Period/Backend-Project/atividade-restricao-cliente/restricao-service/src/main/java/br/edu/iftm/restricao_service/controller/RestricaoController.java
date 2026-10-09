import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication 
@RestController 

public class RestricaoController {
    @GetMapping ("/{cpf}")
    public Map<String, Object> checar
}
