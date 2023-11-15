package christmas.domain.customer;

import static christmas.contant.Event.EVENT_APPLICABLE_AMOUNT;

public record Customer(VisitDate visitDate, OrderMenu orderMenu) {
    public boolean isEventTarget() {
        return orderMenu.getTotalPrice() >= EVENT_APPLICABLE_AMOUNT.get();
    }
}
