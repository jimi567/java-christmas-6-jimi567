package christmas.domain.menu;

public record AppetizerMenu(String name) implements Menu {
    @Override
    public String getName() {
        return name();
    }
}
