package org.flowpay.menu;

import org.flowpay.ProgramStep;
import org.flowpay.attendant.Attendant;
import org.flowpay.request.Request;
import org.flowpay.team.Team;

import java.util.*;

public class AdminMenuHandler {
    private final Scanner scanner;
    private static final String ADM_FLOW_PAY_PASSWORD = "admin";

    public AdminMenuHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public ProgramStep handleAdminLogin() {
        if (loginFlowPay(scanner)) {
            System.out.println();
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

    public ProgramStep handleAdminTeamOperationsMenu(Team team) {
        int selectedOption = getTeamMenu(scanner, team);

        return switch (selectedOption) {
            case 1 -> handleRegisterTeamAttendant(team);
            case 2 -> handleListTeamAttendants(scanner, team);
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
            System.out.println();
            System.out.println("Você possui " + times + " tentativa(s)");
            System.out.print("Digite a senha para acessar: ");
            password = scanner.nextLine();
            counter++;
        }

        return true;
    }

    private int getAdminMenu(Scanner scanner) {
        System.out.println();
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
        System.out.println();
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

    private int getTeamMenu(Scanner scanner, Team team) {
        System.out.println();
        System.out.println("Time " + team.getDepartment() + " => Selecione uma opção: ");
        System.out.println("1. Cadastrar novo atendente");
        System.out.println("2. Ver atendentes");
        System.out.println("3. Voltar para o menu");

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

    private ProgramStep handleRegisterTeamAttendant(Team team) {
        registerTeamAttendant(team);

        return ProgramStep.ADMIN_TEAM_MENU;
    }

    private void registerTeamAttendant(Team team) {
        System.out.println();
        System.out.println("Time " + team.getDepartment() + " => Registrar atendente:");

        System.out.print("Nome: ");
        String attendantName = scanner.nextLine();

        Attendant newAttendant = new Attendant(attendantName);

        List<Attendant> teamAttendants = team.getAttendants();
        teamAttendants.add(newAttendant);
        team.setAttendants(teamAttendants);

        System.out.println("Atendente adicionado ao time " + team.getDepartment());
    }

    private ProgramStep handleListTeamAttendants(Scanner scanner, Team team) {
        int selectedOption = getTeamAttendants(scanner, team);

        if (selectedOption == 1) {
            getTeamAttendantRequest(scanner, team);
        }

        return ProgramStep.ADMIN_TEAM_MENU;
    }

    private int getTeamAttendants(Scanner scanner, Team team) {
        List<Attendant> teamAttendants = listTeamAttendants(team);

        if (!teamAttendants.isEmpty()) {
            System.out.println();
            System.out.println("Lista de atendentes do time " + team.getDepartment());
            System.out.println(teamAttendants);
            System.out.println();

            System.out.println("Deseja ver as solicitações de algum atendente?");
            System.out.println("1. Sim");
            System.out.println("2. Não");

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

        System.out.println();
        System.out.println("Time " + team.getDepartment() + " está sem atendentes");
        return 0;
    }

    private List<Attendant> listTeamAttendants(Team team) {
        return team.getAttendants();
    }

    private void getTeamAttendantRequest(Scanner scanner, Team team) {
        System.out.println();
        System.out.print("Copie e cole o identificador do atendente: ");

        try {
            UUID attendantId = UUID.fromString(scanner.nextLine());

            Attendant attendant = null;

            for (int i = 0; i < team.getAttendants().size(); i++) {
                if (attendantId.equals(team.getAttendants().get(i).getId())) {
                    attendant = team.getAttendants().get(i);
                }
            }

            if (attendant != null) {
                List<Request> attendantRequests = listTeamAttendantRequests(attendant);
                if (attendantRequests != null) {
                    System.out.println();
                    System.out.println("Fila de solicitações do atendente " + attendant.getName());
                    System.out.println(attendantRequests);
                } else {
                    System.out.println();
                    System.out.println("Sem solicitações para o atendente " + attendant.getName());
                }
            } else {
                System.out.println();
                System.out.println("Atendente não encontrado!");
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Identificador inválido. Retornando ao menu de times...");
        }
    }

    private List<Request> listTeamAttendantRequests(Attendant attendant) {
        return attendant.getRequests();
    }
}
