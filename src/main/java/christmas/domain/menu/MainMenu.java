package christmas.domain.menu;

public record MainMenu(String name) implements Menu {
    @Override
    public String getName() {
        return name();
    }
}
