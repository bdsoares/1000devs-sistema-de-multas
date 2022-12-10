package com.mesttra.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "veiculos")
public class Veiculo {
    @Id
    private String placa;

    @Column(nullable = false)
    private int ano;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String marca;

    @ManyToOne
    @JoinColumn(name = "cnh_condutor", referencedColumnName = "nro_cnh")
    private Condutor condutor;

    @OneToMany(mappedBy = "veiculo")
    private List<Multa> multas;

    public Veiculo(String placa, int ano, String modelo, String marca, Condutor condutor) {
        this.placa = placa;
        this.ano = ano;
        this.modelo = modelo;
        this.marca = marca;
        this.condutor = condutor;
    }

    public Veiculo() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Placa: ");
        sb.append(this.placa);
        sb.append("\n\tAno: ");
        sb.append(this.ano);
        sb.append("\n\tModelo: ");
        sb.append(this.modelo);
        sb.append("\n\tMarca: ");
        sb.append(this.marca);

        if (this.condutor != null) {
            sb.append("\n\tCondutor: ");
            sb.append(this.condutor.getNroCnh());
        }

        if (this.multas != null) {
            sb.append("\n\tTotal de multas: ");
            sb.append(this.multas.size());
        }

        return sb.toString();
    }
}
