package christmas.domain.customer;

public record Customer(VisitDate visitDate, OrderMenu orderMenu) {

    public int getTotalPayment() {
        return orderMenu.getTotalPrice();
    }

}
