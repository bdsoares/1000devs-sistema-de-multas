package com.mesttra;

import com.mesttra.controller.CondutorController;
import com.mesttra.controller.MultaController;
import com.mesttra.controller.VeiculoController;

import java.util.Scanner;

public class SistemaMultas {
    public static void main(String[] args) {
        int opcao;
        Scanner in = new Scanner(System.in);

        do {
            opcao = menu(in);

            switch (opcao) {
                case 1 -> condutor(in);
                case 2 -> veiculo(in);
                case 3 -> multa(in);
                case 0 -> System.out.println("Obrigado por utilizar o sistema de multas!");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void condutor(Scanner in) {
        CondutorController condutorController = new CondutorController();
        int opcao = -1;

        do {
            try {
                opcao = menuCondutor(in);
                switch (opcao) {
                    case 1 -> {
                        System.out.println("** Cadastrar condutor **");
                        condutorController.salvar(in);
                        System.out.println("Condutor cadastrado com sucesso!");
                    }
                    case 2 -> {
                        System.out.println("** Atualizar condutor **");

                        if (condutorController.atualizar(in))
                            System.out.println("Condutor atualizado com sucesso!");
                    }
                    case 3 -> {
                        System.out.println("** Excluir condutor **");

                        if (condutorController.excluir(in))
                            System.out.println("Condutor excluído com sucesso!");
                    }
                    case 4 -> {
                        System.out.println("** Buscar condutor por CNH **");
                        condutorController.buscar(in);
                    }
                    case 5 -> {
                        System.out.println("# ===== Lista de Condutores ===== #");
                        condutorController.buscarTodos();
                        System.out.println("# =============================== #");
                    }
                    case 0 -> {
                        condutorController.close();
                        condutorController = null;
                        System.out.println();
                    }
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void veiculo(Scanner in) {
        VeiculoController veiculoController = new VeiculoController();
        int opcao = -1;

        do {
            try {
                opcao = menuVeiculo(in);
                switch (opcao) {
                    case 1 -> {
                        System.out.println("** Cadastrar veículo **");
                        veiculoController.salvar(in);
                        System.out.println("Veículo cadastrado com sucesso!");
                    }
                    case 2 -> {
                        System.out.println("** Atualizar veículo **");

                        if (veiculoController.atualizar(in))
                            System.out.println("Veículo atualizado com sucesso!");
                    }
                    case 3 -> {
                        System.out.println("** Excluir veículo **");

                        if (veiculoController.excluir(in))
                            System.out.println("Veículo excluído com sucesso!");
                    }
                    case 4 -> {
                        System.out.println("** Buscar veículo por placa **");
                        veiculoController.buscar(in);
                    }
                    case 5 -> {
                        System.out.println("# ===== Lista de Veículos ===== #");
                        veiculoController.buscarTodos();
                        System.out.println("# ============================= #");
                    }
                    case 6 -> {
                        System.out.println("** Transferência de Veiculo **");
                        if (veiculoController.transfereVeiculo(in))
                            System.out.println("Veículo transferido com sucesso!");
                    }
                    case 0 -> {
                        veiculoController.close();
                        veiculoController = null;
                        System.out.println();
                    }
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void multa(Scanner in) {
        MultaController multaController = new MultaController();
        int opcao = -1;

        do {
            try {
                opcao = menuMulta(in);
                switch (opcao) {
                    case 1 -> {
                        System.out.println("** Cadastrar multa **");
                        multaController.salvar(in);
                        System.out.println("Multa cadastrada com sucesso!");
                    }
                    case 2 -> {
                        System.out.println("** Atualizar multa **");

                        if (multaController.atualizar(in))
                            System.out.println("Multa atualizada com sucesso!");
                    }
                    case 3 -> {
                        System.out.println("** Excluir multa **");

                        if (multaController.excluir(in))
                            System.out.println("Multa excluída com sucesso!");
                    }
                    case 4 -> {
                        System.out.println("** Buscar multas por veiculo **");
                        multaController.busca(in);
                    }
                    case 5 -> {
                        System.out.println("# ===== Lista de Multas ===== #");
                        multaController.buscaTodos();
                        System.out.println("# =========================== #");
                    }
                    case 0 -> {
                        multaController.close();
                        multaController = null;
                        System.out.println();
                    }
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static int menu(Scanner in) {
        System.out.println("# ===== Sistema de Multas ===== #");
        System.out.println("1 - Opções Condutor");
        System.out.println("2 - Opções Veículo");
        System.out.println("3 - Opções Multa");
        System.out.println("0 - Sair");
        System.out.println("# ============================= #");
        System.out.print("Digite a opção desejada: ");
        return Integer.parseInt(in.nextLine());
    }

    private static int menuCondutor(Scanner in) {
        System.out.println("# ===== Opções Condutor ===== #");
        System.out.println("1 - Cadastrar Condutor");
        System.out.println("2 - Atualizar Condutor");
        System.out.println("3 - Excluir Condutor");
        System.out.println("4 - Consultar Condutor");
        System.out.println("5 - Lista de Condutores");
        System.out.println("0 - Voltar");
        System.out.println("# =========================== #");
        System.out.print("Digite a opção desejada: ");
        return Integer.parseInt(in.nextLine());
    }

    private static int menuVeiculo(Scanner in) {
        System.out.println("# ===== Opções Veículo ===== #");
        System.out.println("1 - Cadastrar Veículo");
        System.out.println("2 - Atualizar Veículo");
        System.out.println("3 - Excluir Veículo");
        System.out.println("4 - Consultar Veículo");
        System.out.println("5 - Lista de Veículos");
        System.out.println("6 - Transferir Veículo");
        System.out.println("0 - Voltar");
        System.out.println("# ========================== #");
        System.out.print("Digite a opção desejada: ");
        return Integer.parseInt(in.nextLine());
    }

    private static int menuMulta(Scanner in) {
        System.out.println("# ===== Opções Multa ===== #");
        System.out.println("1 - Cadastrar Multa");
        System.out.println("2 - Atualizar Multa");
        System.out.println("3 - Remover Multa");
        System.out.println("4 - Consultar Veículo");
        System.out.println("5 - Lista de Multas");
        System.out.println("0 - Voltar");
        System.out.println("# ======================== #");
        System.out.print("Digite a opção desejada: ");
        return Integer.parseInt(in.nextLine());
    }
}
