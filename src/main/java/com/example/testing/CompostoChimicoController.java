package com.example.testing;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class CompostoChimicoController {

    @Autowired
    private final CompostoChimicoRepository compostoChimicoRepository;

    @Autowired
    public CompostoChimicoController(CompostoChimicoRepository compostoChimicoRepository) {
        this.compostoChimicoRepository = compostoChimicoRepository;
    }
/*
    @PostMapping
    public ResponseEntity<?> createComposto(@RequestBody CompostoChimico compostoChimico) {
        CompostoChimico nuovoComposto = compostoChimicoRepository.save(compostoChimico);
        if (nuovoComposto != null) return ResponseEntity.ok("Inserimento riuscito.");// Inserimento nel database riuscito
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inserimento fallito."); // Inserimento nel database fallito
    }

    @GetMapping("/{id}")
    public Optional<CompostoChimico> getCompostoById(@PathVariable Long id) {
        return compostoChimicoRepository.findById(id);
    }
 */
@PostMapping("/api/inserisci")
public ResponseEntity<?> createComposto(@RequestBody CompostoChimico compostoChimico) {
    try {
        int result = compostoChimicoRepository.insertComposto(
                compostoChimico.getNome(),
                compostoChimico.getFormulaChimica(),
                compostoChimico.getPesoMolecolare(),
                compostoChimico.getQuantitaDisponibile(),
                compostoChimico.getNote(),
                compostoChimico.getDataScadenza()
        );

        if (result > 0) {
            return ResponseEntity.ok("Inserimento riuscito.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inserimento fallito.");
        }
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inserimento fallito.");
    }
}
    @GetMapping("/api/listaComposti")
    public ResponseEntity<List<CompostoChimico>> getComposti() {
        List<CompostoChimico> composti = compostoChimicoRepository.trovaTutti(); // Sostituisci con il metodo corretto per ottenere i composti
        return ResponseEntity.ok(composti);

    }


    @PutMapping("/api/modificaComposto/{id}")
    public ResponseEntity<String> updateComposto(@PathVariable Long id, @RequestBody CompostoChimico updatedComposto) {
        // Verifica se l'ID esiste nel database
        CompostoChimico existingComposto = compostoChimicoRepository.findCompostoById(id);
        if (existingComposto != null) {
            compostoChimicoRepository.updateComposto(
                    id,
                    updatedComposto.getNome(),
                    updatedComposto.getFormulaChimica(),
                    updatedComposto.getPesoMolecolare(),
                    updatedComposto.getQuantitaDisponibile()
            );
        return ResponseEntity.ok("Aggiornamento riuscito");
    }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Composto non trovato con ID: " + id);
        }
    }



    private boolean isScadenzaImminente(CompostoChimico composto) {
        // Implementa la logica per determinare se la scadenza � imminente
        // Ad esempio, confronta la data di scadenza con la data odierna
        // Restituisci true se � imminente, altrimenti false.
        // Questa � una logica di esempio e dovresti personalizzarla secondo le tue esigenze.
        return composto.getDataScadenza() != null
                && composto.getDataScadenza().before(new Date());
    }


    @DeleteMapping("/api/eliminaComposto/{id}")
    public void deleteComposto(@PathVariable Long id) {
        compostoChimicoRepository.deleteCompostoById(id);
    }



    @GetMapping("/cercaPerNome")
    public List<CompostoChimico> cercaCompostiPerNome(@RequestParam String nome) {
        return compostoChimicoRepository.findByNomeContaining(nome);
    }



    // Filtra i composti chimici per nome
    @GetMapping("/filtrapernome")
    public List<CompostoChimico> filtraCompostiPerNome(@RequestParam String nome) {
        return compostoChimicoRepository.findByNomeContaining(nome);
    }
    @Autowired
    private CompostoChimicoService compostoChimicoService;

    @PostMapping("/ricerca-avanzata")
    public ResponseEntity<String> effettuaRicercaAvanzata(@RequestParam(required = false) String nomeField,
                                                          @RequestParam(required = false) String formulaField,
                                                          @RequestParam(required = false) Double pesoMolecolareField,
                                                          @RequestParam(required = false) Double quantitaDisponibileField,
                                                          @RequestParam String operatoriLogiciCombo) {
        // Implementa la logica per la ricerca avanzata in base ai parametri ricevuti

        // Esempio di logica di ricerca
        List<CompostoChimico> risultatiRicerca = compostoChimicoService.cercaCompostiAvanzata(nomeField, formulaField, pesoMolecolareField, quantitaDisponibileField, operatoriLogiciCombo);

        if (!risultatiRicerca.isEmpty()) {
            // Se ci sono risultati, restituisci una risposta positiva (200 OK) con i risultati
            return ResponseEntity.ok("Ricerca riuscita. Trovati " + risultatiRicerca.size() + " risultati.");
        } else {
            // Se non ci sono risultati, restituisci una risposta negativa (404 Not Found)
            return ResponseEntity.notFound().build();
        }
    }

    // Ordina i composti chimici per data di scadenza
    @GetMapping("/api/ordinaperdata")
    public List<CompostoChimico> ordinaCompostiPerDataScadenza() {
        return compostoChimicoRepository.findAllByOrderByDataScadenza();
    }


}
