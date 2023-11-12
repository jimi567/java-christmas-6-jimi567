package christmas.contant;

public enum EventBadge {
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

    public int getMinimumBenefitAmount() {
        return minimumBenefitAmount;
    }
}
