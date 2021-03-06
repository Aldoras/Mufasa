import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mufasa {
    private MufasaAccountConfirmation mufAccConf;
    private BankAccount bankAcc;
    private User user;

    public void setMufAccConf(MufasaAccountConfirmation mfac){
        mufAccConf = mfac;
    }
    public User createUserFromForm(String firstName, String lastName, String userName, String password,
            String passwordValidation, String country, String birthDate, String phoneNumber, String email,
            String emailValidation) throws Exception {
        return User.createUserFromForm(firstName, lastName, userName, password, passwordValidation, country, birthDate,
                phoneNumber, email, emailValidation);
    }

    public BankAccount createBankAccount(String address, String city, String postalCode, String country,
    String password, String accPassword, String holder, BankCardTypes type, long cardNumber, int expiracyMonth,
    int expiracyYear, int cvc) throws Exception {
        return BankAccount.createBankAccount(address, city, postalCode, country, password, accPassword, 
        holder, type, cardNumber, expiracyMonth, expiracyYear, cvc);
    }

    public boolean verifyAccountAttachmentConfirmation(int cvc){
        if((confirmAccountDeduction(cvc)== "Pass") &&
        confirmAccountRefund() == "Refund Success") 
        return true;
        return false;
    }

    public String confirmAccountDeduction(int cvc) {
        if (mufAccConf.deductMoney(cvc))
            return "Pass";
        return "Failed";
    }

    public String confirmAccountRefund() {
        if (mufAccConf.refundMoney())
            return "Refund Success";
        return "Refund Failed";
    }

}
