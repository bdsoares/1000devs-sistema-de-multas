package com.mesttra.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Condutor {
    @Id
    @Column(name = "nro_cnh")
    private String nroCnh;

    @Column(nullable = false)
    private LocalDate dataEmissao;

    @Column(nullable = false, name = "orgao_emissor")
    private String orgaoEmissor;

    @Column(nullable = false)
    private int pontuacao;

    @OneToOne(mappedBy = "condutor")
    private Veiculo veiculo;

    public Condutor(String nroCnh, LocalDate dataEmissao, String orgaoEmissor, int pontuacao) {
        this.nroCnh = nroCnh;
        this.dataEmissao = dataEmissao;
        this.orgaoEmissor = orgaoEmissor;
        this.pontuacao = pontuacao;
    }

    public Condutor() { }

    public String getNroCnh() {
        return nroCnh;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();

        sb.append("Número da CNH: ");
        sb.append(this.nroCnh);
        sb.append("\n\tData de Emissão: ");
        sb.append(this.dataEmissao.format(formatter));
        sb.append("\n\tOrgão Emissor: ");
        sb.append(this.orgaoEmissor);
        sb.append("\n\tPontuação: ");
        sb.append(this.pontuacao);

        if (this.veiculo != null) {
            sb.append("\n\tVeículo: ");
            sb.append(this.veiculo.getPlaca());
        }

        return sb.toString();
    }
}
