package com.example.testing;

import jakarta.persistence.*;

import java.util.Date;


@Entity
public class CompostoChimico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String formulaChimica;
    private Double pesoMolecolare;
    private Double quantitaDisponibile; // Aggiunto attributo per la quantit� disponibile
    private String note;

    @Temporal(TemporalType.DATE)
    private Date dataScadenza;

    public CompostoChimico() {
        // Costruttore vuoto necessario per JPA
    }

    public CompostoChimico(String nome, String formulaChimica, Double pesoMolecolare, Double quantitaDisponibile, String note) {
        this.nome = nome;
        this.formulaChimica = formulaChimica;
        this.pesoMolecolare = pesoMolecolare;
        this.quantitaDisponibile = quantitaDisponibile;
        this.note = note;
    }

    public CompostoChimico(String nome, String formulaChimica, double pesoMolecolare, double quantitaDisponibile) {
        this.nome = nome;
        this.formulaChimica = formulaChimica;
        this.pesoMolecolare = pesoMolecolare;
        this.quantitaDisponibile = quantitaDisponibile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFormulaChimica() {
        return formulaChimica;
    }

    public void setFormulaChimica(String formulaChimica) {
        this.formulaChimica = formulaChimica;
    }

    public Double getPesoMolecolare() {
        return pesoMolecolare;
    }

    public void setPesoMolecolare(Double pesoMolecolare) {
        this.pesoMolecolare = pesoMolecolare;
    }

    public Double getQuantitaDisponibile() {
        return quantitaDisponibile;
    }

    public void setQuantitaDisponibile(Double quantitaDisponibile) {
        this.quantitaDisponibile = quantitaDisponibile;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    // Metodi getter e setter per dataScadenza
    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Date dataScadenza) {
        this.dataScadenza = dataScadenza;
    }
}
