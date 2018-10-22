public class BankAccount {
    private String adress;
    private String city;
    private String postalCode;

    private String country;
    private String password;

    private BankCard card;

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
            int expiracyYear, int cvc) {

                return new BankAccount(address, city, postalCode, country, password,
                new BankCard(holder, type, cardNumber, expiracyMonth, expiracyYear, cvc));
     }


     public boolean verifyAdress(String adress){
         return adress.matches("[A-Za-z0-9]*");
     }
     public boolean verifyCityAndCountry(String city){
        return city.matches("[A-Za-z]*");
    }
    public boolean verifyPostal(String postal){
        return postal.matches("[0-9]*-?[0-9]*");
    }
    public boolean verifyPasswordDifference(String pwd,String accPwd){
        return pwd != accPwd;
    }
    public boolean verifyPassword(String pwd){
        return User.verifyPassword(pwd);
    }

    public boolean verifyCardDetails(String holder, BankCardTypes type, long cardNumber, int expiracyMonth, int expiracyYear, int cvc){
        //Not implemented..
        return true;
    }

}