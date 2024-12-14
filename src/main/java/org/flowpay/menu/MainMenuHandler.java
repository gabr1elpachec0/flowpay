package org.flowpay.menu;

import org.flowpay.ProgramStep;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainMenuHandler {
    private final Scanner scanner;

    public MainMenuHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public ProgramStep handleMainMenu() {
        int selectedOption = getMainMenu(scanner);

        return switch (selectedOption) {
            case 1 -> ProgramStep.ADMIN_LOGIN;
            case 2 -> ProgramStep.CUSTOMER_MENU;
            case 3 -> ProgramStep.MAIN_MENU_EXIT;
            default -> ProgramStep.MAIN_MENU;
        };
    }

    private int getMainMenu(Scanner scanner) {
        System.out.println();
        System.out.println("-------------------");
        System.out.println("BEM-VINDO À FLOWPAY");
        System.out.println("-------------------");

        System.out.println("Para prosseguir, escolha uma opção:");
        System.out.println("1. Abrir FlowPay => Opção para funcionários FlowPay");
        System.out.println("2. Abrir chamado => Opção para clientes FlowPay");
        System.out.println("3. Sair");

        List<Integer> validOptions = Arrays.asList(1, 2, 3);
        int option = 0;

        while (!validOptions.contains(option)) {
            try {
                System.out.print("Opção a selecionar (selecione um número): ");
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                System.out.println("Digite um número de 1 a 3.");
                scanner.nextLine();
            }
        }
        return option;
    }
}
