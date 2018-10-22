public class BankCard {
    private String holder;
    private BankCardTypes type;
    private long cardNumber;
    private int expiracyMonth;
    private int expiracyYear;
    private int cvc;

    public BankCard(String holder, BankCardTypes type, long cardNumber, int expiracyMonth, int expiracyYear, int cvc) {
        this.holder = holder;
        this.type = type;
        this.cardNumber = cardNumber;
        this.expiracyMonth = expiracyMonth;
        this.expiracyYear = expiracyYear;
        this.cvc = cvc;
    }

    
}
