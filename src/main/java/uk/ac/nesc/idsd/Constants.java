package uk.ac.nesc.idsd;

public class Constants {

    public static final String ALL_CENTRES = "All Centres";
    public static final String ICAH = "icah";
    public static final String ROLE_PATIENT = "ROLE_PATIENT";

    public static final String EMAIL_REG_EXP = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public static final String USERNAME_REG_EXP = "^[a-zA-Z0-9_-]{3,20}$"; //letters (Upper or lower), numbers, _ and -, length between 3 and 20
    //    public static final String FULL_NAME_REG_EXP = "^[a-zA-Z\\s]+";
    public static final String FULL_NAME_REG_EXP = "^[\\p{L} ,.'-]+$";
    public static final String PASSWORD_REG_EX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
}
