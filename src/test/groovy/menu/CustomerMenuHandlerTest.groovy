package menu

import org.flowpay.ProgramStep

import org.flowpay.menu.CustomerMenuHandler
import spock.lang.Specification

class CustomerMenuHandlerTest extends Specification {
    private Scanner scanner
    private CustomerMenuHandler customerMenuHandler

    def "should render some step when selecting an option in customer menu"() {
        given:
        scanner = new Scanner(input)
        customerMenuHandler = new CustomerMenuHandler(scanner)

        def originalOutput = System.out
        def output = new ByteArrayOutputStream()
        System.setOut(new PrintStream(output))

        expect:
        result == customerMenuHandler.handleCustomerMenu()

        cleanup:
        System.setOut(originalOutput)

        where:
        input                                 || result
        new ByteArrayInputStream("1\n".bytes) || ProgramStep.CUSTOMER_REQUEST_MENU
        new ByteArrayInputStream("2\n".bytes) || ProgramStep.MAIN_MENU
    }
}
