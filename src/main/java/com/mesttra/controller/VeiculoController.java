package com.mesttra.controller;

import com.mesttra.exceptions.CondutorException;
import com.mesttra.exceptions.VeiculoException;
import com.mesttra.model.dao.VeiculoDAO;
import com.mesttra.model.entity.Veiculo;

import java.util.Scanner;

public class VeiculoController {
    private final VeiculoDAO dao = new VeiculoDAO();

    public void salvar(Scanner in) {
        CondutorController condutorController = new CondutorController();

        System.out.print("Placa do veículo: ");
        String placa = in.nextLine();

        if (dao.buscar(placa) != null)
            throw new VeiculoException("Já existe um veículo com essa placa");

        System.out.print("Ano do veículo: ");
        int ano = Integer.parseInt(in.nextLine());
        System.out.print("Modelo do veículo: ");
        String modelo = in.nextLine();
        System.out.print("Marca do veículo: ");
        String marca = in.nextLine();
        System.out.print("[Condutor] ");

        dao.salvar(new Veiculo(placa, ano, modelo, marca, condutorController.buscaCondutor(in)));
    }

    public boolean atualizar(Scanner in) {
        Veiculo veiculo = buscaVeiculo(in);
        System.out.print("Ano do veículo: ");
        veiculo.setAno(Integer.parseInt(in.nextLine()));
        System.out.print("Modelo do veículo: ");
        veiculo.setModelo(in.nextLine());
        System.out.print("Marca do veículo: ");
        veiculo.setMarca(in.nextLine());

        dao.atualizar(veiculo);
        return true;
    }

    public boolean excluir(Scanner in) {
        Veiculo veiculo = buscaVeiculo(in);
        dao.excluir(veiculo);
        return true;
    }

    public void buscar(Scanner in) {
        Veiculo veiculo = buscaVeiculo(in);
        System.out.println();
        System.out.println(veiculo.toString());
    }

    public void buscarTodos() {
        System.out.println();

        for (Veiculo v : dao.buscarTodos()) {
            System.out.println(v.toString());
            System.out.println();
        }
    }

    public boolean transfereVeiculo(Scanner in) {
        Veiculo veiculo = buscaVeiculo(in);
        System.out.print("[Novo Condutor] ");
        veiculo.setCondutor(new CondutorController().buscaCondutor(in));
        dao.atualizar(veiculo);
        return true;
    }

    public Veiculo buscaVeiculo(Scanner in) {
        System.out.print("Placa do veículo: ");
        String placa = in.nextLine();
        Veiculo veiculo = dao.buscar(placa);

        if (veiculo == null)
            throw new VeiculoException("Não existe um veículo com essa placa");

        return veiculo;
    }

    public void close() {
        dao.close();
    }
}
