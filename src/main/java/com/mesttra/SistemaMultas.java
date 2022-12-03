package com.mesttra;

import com.mesttra.controller.CondutorController;

import java.util.Scanner;

public class SistemaMultas {
    public static void main(String[] args) {
        int opcao = -1;
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
                    case 0 -> System.out.println();
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void veiculo(Scanner in) {
        int opcao = -1;

        do {
            opcao = menuVeiculo(in);
        } while (opcao == 0);
    }

    private static void multa(Scanner in) {
        int opcao = -1;

        do {
            opcao = menuMulta(in);
        } while (opcao == 0);
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
        System.out.println("2 - Consultar Veículo");
        System.out.println("3 - Alterar Veículo");
        System.out.println("4 - Excluir Veículo");
        System.out.println("5 - Transferir Veículo");
        System.out.println("0 - Voltar");
        System.out.println("# ========================== #");
        System.out.print("Digite a opção desejada: ");
        return Integer.parseInt(in.nextLine());
    }

    private static int menuMulta(Scanner in) {
        System.out.println("# ===== Opções Multa ===== #");
        System.out.println("1 - Cadastrar Multa");
        System.out.println("2 - Consultar Multa");
        System.out.println("3 - Alterar Multa");
        System.out.println("4 - Excluir Multa");
        System.out.println("0 - Voltar");
        System.out.println("# ======================== #");
        System.out.print("Digite a opção desejada: ");
        return Integer.parseInt(in.nextLine());
    }
}
