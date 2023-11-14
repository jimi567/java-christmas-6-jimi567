package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.contant.MenuBoard;
import christmas.domain.customer.Customer;
import christmas.domain.customer.DecemberVisitDate;
import christmas.domain.customer.OrderMenu;
import christmas.domain.menu.Menu;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GiftDiscountPolicyTest {

    DiscountPolicy discountPolicy = new GiftDiscountPolicy();
    OrderMenu orderMenu;

    @BeforeEach
    void setOrderMenu() {
        HashMap<Menu, Integer> orderMenuSource = new HashMap<>();
        //양송이 수프 20개 주문하면 12만원
        orderMenuSource.put(new Menu("양송이수프"), 20);
        orderMenu = new OrderMenu(orderMenuSource);
    }

    @Test
    @DisplayName("고객의 총 주문 금액이 12만원 이상이면 혜택적용이 가능하다.")
    void testApplicableEventCaseNotApplicable() {
        Customer customer = new Customer(new DecemberVisitDate(31), orderMenu);

        assertThat(discountPolicy.applicableEvent(customer)).isTrue();
    }

    @Test
    @DisplayName("증정 혜택의 혜택 금액을 리턴한다.")
    void testGetDiscountAmount() {
        Customer customer = new Customer(new DecemberVisitDate(1), orderMenu);

        int result = discountPolicy.getDiscountAmount(customer);
        int expected = MenuBoard.CHAMPAGNE.getPrice();

        assertThat(result).isEqualTo(expected);
    }

}

