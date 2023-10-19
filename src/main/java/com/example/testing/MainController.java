package com.example.testing;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MainController implements ErrorController {

    @GetMapping("/main")
    public String mainPage() {
        return "main"; // Restituisce il nome della vista (senza estensione .html)
    }
    @GetMapping("/visualizzaAggiungi_composto")
    public String visualizzaAggiungi_composto() {
        return "visualizzaAggiungi_composto"; // Restituisce il nome della vista (senza estensione .html)
    }
    @RequestMapping("/error")
    public String handleError() {
        // Logica per gestire l'errore, come registrare l'errore o reindirizzare a una pagina specifica di errore
        return "error"; // Restituisce il nome della vista per la pagina di errore personalizzata (senza estensione .html)
    }

    @GetMapping("/ordina_scadenza")
    public String ordina_scadenza() {
        return "ordina_scadenza"; // Restituisce il nome della vista (senza estensione .html)
    }

    @GetMapping("/ricerca_nome")
    public String ricercaNome() {
        return "ricerca_nome"; // Restituisce il nome della vista (senza estensione .html)
    }

    @GetMapping("/ricerca_avanzata")
    public String ricercaAvanzata() {
        return "ricerca_avanzata"; // Restituisce il nome della vista (senza estensione .html)
    }
    @GetMapping("/modifica_composto")
    public String modificaComposto() {
        return "modifica_composto"; // Restituisce il nome della vista (senza estensione .html)
    }

    @GetMapping("/elimina_composto")
    public String eliminaComposto() {
        return "elimina_composto"; // Restituisce il nome della vista (senza estensione .html)
    }
    @GetMapping("/listaComposti")
    public String listaComposti() {
        return "listaComposti"; // Restituisce il nome della vista (senza estensione .html)
    }


}
