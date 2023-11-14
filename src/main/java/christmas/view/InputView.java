package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final InputView INSTANCE = new InputView();
    private final OutputView outputView = OutputView.getInstance();

    private InputView() {

    }

    public static InputView getInstance() {
        return INSTANCE;
    }

    public String readVisitDay() {
        outputView.printVisitDayPrompt();
        return readLine();
    }

    public String readOrderMenu() {
        outputView.printOrderMenuPrompt();
        return readLine();
    }

    private String readLine() {
        return Console.readLine();
    }
}
