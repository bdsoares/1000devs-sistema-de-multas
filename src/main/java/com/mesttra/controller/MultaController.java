package com.mesttra.controller;

import com.mesttra.exceptions.MultaException;
import com.mesttra.model.dao.MultaDAO;
import com.mesttra.model.entity.Condutor;
import com.mesttra.model.entity.Multa;
import com.mesttra.model.entity.Veiculo;

import java.util.List;
import java.util.Scanner;

public class MultaController {
    private final MultaDAO dao = new MultaDAO();

    public void salvar(Scanner in) {
        System.out.print("Valor da multa: R$");
        double valor = Double.parseDouble(in.nextLine());
        System.out.print("Pontuação da multa: ");
        int pontos = Integer.parseInt(in.nextLine());

        VeiculoController veiculoController = new VeiculoController();
        Veiculo veiculo = veiculoController.buscaVeiculo(in);
        Condutor condutor = veiculo.getCondutor();
        Multa multa = new Multa(valor, pontos, veiculo, condutor);

        dao.salvar(multa);
        CondutorController condutorController = new CondutorController();
        condutorController.contabilizaMultas(condutor, pontos);

        condutorController.close();
        veiculoController.close();
    }

    public boolean atualizar(Scanner in) {
        Multa multa = buscaMulta(in);
        System.out.print("Valor da multa: R$");
        multa.setValor(Double.parseDouble(in.nextLine()));
        System.out.print("Pontuação da multa: ");
        multa.setPontos(Integer.parseInt(in.nextLine()));

        dao.atualizar(multa);
        return true;
    }

    public boolean excluir(Scanner in) {
        Multa multa = buscaMulta(in);

        System.out.println(multa);
        System.out.print("Deseja realmente excluir essa multa? (S/N): ");

        if (in.nextLine().equalsIgnoreCase("S")) {
            System.out.print("Remover pontos do condutor? (S/N): ");
            if (in.nextLine().equalsIgnoreCase("S")) {
                CondutorController condutorController = new CondutorController();
                condutorController.contabilizaMultas(multa.getCondutor(), -multa.getPontos());

                condutorController.close();
            }

            dao.excluir(multa);
            return true;
        }

        return false;
    }

    public void busca(Scanner in) {
        System.out.println();
        List<Multa> multas = buscaMultaVeiculo(in);

        if(multas.isEmpty())
            throw new MultaException("Nenhuma multa encontrada!");

        System.out.println("# ===== Lista de Multas ===== #");

        for (Multa m : multas)
            System.out.println(m.toString());

        System.out.println("# =========================== #");
    }

    public void buscaTodos() {
        System.out.println();
        List<Multa> multas = dao.buscarTodos();

        if(multas.isEmpty())
            throw new MultaException("Nenhuma multa encontrada!");

        for (Multa m : multas) {
            System.out.println(m.toString());
            System.out.println();
        }
    }

    public void close() {
        dao.close();
    }

    private Multa buscaMulta(Scanner in) {
        System.out.print("Informe o ID da multa: ");
        Multa multa = dao.buscarPorId(Long.parseLong(in.nextLine()));

        if (multa == null)
            throw new MultaException("Não existe multa com o ID informado");

        return multa;
    }

    private List<Multa> buscaMultaVeiculo(Scanner in) {
        VeiculoController veiculoController = new VeiculoController();
        List<Multa> multas = dao.buscarPorPlaca(veiculoController.buscaVeiculo(in));

        veiculoController.close();

        if (multas == null)
            throw new MultaException("Não existem multas para esse veículo");

        return multas;
    }
}
