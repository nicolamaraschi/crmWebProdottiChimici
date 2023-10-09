package com.example.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CompostoChimicoControllerTest {

    @Mock
    private CompostoChimicoRepository compostoChimicoRepository;

    private CompostoChimicoController controller;


    @Test
    public void testCreateComposto_InsertsElementInDatabase() {
        // Crea un oggetto CompostoChimico con dati noti
        CompostoChimico compostoChimico = new CompostoChimico();
        compostoChimico.setNome("Nome di prova");
        compostoChimico.setFormulaChimica("Formula di prova");

        // Chiama il metodo createComposto del controller
        ResponseEntity<?> response = controller.createComposto(compostoChimico);

        // Verifica che il controller restituisca una risposta HTTP OK (200)
        assertEquals(200, response.getStatusCodeValue());

        // Verifica che l'elemento sia stato inserito nel database
        CompostoChimico elementoNelDatabase = compostoChimicoRepository.findByNome("Nome di prova");
        assertNotNull(elementoNelDatabase);
        assertEquals("Formula di prova", elementoNelDatabase.getFormulaChimica());
    }


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new CompostoChimicoController(compostoChimicoRepository);
    }


    @Test
    void testCreateComposto_Success() {
        CompostoChimico compostoChimico = new CompostoChimico();
        when(compostoChimicoRepository.save(compostoChimico)).thenReturn(compostoChimico);

        ResponseEntity<?> response = controller.createComposto(compostoChimico);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Inserimento riuscito.", response.getBody());
    }

    @Test
    void testCreateComposto_Failure() {
        CompostoChimico compostoChimico = new CompostoChimico();
        when(compostoChimicoRepository.save(compostoChimico)).thenReturn(null);

        ResponseEntity<?> response = controller.createComposto(compostoChimico);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Inserimento fallito.", response.getBody());
    }


    /*
    @Test
    void testGetCompostoById_Success() {
        Long compostoId = 1L;
        CompostoChimico compostoChimico = new CompostoChimico();
        when(compostoChimicoRepository.findById(compostoId)).thenReturn(Optional.of(compostoChimico));

        Optional<CompostoChimico> response = controller.getCompostoById(compostoId);

        assertTrue(response.isPresent());
        assertEquals(compostoChimico, response.get());
    }

    @Test
    void testGetCompostoById_NotFound() {
        Long compostoId = 1L;
        when(compostoChimicoRepository.findById(compostoId)).thenReturn(Optional.empty());

        Optional<CompostoChimico> response = controller.getCompostoById(compostoId);

        assertFalse(response.isPresent());
    }

    @Test
    void testUpdateComposto_Success() {
        Long compostoId = 1L;
        CompostoChimico updatedComposto = new CompostoChimico();

        when(compostoChimicoRepository.findById(compostoId)).thenReturn(Optional.of(new CompostoChimico()));

        ResponseEntity<String> response = controller.updateComposto(compostoId, updatedComposto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Aggiornamento riuscito", response.getBody());
    }

*/
    @Test
    void testUpdateComposto_NotFound() {
        Long compostoId = 1L;
        CompostoChimico updatedComposto = new CompostoChimico();

        when(compostoChimicoRepository.findById(compostoId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = controller.updateComposto(compostoId, updatedComposto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Composto non trovato con ID: " + compostoId, response.getBody());
    }

    @Test
    void testCercaCompostiPerNome_Success() {
        String nome = "Sample";
        List<CompostoChimico> compostiChimici = new ArrayList<>();
        compostiChimici.add(new CompostoChimico());
        when(compostoChimicoRepository.findByNomeContaining(nome)).thenReturn(compostiChimici);

        List<CompostoChimico> response = controller.cercaCompostiPerNome(nome);

        assertFalse(response.isEmpty());
        assertEquals(compostiChimici, response);
    }

    @Test
    void testCercaCompostiPerNome_NoResults() {
        String nome = "NonExistent";
        when(compostoChimicoRepository.findByNomeContaining(nome)).thenReturn(new ArrayList<>());

        List<CompostoChimico> response = controller.cercaCompostiPerNome(nome);

        assertTrue(response.isEmpty());
    }

    // Aggiungi altri casi di test per gli altri metodi del controller...
}
