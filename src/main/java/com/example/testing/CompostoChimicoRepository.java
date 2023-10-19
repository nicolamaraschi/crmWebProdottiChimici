package com.example.testing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CompostoChimicoRepository extends JpaRepository<CompostoChimico, Long> {

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM composti_chimici WHERE nome LIKE %:nome%", nativeQuery = true)
    List<CompostoChimico> findByNomeContaining(@Param("nome") String nome);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM composti_chimici WHERE formula_chimica LIKE %:formulaChimica%", nativeQuery = true)
    Collection<? extends CompostoChimico> findByFormulaChimicaContaining(@Param("formulaChimica") String formulaChimica);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM composti_chimici WHERE peso_molecolare LIKE :pesoMolecolare", nativeQuery = true)
    Collection<? extends CompostoChimico> findByPesoMolecolare(@Param("pesoMolecolare") Double pesoMolecolare);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM composti_chimici WHERE quantita_disponibile LIKE :quantitaDisponibile", nativeQuery = true)
    Collection<? extends CompostoChimico> findByQuantitaDisponibile(@Param("quantitaDisponibile") Double quantitaDisponibile);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM composti_chimici WHERE nome LIKE %:nome% AND formula_chimica LIKE %:formulaChimica% AND peso_molecolare = :pesoMolecolare AND quantita_disponibile = :quantitaDisponibile", nativeQuery = true)
    List<CompostoChimico> findByNomeContainingAndFormulaChimicaContainingAndPesoMolecolareAndQuantitaDisponibile(
            @Param("nome") String nome,
            @Param("formulaChimica") String formulaChimica,
            @Param("pesoMolecolare") Double pesoMolecolare,
            @Param("quantitaDisponibile") Double quantitaDisponibile);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM composti_chimici WHERE nome = 'nomeDiProva'", nativeQuery = true)
    CompostoChimico findByNome(String nomeDiProva);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM composti_chimici ORDER BY data_scadenza ASC;", nativeQuery = true)
    List<CompostoChimico> findAllByOrderByDataScadenza();

    // Esempio di query personalizzata per cercare composti scaduti
    @Query("SELECT c FROM CompostoChimico c WHERE c.dataScadenza < CURRENT_DATE")
    List<CompostoChimico> findCompostiScaduti();

    // Esempio di query personalizzata per cercare composti disponibili con una quantit� minima
    @Query("SELECT c FROM CompostoChimico c WHERE c.quantitaDisponibile >= :quantitaMinima")
    List<CompostoChimico> findCompostiConQuantitaMinima(@Param("quantitaMinima") Double quantitaMinima);

    // Esempio di query personalizzata per cercare composti in base a una data di scadenza specifica
    @Query("SELECT c FROM CompostoChimico c WHERE c.dataScadenza = :dataScadenza")
    List<CompostoChimico> findCompostiPerDataScadenza(@Param("dataScadenza") Date dataScadenza);




    @Modifying
    @Transactional
    @Query(value = "INSERT INTO composti_chimici (nome, formula_chimica, peso_molecolare, quantita_disponibile, note, data_scadenza) " +
            "VALUES (:nome, :formulaChimica, :pesoMolecolare, :quantitaDisponibile, :note, :dataScadenza)", nativeQuery = true)
    int insertComposto(@Param("nome") String nome,
                       @Param("formulaChimica") String formulaChimica,
                       @Param("pesoMolecolare") Double pesoMolecolare,
                       @Param("quantitaDisponibile") Double quantitaDisponibile,
                       @Param("note") String note,
                       @Param("dataScadenza") Date dataScadenza);
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM composti_chimici", nativeQuery = true)
    List<CompostoChimico> trovaTutti();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM composti_chimici WHERE id = :id", nativeQuery = true)
    void deleteCompostoById(@Param("id") Long id);

    @Query(value = "SELECT * FROM composti_chimici WHERE id = :id", nativeQuery = true)
    CompostoChimico findCompostoById(@Param("id") Long id);


    @Modifying
    @Transactional
    @Query(value = "UPDATE composti_chimici SET nome = :nome, formula_chimica = :formulaChimica, peso_molecolare = :pesoMolecolare, quantita_disponibile = :quantita_disponibile WHERE id = :id", nativeQuery = true)
    void updateComposto(@Param("id") Long id,
                        @Param("nome") String nome,
                        @Param("formulaChimica") String formulaChimica,
                        @Param("pesoMolecolare") Double pesoMolecolare,
                        @Param("quantita_disponibile") Double quantitaDisponibile);

}

