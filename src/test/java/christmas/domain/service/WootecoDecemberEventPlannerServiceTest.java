package christmas.domain.service;

import static christmas.contant.EventBadge.SANTA;
import static christmas.contant.MenuBoard.CHAMPAGNE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.contant.EventBadge;
import christmas.domain.customer.Customer;
import christmas.domain.customer.DecemberVisitDay;
import christmas.domain.customer.OrderMenu;
import christmas.domain.menu.Menu;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WootecoDecemberEventPlannerServiceTest {
    EventPlannerService eventPlannerService = new WootecoDecemberEventPlannerService();
    Customer customer;

    @BeforeEach
    void setCustomer() {
        HashMap<Menu, Integer> orderMenuSource = new HashMap<>();
        orderMenuSource.put(new Menu("티본스테이크"), 1);
        orderMenuSource.put(new Menu("바비큐립"), 1);
        orderMenuSource.put(new Menu("초코케이크"), 2);
        orderMenuSource.put(new Menu("제로콜라"), 1);
        customer = new Customer(new DecemberVisitDay(3), new OrderMenu(orderMenuSource));
    }

    @Test
    @DisplayName("총 주문이 만원 이상이면 이벤트 대상자이다")
    void testIsEventTarget() {
        boolean result = eventPlannerService.isEventTarget(customer);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("이벤트 배지를 반환한다.")
    void testGetEventBadge() {
        EventBadge result = eventPlannerService.getEventBadge(customer);
        EventBadge expected = SANTA;

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("총 할인 혜택 금액을 반환한다.")
    void testGetTotalDiscountAmount() {
        int result = eventPlannerService.getTotalDiscountAmount(customer);
        int expected = 31246;

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testGetBenefitHistory() {
        String result = eventPlannerService.getBenefitHistory(customer);

        assertThat(result).contains(
                "크리스마스 디데이 할인: -1,200원",
                "평일 할인: -4,046원",
                "특별 할인: -1,000원",
                "증정 이벤트: -25,000원"
        );
    }

    @Test
    void testGetGiftMenu() {
        Menu result = eventPlannerService.getGiftMenu(customer);
        Menu expected = CHAMPAGNE.getMenu();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testGetDiscountedPayment() {
        int result = eventPlannerService.getDiscountedPayment(customer);
        int expected = 135_754;

        assertThat(result).isEqualTo(expected);
    }
}