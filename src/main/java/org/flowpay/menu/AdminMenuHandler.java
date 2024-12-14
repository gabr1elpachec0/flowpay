package org.flowpay.menu;

import org.flowpay.ProgramStep;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminMenuHandler {
    private final Scanner scanner;
    private static final String ADM_FLOW_PAY_PASSWORD = "admin";

    public AdminMenuHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public ProgramStep handleAdminLogin() {
        if (loginFlowPay(scanner)) {
            System.out.println("Admin logado com sucesso...");
        }
        return ProgramStep.ADMIN_MENU;
    }

    public ProgramStep handleAdminMenu() {
        int selectedOption = getAdminMenu(scanner);

        return switch (selectedOption) {
            case 1 -> ProgramStep.ADMIN_TEAM_MENU;
            case 2 -> ProgramStep.MAIN_MENU;
            default -> ProgramStep.ADMIN_MENU;
        };
    }

    public ProgramStep handleAdminTeamMenu() {
        int selectedOption = getAdminTeamMenu(scanner);

        return switch (selectedOption) {
            case 1 -> ProgramStep.CARDS_MENU;
            case 2 -> ProgramStep.LOANS_MENU;
            case 3 -> ProgramStep.OTHER_SUBJECTS_MENU;
            case 4 -> ProgramStep.ADMIN_MENU;
            default -> ProgramStep.ADMIN_TEAM_MENU;
        };
    }

    private boolean loginFlowPay(Scanner scanner) {
        String password = "";
        int counter = 0;

        while (!password.equalsIgnoreCase(ADM_FLOW_PAY_PASSWORD)) {
            if (counter == 5) {
                System.out.println("Número máximo de tentativas atingido.");
                System.exit(0);
            }
            int times = 5 - counter;
            System.out.println("Você possui " + times + " tentativa(s)");
            System.out.print("Digite a senha para acessar: ");
            password = scanner.nextLine();
            counter++;
        }

        return true;
    }

    private int getAdminMenu(Scanner scanner) {
        System.out.println("Selecione uma opção: ");
        System.out.println("1. Times");
        System.out.println("2. Voltar para o menu principal");

        List<Integer> validOptions = Arrays.asList(1, 2);
        int option = 0;

        while (!validOptions.contains(option)) {
            try {
                System.out.print("Opção a selecionar (selecione um número): ");
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                System.out.println("Digite um número de 1 a 2.");
                scanner.nextLine();
            }
        }

        return option;
    }

    private int getAdminTeamMenu(Scanner scanner) {
        System.out.println("Selecione uma opção: ");
        System.out.println("1. Cartões");
        System.out.println("2. Empréstimos");
        System.out.println("3. Outros Assuntos");
        System.out.println("4. Voltar para o menu");

        List<Integer> validOptions = Arrays.asList(1, 2, 3, 4);
        int option = 0;

        while (!validOptions.contains(option)) {
            try {
                System.out.print("Opção a selecionar (selecione um número): ");
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                System.out.println("Digite um número de 1 a 4.");
                scanner.nextLine();
            }
        }

        return option;
    }
}
