package christmas.domain.customer;

import static christmas.contant.Error.NOT_VALIDATE_ORDER_ERROR;
import static christmas.contant.Error.ORDER_ONLY_DRINK_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.domain.menu.Menu;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OrderMenuTest {

    private OrderMenu orderMenu;

    void setOrderMenu() {
        HashMap<Menu, Integer> source = new HashMap<>();
        source.put(new Menu("크리스마스파스타"), 2);
        source.put(new Menu("레드와인"), 1);
        source.put(new Menu("타파스"), 2);
        source.put(new Menu("초코케이크"), 2);
        orderMenu = new OrderMenu(source);
    }

    @ParameterizedTest
    @CsvSource({"카레라이스 ,1", "파스타,1", "바베큐립,1"})
    @DisplayName("메뉴판에 없는 메뉴를 주문한 경우")
    void createByNonExistMenuInMenuBoard(String name, int count) {
        HashMap<Menu, Integer> orderMenu = new HashMap<>();
        orderMenu.put(new Menu(name), count);
        assertThatIllegalArgumentException().isThrownBy(() -> new OrderMenu(orderMenu))
                .withMessage(NOT_VALIDATE_ORDER_ERROR.get());
    }

    @ParameterizedTest
    @CsvSource({"크리스마스파스타 ,0", "바비큐립,-2"})
    @DisplayName("메뉴 개수가 1개 이상이 아닐 경우 예외를 발생한다.")
    void createByCountLessThan1(String name, int count) {
        HashMap<Menu, Integer> orderMenu = new HashMap<>();
        orderMenu.put(new Menu(name), count);
        assertThatIllegalArgumentException().isThrownBy(() -> new OrderMenu(orderMenu))
                .withMessage(NOT_VALIDATE_ORDER_ERROR.get());
    }

    @Test
    @DisplayName("메뉴 개수가 1개 이상이 아닐 경우 예외를 발생한다.")
    void createByTotalCountGreater20() {
        HashMap<Menu, Integer> orderMenu = new HashMap<>();

        orderMenu.put(new Menu("제로콜라"), 5);
        orderMenu.put(new Menu("크리스마스파스터"), 16);

        assertThatIllegalArgumentException().isThrownBy(() -> new OrderMenu(orderMenu))
                .withMessage(NOT_VALIDATE_ORDER_ERROR.get());
    }

    @Test
    @DisplayName("음료만 주문했을 경우 예외를 발생한다.")
    void createByOnlyDrink() {
        HashMap<Menu, Integer> orderMenu = new HashMap<>();

        orderMenu.put(new Menu("제로콜라"), 5);
        orderMenu.put(new Menu("레드와인"), 10);

        assertThatIllegalArgumentException().isThrownBy(() -> new OrderMenu(orderMenu))
                .withMessage(ORDER_ONLY_DRINK_ERROR.get());
    }

    @Test
    @DisplayName("메뉴의 총 금액을 리턴하는 기능")
    void testGetTotalAmount() {
        setOrderMenu();

        int result = orderMenu.getTotalAmount();
        int expected = 151_000;

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("디저트 메뉴의 갯수를 리턴하는 기능")
    void testGetQuantityOfDessertMenu() {
        setOrderMenu();

        int result = orderMenu.getQuantityOfDessertMenu();
        int expected = 2;

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("메인 메뉴의 갯수를 리턴하는 기능")
    void testGetQuantityOfMainMenu() {
        setOrderMenu();

        int result = orderMenu.getQuantityOfMainMenu();
        int expected = 2;

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("음료 메뉴의 갯수를 리턴하는 기능")
    void testGetQuantityOfDrinkMenu() {
        setOrderMenu();

        int result = orderMenu.getQuantityOfDrinkMenu();
        int expected = 1;

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("에피타이저 메뉴의 갯수를 리턴하는 기능")
    void testGetQuantityOfAppetizerMenu() {
        setOrderMenu();

        int result = orderMenu.getQuantityOfAppetizerMenu();
        int expected = 2;

        assertThat(result).isEqualTo(expected);
    }
}
