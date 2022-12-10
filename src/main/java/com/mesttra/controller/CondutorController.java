package com.mesttra.controller;

import com.mesttra.exceptions.CondutorException;
import com.mesttra.model.dao.CondutorDAO;
import com.mesttra.model.entity.Condutor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CondutorController {
    private final CondutorDAO dao = new CondutorDAO();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void salvar(Scanner in) {
        System.out.print("Número da CNH: ");
        String nroCnh = in.nextLine();

        if (dao.buscar(nroCnh) != null)
            throw new CondutorException("Já existe um condutor com esse número de CNH");

        System.out.print("Data de Emissão (dd/mm/aaaa): ");
        LocalDate dataEmissao = LocalDate.parse(in.nextLine(), formatter);
        System.out.print("Orgão Emissor: ");
        String orgaoEmissor = in.nextLine();

        dao.salvar(new Condutor(nroCnh, dataEmissao, orgaoEmissor, 0));
    }

    public boolean atualizar(Scanner in) {
        Condutor condutor = buscaCondutor(in);
        System.out.print("Data de Emissão (dd/mm/aaaa): ");
        condutor.setDataEmissao(LocalDate.parse(in.nextLine(), formatter));
        System.out.print("Orgão Emissor: ");
        condutor.setOrgaoEmissor(in.nextLine());

        dao.atualizar(condutor);
        return true;
    }

    public boolean excluir(Scanner in) {
        Condutor condutor = buscaCondutor(in);

        System.out.printf("Deseja realmente remover o condutor %s (S/N)? ", condutor.getNroCnh());
        if (in.nextLine().equalsIgnoreCase("S")) {
            dao.excluir(condutor);
            return true;
        }

        return false;
    }

    public void buscar(Scanner in) {
        Condutor condutor = buscaCondutor(in);
        System.out.println();
        System.out.println(condutor.toString());
    }

    public void buscarTodos() {
        System.out.println();

        for (Condutor c : dao.buscarTodos()) {
            System.out.println(c.toString());
            System.out.println();
        }
    }

    public Condutor buscaCondutor(Scanner in) {
        System.out.print("Número da CNH: ");
        String nroCnh = in.nextLine();

        Condutor condutor = dao.buscar(nroCnh);
        if(condutor == null)
            throw new CondutorException("Condutor não encontrado");

        return condutor;
    }

    public void contabilizaMultas(Condutor condutor, int pontuacao) {
        condutor.setPontuacao(condutor.getPontuacao() + pontuacao);
        dao.atualizar(condutor);
    }

    public void close() {
        dao.close();
    }
}
