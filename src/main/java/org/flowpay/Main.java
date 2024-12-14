package org.flowpay;

import org.flowpay.menu.AdminMenuHandler;
import org.flowpay.menu.CustomerMenuHandler;
import org.flowpay.menu.MainMenuHandler;
import org.flowpay.team.Team;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CustomerMenuHandler customerMenuHandler = new CustomerMenuHandler(scanner);
        MainMenuHandler mainMenuHandler = new MainMenuHandler(scanner);
        AdminMenuHandler adminMenuHandler = new AdminMenuHandler(scanner);

        Team cards = new Team("Cartões");
        Team loans = new Team("Empréstimos");
        Team othersSubjects = new Team("Outros Assuntos");
        List<Team> teamsList = Arrays.asList(cards, loans, othersSubjects);


        ProgramStep currentStep = ProgramStep.MAIN_MENU;

        while (true) {
            switch (currentStep) {
                case MAIN_MENU -> {
                    System.out.println("Abrindo menu principal...");
                    currentStep = mainMenuHandler.handleMainMenu();
                }
                case ADMIN_LOGIN -> {
                    System.out.println("Abrindo formulario para login...");
                    currentStep = adminMenuHandler.handleAdminLogin();
                }
                case ADMIN_MENU -> {
                    System.out.println("Abrindo menu do admin...");
                    currentStep = adminMenuHandler.handleAdminMenu();
                }
                case ADMIN_TEAM_MENU -> {
                    System.out.println("Abrindo menu de times...");
                    currentStep = adminMenuHandler.handleAdminTeamMenu();
                }
                case CUSTOMER_MENU -> {
                    System.out.println("Abrindo menu do cliente...");
                    currentStep = customerMenuHandler.handleCustomerMenu();
                }
                case CUSTOMER_REQUEST_MENU -> {
                    System.out.println("Abrindo formulário para novo chamado...");
                    currentStep = customerMenuHandler.handleCustomerRequest(teamsList);
                }
                default -> {
                    System.out.println("Encerrando o programa...");
                    System.exit(0);
                }
            }
        }
    }
}