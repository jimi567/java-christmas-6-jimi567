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
        return customer.getTotalAmount() >= GIFT_APPLICABLE_AMOUNT.get();
    }
}
