package com.mesttra.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

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

    @OneToOne(mappedBy = "condutor", cascade = CascadeType.ALL)
    private Veiculo veiculo;

    public String getNroCnh() {
        return nroCnh;
    }

    public void setNroCnh(String nroCnh) {
        this.nroCnh = nroCnh;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getOrgaoEmissor() {
        return orgaoEmissor;
    }

    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
