package christmas.domain.customer;

public record Customer(VisitDay visitDay, OrderMenu orderMenu) {

    public int getTotalAmount() {
        return orderMenu.getTotalAmount();
    }

}
