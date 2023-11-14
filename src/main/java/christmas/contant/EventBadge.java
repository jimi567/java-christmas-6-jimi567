package christmas.contant;

public enum EventBadge {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int minimumBenefitAmount;

    EventBadge(String name, int minimumBenefitAmount) {
        this.name = name;
        this.minimumBenefitAmount = minimumBenefitAmount;
    }

    public String getName() {
        return name;
    }

    public static EventBadge getEventBadgeByTotalDiscountAmount(int discountAmount) {
        if (discountAmount >= SANTA.minimumBenefitAmount) {
            return SANTA;
        } else if (discountAmount >= TREE.minimumBenefitAmount) {
            return TREE;
        } else if (discountAmount >= STAR.minimumBenefitAmount) {
            return STAR;
        }
        return NONE;
    }

}
