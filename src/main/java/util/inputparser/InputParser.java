package util.inputparser;

import static christmas.contant.Error.INVALID_DATE_ERROR;
import static christmas.contant.ViewMessage.COMMA;
import static christmas.contant.ViewMessage.DASH;

import christmas.domain.menu.Menu;
import java.util.Arrays;
import java.util.HashMap;
import util.inputvalidator.InputValidator;
import util.inputvalidator.OrderMenuInputValidator;

public class InputParser {
    InputValidator inputValidator = new OrderMenuInputValidator();

    public int parseInt(String input) {
        input = removeSpaces(input);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            INVALID_DATE_ERROR.throwError();
        }
        return -1;
    }

    public HashMap<Menu, Integer> parseOrderMenu(String input) {
        input = removeSpaces(input);
        inputValidator.validate(input);
        return makeOrderMenuSource(input);
    }

    private String removeSpaces(String input) {
        return input.replaceAll(" ", "");
    }

    private HashMap<Menu, Integer> makeOrderMenuSource(String input) {
        HashMap<Menu, Integer> orderMenuSource = new HashMap<>();
        Arrays.asList(input.split(COMMA.get())).forEach(order -> {
            String[] parts = order.split(DASH.get());
            orderMenuSource.put(new Menu(parts[0]), parseInt(parts[1]));
        });
        return orderMenuSource;
    }

}
