package christmas.domain.discount;

import static christmas.contant.Event.GIFT_APPLICABLE_AMOUNT;

import christmas.contant.MenuBoard;
import christmas.domain.customer.Customer;

public class GiftDiscountPolicy implements DiscountPolicy {
    @Override
    public int getDiscountAmount(Customer customer) {
        if (applicableEvent(customer)) {
            return MenuBoard.CHAMPAGNE.getPrice();
        }
        return 0;
    }

    @Override
    public boolean applicableEvent(Customer customer) {
        return customer.isEventTarget() && customer.orderMenu().getTotalPrice() >= GIFT_APPLICABLE_AMOUNT.get();
    }

    @Override
    public String toString() {
        return "증정 이벤트: ";
    }
}
