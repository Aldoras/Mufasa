public class BankAccount {
    private String adress;
    private String city;
    private String postalCode;

    private String country;
    private String password;

    private BankCard card;
    public BankAccount(){};
    public BankAccount(String address, String city, String postalCode, String country, String password, BankCard card) {
        this.adress = adress;
        this.city = city;
        this.postalCode = postalCode;

        this.country = country;
        this.password = password;

        this.card = card;
    }

    public static BankAccount createBankAccount(String address, String city, String postalCode, String country,
            String password, String accPassword, String holder, BankCardTypes type, long cardNumber, int expiracyMonth,
            int expiracyYear, int cvc) throws Exception {
                if(!(verifyAdress(address))) throw new Exception("Invalid address");
                if(!(verifyCityAndCountry(city))) throw new Exception("Invalid city");
                if(!(verifyPostal(postalCode))) throw new Exception("Invalid postalCode");
                if(!(verifyCityAndCountry(country))) throw new Exception("Invalid country");
                if(!(verifyPasswordDifference(password,accPassword))) throw new Exception("Password has to be diferent from the Account password");
                if(!(verifyPassword(password))) throw new Exception("Invalid password");
                if(!(verifyCardDetails(holder, type, cardNumber, expiracyMonth, expiracyYear, cvc))) throw new Exception("Invalid card details");
                return new BankAccount(address, city, postalCode, country, password,
                new BankCard(holder, type, cardNumber, expiracyMonth, expiracyYear, cvc));
     }


     public static boolean verifyAdress(String adress){
         return adress.matches("[A-Za-z0-9]*");
     }
     public static boolean verifyCityAndCountry(String city){
        return city.matches("[A-Za-z]*");
    }
    public static boolean verifyPostal(String postal){
        return postal.matches("[0-9]*-?[0-9]*");
    }
    public static boolean verifyPasswordDifference(String pwd,String accPwd){
        return pwd != accPwd;
    }
    public static boolean verifyPassword(String pwd){
        return User.verifyPassword(pwd);
    }

    public static boolean verifyCardDetails(String holder, BankCardTypes type, long cardNumber, int expiracyMonth, int expiracyYear, int cvc){
        //Not implemented..
        return true;
    }

}