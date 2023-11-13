package christmas.domain.menu;

public record DrinkMenu(String name) implements Menu {
    @Override
    public String getName() {
        return name();
    }
}
