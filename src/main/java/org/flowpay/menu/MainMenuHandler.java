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
        System.out.println("BEM-VINDO A FLOWPAY");
        System.out.println("-------------------");

        System.out.println("Para prosseguir, escolha uma opcao:");
        System.out.println("1. Abrir FlowPay => Opcao para funcionarios FlowPay");
        System.out.println("2. Abrir chamado => Opcao para clientes FlowPay");
        System.out.println("3. Sair");

        List<Integer> validOptions = Arrays.asList(1, 2, 3);
        int option = 0;

        while (!validOptions.contains(option)) {
            try {
                System.out.print("Opcao a selecionar (selecione um numero): ");
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                System.out.println("Digite um numero de 1 a 3.");
                scanner.nextLine();
            }
        }
        return option;
    }
}
