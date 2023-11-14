package christmas;

import christmas.controller.DecemberEventPlannerController;

public class Application {
    public static void main(String[] args) {
        DecemberEventPlannerController decemberEventPlannerController = new DecemberEventPlannerController();
        decemberEventPlannerController.run();
    }
}
