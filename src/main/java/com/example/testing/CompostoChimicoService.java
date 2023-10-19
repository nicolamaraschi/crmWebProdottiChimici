package com.example.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompostoChimicoService {

    @Autowired
    private CompostoChimicoRepository compostoChimicoRepository;


    public List<CompostoChimico> cercaCompostiAvanzata(String nome, String formulaChimica, Double pesoMolecolare, Double quantitaDisponibile, String operatoreLogico) {
        // Inizializza una lista vuota per i risultati della ricerca
        List<CompostoChimico> risultatiRicerca = new ArrayList<>();

        // Esegui la ricerca in base ai parametri forniti
        if (operatoreLogico.equals("AND")) {
            // Ricerca con operatore logico AND (tutti i parametri devono essere soddisfatti)
            risultatiRicerca = compostoChimicoRepository.findByNomeContainingAndFormulaChimicaContainingAndPesoMolecolareAndQuantitaDisponibile(nome, formulaChimica, pesoMolecolare, quantitaDisponibile);
        } else if (operatoreLogico.equals("OR")) {
            // Ricerca con operatore logico OR (almeno uno dei parametri deve essere soddisfatto)
            if (nome != null) {
                risultatiRicerca.addAll(compostoChimicoRepository.findByNomeContaining(nome));
            }
            if (formulaChimica != null) {
                risultatiRicerca.addAll(compostoChimicoRepository.findByFormulaChimicaContaining(formulaChimica));
            }
            if (pesoMolecolare != null) {
                risultatiRicerca.addAll(compostoChimicoRepository.findByPesoMolecolare(pesoMolecolare));
            }
            if (quantitaDisponibile != null) {
                risultatiRicerca.addAll(compostoChimicoRepository.findByQuantitaDisponibile(quantitaDisponibile));
            }
        }

        // Rimuovi duplicati dai risultati
        //risultatiRicerca = risultatiRicerca.stream().distinct().collect(Collectors.toList());

        return risultatiRicerca;
    }
}
