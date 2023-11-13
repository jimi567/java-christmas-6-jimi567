package christmas.domain.menu;

public record DessertMenu(String name) implements Menu {
    @Override
    public String getName() {
        return name();
    }
}
