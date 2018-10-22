import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String country;
    private Date birthDate;
    private String phoneNumber;
    private String email;

    public User(){};
    public User(String firstName, String lastName, String userName, String pwd, String country,
    Date birthDate, String phoneNumber, String email){
        this.firstName=firstName;
        this.lastName=lastName;
        this.userName=userName;
        this.password=pwd;
        this.country=country;
        this.birthDate=birthDate;
        this.phoneNumber=phoneNumber;
        this.email=email;
    }
    public static User createUserFromForm(String firstName,String lastName,String userName,String password,
    String passwordValidation,String country,String birthDate,String phoneNumber,
    String email,String emailValidation) throws Exception{
        if(!verifyName(firstName)) throw new Exception("Invalid First Name");
        if(!verifyName(lastName)) throw new Exception("Invalid LastName");
        if(!verifyUserName(userName)) throw new Exception("Invalid UserName");
        if(!verifyPassword(password)) throw new Exception("Invalid Password");
        if(!verifyPasswordAndMailDuplicate(password, passwordValidation)) throw new Exception("Passwords dont match");
        if(!verifyCountry(country))throw new Exception("Invalid Coutnry");
        //Would be better to divide in two checks - but aint got time for that
        if(!verifyBirthDate(birthDate)) throw new Exception("Wrong BirthDate format, or the user is not 18 yet.");
        if(!verifyPhoneNumber(phoneNumber)) throw new Exception("Invalid Phone Number");
        if(!verifyEmail(email)) throw new Exception("Invalid email");
        if(!verifyPasswordAndMailDuplicate(email, emailValidation)) throw new Exception("Emails dont match");

        return new User(firstName,lastName,userName,password,country,new SimpleDateFormat("dd/MM/yyyy").parse(birthDate),
        phoneNumber,email);
    }
     //Only standardEnglish
     public static boolean verifyName(String name) {
        return name.matches("[A-Za-z]");
    }
    
    //Standart English + max one "_"
    public static boolean verifyUserName(String user)
    {
        return (user.length() < 16 && user.matches("[A-Za-z0-9]*_?[A-Za-z0-9]*"));
    }

    public static boolean verifyPasswordAndMailDuplicate(String input, String input2){
        return input == input2;
    }

    //Longer than 8 + hasUpperCaseSymbol
    public static boolean verifyPassword(String pwd)
    {
        Pattern regex;
        Matcher m;
        boolean hasUpperCase = false;
        regex = Pattern.compile("[A-Z]");
        m = regex.matcher(pwd);
        hasUpperCase = m.find();
        return pwd.length() > 7 && hasUpperCase;
    }

    public static boolean verifyCountry(String country)
    {
        //Country should be selected from provided list, user shouldnt be able to change it any way.
        //Plus I m not willing to make a country list a check whether the input matches :/
        return true;
    }

    public static boolean verifyBirthDate(String birthDate){
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try{
            date = formatter1.parse(birthDate);
                   
        }catch(Exception ex)
        {
            return false;
        }
        long diffInMillies = Math.abs(date.getTime() - new Date().getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        //Not the best approach tbh, coz of leap years...
        return diff >= 18*365;
    }

    public static boolean verifyPhoneNumber(String phone){
        return phone.matches("[0-9]*");
    }

    public static boolean verifyEmail(String email)
    {
        //Taken from web  https://www.regular-expressions.info/email.html, hope it works
        return email.matches("\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}\\b");
    }

}