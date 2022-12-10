package com.mesttra.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity(name = "condutores")
public class Condutor {
    @Id
    @Column(name = "nro_cnh")
    private String nroCnh;

    @Column(nullable = false, name = "data_emissao")
    private LocalDate dataEmissao;

    @Column(nullable = false, name = "orgao_emissor")
    private String orgaoEmissor;

    @Column(nullable = false)
    private int pontuacao;

    // Atributo modificado para que seja possível um condutor possuir mais de um veículo.
    @OneToMany(mappedBy = "condutor")
    private List<Veiculo> veiculos;

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

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
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

        if (this.veiculos != null && !this.veiculos.isEmpty()) {
            sb.append("\n\tVeículos: ");
            for (Veiculo veiculo : this.veiculos) {
                sb.append("\n\t\t");
                sb.append(veiculo.getPlaca());
            }
        }

        return sb.toString();
    }
}
