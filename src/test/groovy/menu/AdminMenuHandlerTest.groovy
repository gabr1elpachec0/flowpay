package menu

import org.flowpay.ProgramStep
import org.flowpay.menu.AdminMenuHandler
import spock.lang.Specification

class AdminMenuHandlerTest extends Specification {
    private Scanner scanner
    private AdminMenuHandler adminMenuHandler

    def "should render some step when selecting an option in admin menu"() {
        given:
        scanner = new Scanner(input)
        adminMenuHandler = new AdminMenuHandler(scanner)

        def originalOutput = System.out
        def output = new ByteArrayOutputStream()
        System.setOut(new PrintStream(output))

        expect:
        result == adminMenuHandler.handleAdminMenu()

        cleanup:
        System.setOut(originalOutput)

        where:
        input                                 || result
        new ByteArrayInputStream("1\n".bytes) || ProgramStep.ADMIN_TEAM_MENU
        new ByteArrayInputStream("2\n".bytes) || ProgramStep.MAIN_MENU
    }

    def "should render some step when selecting an option in admin team menu"() {
        given:
        scanner = new Scanner(input)
        adminMenuHandler = new AdminMenuHandler(scanner)

        def originalOutput = System.out
        def output = new ByteArrayOutputStream()
        System.setOut(new PrintStream(output))

        expect:
        result == adminMenuHandler.handleAdminTeamMenu()

        cleanup:
        System.setOut(originalOutput)

        where:
        input                                 || result
        new ByteArrayInputStream("1\n".bytes) || ProgramStep.CARDS_MENU
        new ByteArrayInputStream("2\n".bytes) || ProgramStep.LOANS_MENU
        new ByteArrayInputStream("3\n".bytes) || ProgramStep.OTHER_SUBJECTS_MENU
        new ByteArrayInputStream("4\n".bytes) || ProgramStep.ADMIN_MENU
    }
}
