package christmas.controller;

import static christmas.contant.ViewMessage.DECIMAL_FORMAT;
import static christmas.contant.ViewMessage.DISCOUNT_AMOUNT_FORMAT;
import static christmas.contant.ViewMessage.MONEY_FORMAT;

import christmas.domain.customer.Customer;
import christmas.domain.customer.DecemberVisitDate;
import christmas.domain.customer.OrderMenu;
import christmas.domain.customer.VisitDate;
import christmas.domain.discount.DdayDiscountPolicy;
import christmas.domain.discount.DiscountPolicy;
import christmas.domain.discount.GiftDiscountPolicy;
import christmas.domain.discount.SpecialDiscountPolicy;
import christmas.domain.discount.WeekdayDiscountPolicy;
import christmas.domain.discount.WeekendDiscountPolicy;
import christmas.domain.service.EventPlannerService;
import christmas.domain.service.WootecoDecemberEventPlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import util.inputparser.InputParser;

public class DecemberEventPlannerController {

    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();
    private final InputParser inputParser = new InputParser();
    private final List<DiscountPolicy> discountPolicies = new ArrayList<>();
    private final EventPlannerService eventPlannerService = new WootecoDecemberEventPlannerService();
    private final DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT.get());
    private VisitDate visitDate;
    private OrderMenu orderMenu;
    private Customer customer;


    public void run() {
        initDiscountPolies();
        outputView.printStartMessage();
        setCustomer();
        outputView.printResultMessage();
        showResult();
    }

    private void initDiscountPolies() {
        discountPolicies.add(new DdayDiscountPolicy());
        discountPolicies.add(new WeekendDiscountPolicy());
        discountPolicies.add(new WeekdayDiscountPolicy());
        discountPolicies.add(new SpecialDiscountPolicy());
        discountPolicies.add(new GiftDiscountPolicy());
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
        outputView.printOrderMenu(orderMenu.toString());
        outputView.printBeforeDiscountTotalPayment(
                String.format(MONEY_FORMAT.get(), decimalFormat.format(customer.getTotalPayment())));
        outputView.printGiftMenu(eventPlannerService.getGiftMenuHistory(customer));
        outputView.printBenefitHistory(eventPlannerService.getBenefitHistory(customer));
        outputView.printTotalBenefitAmount(String.format(DISCOUNT_AMOUNT_FORMAT.get(),
                decimalFormat.format(eventPlannerService.getTotalDiscountAmount(customer))));
        outputView.printAfterDiscountTotalPayment(String.format(MONEY_FORMAT.get(),
                decimalFormat.format(eventPlannerService.getDiscountedPayment(customer))));
        outputView.printEventBadge(eventPlannerService.getEventBadge(customer).getName());
    }

    private void showOrderMenu() {
        outputView.printOrderMenu(orderMenu.toString());
    }

}
