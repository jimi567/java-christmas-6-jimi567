package christmas.domain.customer;

import static christmas.contant.Error.NOT_VALIDATE_ORDER_ERROR;
import static christmas.contant.Error.ORDER_ONLY_DRINK_ERROR;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.domain.menu.DrinkMenu;
import christmas.domain.menu.MainMenu;
import christmas.domain.menu.Menu;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OrderMenuTest {
    @ParameterizedTest
    @CsvSource({"카레라이스 ,1", "파스타,1", "바베큐립,1"})
    @DisplayName("메뉴판에 없는 메뉴를 주문한 경우")
    void createByNonExistMenuInMenuBoard(String name, int count) {
        HashMap<Menu, Integer> orderMenu = new HashMap<>();
        orderMenu.put(new MainMenu(name), count);
        assertThatIllegalArgumentException().isThrownBy(() -> new OrderMenu(orderMenu))
                .withMessage(NOT_VALIDATE_ORDER_ERROR.get());
    }

    @ParameterizedTest
    @CsvSource({"크리스마스파스타 ,0", "바비큐립,-2"})
    @DisplayName("메뉴 개수가 1개 이상이 아닐 경우 예외를 발생한다.")
    void createByCountLessThan1(String name, int count) {
        HashMap<Menu, Integer> orderMenu = new HashMap<>();
        orderMenu.put(new MainMenu(name), count);
        assertThatIllegalArgumentException().isThrownBy(() -> new OrderMenu(orderMenu))
                .withMessage(NOT_VALIDATE_ORDER_ERROR.get());
    }

    @Test
    @DisplayName("메뉴 개수가 1개 이상이 아닐 경우 예외를 발생한다.")
    void createByTotalCountGreater20() {
        HashMap<Menu, Integer> orderMenu = new HashMap<>();

        orderMenu.put(new DrinkMenu("제로콜라"), 5);
        orderMenu.put(new MainMenu("크리스마스파스터"), 16);

        assertThatIllegalArgumentException().isThrownBy(() -> new OrderMenu(orderMenu))
                .withMessage(NOT_VALIDATE_ORDER_ERROR.get());
    }

    @Test
    @DisplayName("음료만 주문했을 경우 예외를 발생한다.")
    void createByOnlyDrink() {
        HashMap<Menu, Integer> orderMenu = new HashMap<>();

        orderMenu.put(new DrinkMenu("제로콜라"), 5);
        orderMenu.put(new DrinkMenu("레드와인"), 10);

        assertThatIllegalArgumentException().isThrownBy(() -> new OrderMenu(orderMenu))
                .withMessage(ORDER_ONLY_DRINK_ERROR.get());
    }
}
