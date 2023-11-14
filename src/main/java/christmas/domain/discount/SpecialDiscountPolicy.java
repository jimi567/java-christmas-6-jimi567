package christmas.domain.discount;

import static christmas.contant.Event.SPECIAL_DISCOUNT_AMOUNT;

import christmas.domain.customer.Customer;

public class SpecialDiscountPolicy implements DiscountPolicy {
    @Override
    public int getDiscountAmount(Customer customer) {
        if (applicableEvent(customer)) {
            return SPECIAL_DISCOUNT_AMOUNT.get();
        }
        return 0;
    }

    @Override
    public boolean applicableEvent(Customer customer) {
        return customer.visitDay().isStarDay();
    }
}
