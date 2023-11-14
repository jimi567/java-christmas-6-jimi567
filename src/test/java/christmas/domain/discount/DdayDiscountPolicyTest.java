package christmas.domain.discount;

import static christmas.contant.Event.D_DAY_DISCOUNT_PLUS_AMOUNT;
import static christmas.contant.Event.D_DAY_DISCOUNT_START_AMOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.customer.Customer;
import christmas.domain.customer.DecemberVisitDate;
import christmas.domain.customer.OrderMenu;
import christmas.domain.menu.Menu;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DdayDiscountPolicyTest {
    DiscountPolicy discountPolicy = new DdayDiscountPolicy();
    OrderMenu orderMenu;

    @BeforeEach
    void setOrderMenu() {
        HashMap<Menu, Integer> orderMenuSource = new HashMap<>();
        orderMenuSource.put(new Menu("티본스테이크"), 1);
        orderMenuSource.put(new Menu("바비큐립"), 1);
        orderMenuSource.put(new Menu("초코케이크"), 2);
        orderMenuSource.put(new Menu("제로콜라"), 1);
        orderMenu = new OrderMenu(orderMenuSource);
    }

    @ParameterizedTest
    @ValueSource(ints = {26, 27, 30, 31})
    @DisplayName("고객의 방문날짜가 크리스마 이후 이면 혜택적용이 불가능하다.")
    void testApplicableEventCaseApplicable(int date) {
        Customer customer = new Customer(new DecemberVisitDate(date), orderMenu);

        assertThat(discountPolicy.applicableEvent(customer)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 7, 13, 14, 20, 21, 25})
    @DisplayName("고객의 방문날짜가 크리스마스 이전 이면 혜택적용이 가능하다.")
    void testApplicableEventCaseNotApplicable(int date) {
        Customer customer = new Customer(new DecemberVisitDate(date), orderMenu);

        assertThat(discountPolicy.applicableEvent(customer)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 25})
    @DisplayName("디데이 혜택의 혜택 금액을 리턴한다.")
    void testGetDiscountAmount(int date) {
        Customer customer = new Customer(new DecemberVisitDate(date), orderMenu);

        int result = discountPolicy.getDiscountAmount(customer);
        int expected = D_DAY_DISCOUNT_START_AMOUNT.get() + ((date - 1) * D_DAY_DISCOUNT_PLUS_AMOUNT.get());

        assertThat(result).isEqualTo(expected);
    }

}
