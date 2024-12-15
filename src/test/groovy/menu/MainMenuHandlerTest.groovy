package menu

import org.flowpay.ProgramStep
import org.flowpay.menu.MainMenuHandler
import spock.lang.Specification

class MainMenuHandlerTest extends Specification {
    private Scanner scanner
    private MainMenuHandler mainMenuHandler

    def "should render some step when selecting an option in main menu"() {
        given:
        scanner = new Scanner(input)
        mainMenuHandler = new MainMenuHandler(scanner)

        def originalOutput = System.out
        def output = new ByteArrayOutputStream()
        System.setOut(new PrintStream(output))

        expect:
        result == mainMenuHandler.handleMainMenu()

        cleanup:
        System.setOut(originalOutput)

        where:
        input                                 || result
        new ByteArrayInputStream("1\n".bytes) || ProgramStep.ADMIN_LOGIN
        new ByteArrayInputStream("2\n".bytes) || ProgramStep.CUSTOMER_MENU
        new ByteArrayInputStream("3\n".bytes) || ProgramStep.MAIN_MENU_EXIT
    }
}
