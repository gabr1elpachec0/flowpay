package org.flowpay.menu;

import org.flowpay.ProgramStep;
import org.flowpay.attendant.Attendant;
import org.flowpay.request.Request;
import org.flowpay.team.Team;

import java.util.*;

public class CustomerMenuHandler {
    private final Scanner scanner;

    public CustomerMenuHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public ProgramStep handleCustomerMenu() {
        int customerOption = getCustomerMenu(scanner);

        return switch (customerOption) {
            case 1 -> ProgramStep.CUSTOMER_REQUEST_MENU;
            case 2 -> ProgramStep.MAIN_MENU;
            default -> ProgramStep.CUSTOMER_MENU;
        };
    }

    public ProgramStep handleCustomerRequest(List<Team> teamsList) {
        Request request = registerRequest(scanner);

        switch (request.getType()) {
            case "Cartões" -> {
                distributeRequest(teamsList.get(0), request);
                System.out.println("Chamado adicionado!");
            }
            case "Empréstimos" -> {
                distributeRequest(teamsList.get(1), request);
                System.out.println("Chamado adicionado!");
            }
            case "Outros Assuntos" -> {
                distributeRequest(teamsList.get(2), request);
                System.out.println("Chamado adicionado!");
            }
        }

        return ProgramStep.CUSTOMER_MENU;
    }

    public Request registerRequest(Scanner scanner) {
        System.out.println("Preencha os dados a seguir para criar solicitação:");

        System.out.print("Nome: ");
        String customerName = scanner.nextLine();

        System.out.println("Selecione o tipo do problema: ");
        System.out.println("1. Cartões");
        System.out.println("2. Empréstimo");
        System.out.println("3. Outros Assuntos");

        List<Integer> validOptions = Arrays.asList(1, 2, 3);
        int problemType = 0;

        while (!validOptions.contains(problemType)) {
            try {
                System.out.print("Opção a selecionar (selecione um número): ");
                problemType = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                System.out.println("Digite um número de 1 a 3.");
                scanner.nextLine();
            }
        }

        System.out.print("Descreva o problema: ");
        String problemDescription = scanner.nextLine();

        Request request = new Request(problemDescription, customerName);

        if (problemType == 1) {
            request.setType("Cartões");
        } else if (problemType == 2) {
            request.setType("Empréstimos");
        } else if (problemType == 3) {
            request.setType("Outros Assuntos");
        }

        return request;
    }

    public void distributeRequest(Team team, Request request) {
        List<Attendant> teamAttendants = team.getAttendants();

        if (!teamAttendants.isEmpty()) {
            for (Attendant attendant : teamAttendants) {
                if (attendant.getRequests().size() < 3) {
                    addRequestOnAttendantRequestsList(attendant, request);
                } else {
                    System.out.println();
                    System.out.println("Direcionando solicitação para fila de solicitações do time ");
                    addRequestOnTeamRequestsQueue(team, request);
                }
            }
        } else {
            System.out.println();
            System.out.println("Direcionando solicitação para fila de solicitações do time ");
            addRequestOnTeamRequestsQueue(team, request);
        }
    }

    private int getCustomerMenu(Scanner scanner) {
        System.out.println("Selecione uma opção: ");
        System.out.println("1. Abrir chamado");
        System.out.println("2. Voltar para o menu principal");

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

    private void addRequestOnTeamRequestsQueue(Team team, Request request) {
        Queue<Request> requestQueue = team.getRequests();
        requestQueue.add(request);
        team.setRequests(requestQueue);
    }

    private void addRequestOnAttendantRequestsList(Attendant attendant, Request request) {
        List<Request> attendantRequests = attendant.getRequests();
        System.out.println();
        System.out.println("Direcionando solicitação para o atendente " + attendant.getName());
        attendantRequests.add(request);
        attendant.setRequests(attendantRequests);
    }
}
