package christmas.controller;

import static christmas.contant.ViewMessage.DECIMAL_FORMAT;
import static christmas.contant.ViewMessage.DISCOUNT_AMOUNT_FORMAT;
import static christmas.contant.ViewMessage.MONEY_FORMAT;

import christmas.domain.customer.Customer;
import christmas.domain.customer.DecemberVisitDate;
import christmas.domain.customer.OrderMenu;
import christmas.domain.customer.VisitDate;
import christmas.domain.discount.DdayDiscountPolicy;
import christmas.domain.discount.GiftDiscountPolicy;
import christmas.domain.discount.SpecialDiscountPolicy;
import christmas.domain.discount.WeekdayDiscountPolicy;
import christmas.domain.discount.WeekendDiscountPolicy;
import christmas.domain.service.EventPlannerService;
import christmas.domain.service.WootecoDecemberEventPlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.text.DecimalFormat;
import java.util.List;
import util.inputparser.InputParser;

public class DecemberEventPlannerController {

    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();
    private final InputParser inputParser = new InputParser();
    private final DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT.get());
    private EventPlannerService eventPlannerService;
    private VisitDate visitDate;
    private OrderMenu orderMenu;
    private Customer customer;


    public void run() {
        setEventPlannerService();
        outputView.printStartMessage();
        setCustomer();
        outputView.printResultMessage();
        showResult();
    }

    private void setEventPlannerService() {
        eventPlannerService = new WootecoDecemberEventPlannerService(
                List.of(new DdayDiscountPolicy(), new WeekdayDiscountPolicy(), new WeekendDiscountPolicy(),
                        new GiftDiscountPolicy(), new SpecialDiscountPolicy()));
    }

    private void setCustomer() {
        setVisitDate();
        setOrderMenuPrompt();
        customer = new Customer(visitDate, orderMenu);
    }

    private void setVisitDate() {
        try {
            visitDate = new DecemberVisitDate(inputParser.parseInt(inputView.readVisitDay()));
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            setVisitDate();
        }
    }

    private void setOrderMenuPrompt() {
        try {
            orderMenu = new OrderMenu(inputParser.parseOrderMenu(inputView.readOrderMenu()));
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            setOrderMenuPrompt();
        }
    }

    private void showResult() {
        showOrderMenu();
        showBeforeDiscountTotalPayment();
        showGiftMenu();
        showBenefitHistory();
        showTotalBenefitAmount();
        showAfterDiscountTotalPayment();
        showEventBadge();
    }

    private void showOrderMenu() {
        outputView.printOrderMenu(orderMenu.toString());
    }

    private void showBeforeDiscountTotalPayment() {
        outputView.printBeforeDiscountTotalPayment(
                String.format(MONEY_FORMAT.get(), decimalFormat.format(customer.getTotalPayment())));
    }

    private void showGiftMenu() {
        outputView.printGiftMenu(eventPlannerService.getGiftMenuHistory(customer));
    }

    private void showBenefitHistory() {
        outputView.printBenefitHistory(eventPlannerService.getBenefitHistory(customer));
    }

    private void showTotalBenefitAmount() {
        int totalBenefitAmount = eventPlannerService.getTotalDiscountAmount(customer);
        String history = String.format(MONEY_FORMAT.get(), 0);
        if (totalBenefitAmount > 0) {
            history = String.format(DISCOUNT_AMOUNT_FORMAT.get(),
                    decimalFormat.format(eventPlannerService.getTotalDiscountAmount(customer)));
        }
        outputView.printTotalBenefitAmount(history);
    }

    private void showAfterDiscountTotalPayment() {
        outputView.printAfterDiscountTotalPayment(String.format(MONEY_FORMAT.get(),
                decimalFormat.format(eventPlannerService.getDiscountedPayment(customer))));
    }

    private void showEventBadge() {
        outputView.printEventBadge(eventPlannerService.getEventBadge(customer).getName());
    }
}
