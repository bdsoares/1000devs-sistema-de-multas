package com.mesttra.model.entity;

import jakarta.persistence.*;

@Entity(name = "multas")
public class Multa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_multa")
    private Long codigoMulta;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private int pontos;

    @ManyToOne
    @JoinColumn(name = "placa", referencedColumnName = "placa")
    private Veiculo veiculo;

    // Atributo adicionado para que seja possível uma multa possuir um condutor.
    @ManyToOne
    @JoinColumn(name = "nro_cnh", referencedColumnName = "nro_cnh")
    private Condutor condutor;

    public Multa(double valor, int pontos, Veiculo buscaVeiculo, Condutor condutor) {
        this.valor = valor;
        this.pontos = pontos;
        this.veiculo = buscaVeiculo;
        this.condutor = condutor;
    }

    public Multa() { }

    public void setValor(double valor) {
        if (valor < 0)
            throw new IllegalArgumentException("Valor da multa não pode ser negativo.");

        this.valor = valor;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontuacao) {
        if (pontuacao < 0)
            throw new IllegalArgumentException("A pontuação não pode ser negativa.");

        if (this.pontos - pontuacao < 0)
            throw new IllegalArgumentException("O Condutor não tem pontos suficientes a serem removidos.");

        this.pontos = pontuacao;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    @Override
    public String toString() {
        return "ID: " +
                this.codigoMulta +
                "\n\tPlaca: " +
                this.veiculo.getPlaca() +
                "\n\tValor: R$" +
                this.valor +
                "\n\tPontos: " +
                this.pontos;
    }
}
