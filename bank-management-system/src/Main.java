import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static ArrayList<customer> cstmr_Array = new ArrayList<customer>();
    public static HashMap<String,String> Tc_PasswordHashMap = new HashMap<String,String>();

    public static void main(String[] args) throws ParseException, IOException {

        management.customer_management.customer_load();
        last_transaction.last_transaction_load();

        boolean x = true;

        while (x) {
            System.out.println("1-Giriş yap\n2-Çıkış");
            Scanner trsctnObj = new Scanner(System.in);
            String transaction = trsctnObj.nextLine();
            switch (transaction) {
                case "1":
                    System.out.println("TC : ");
                    Scanner objOfTC = new Scanner(System.in);
                    String TC = objOfTC.nextLine();
                    System.out.println("Şifre : ");
                    Scanner objOfPassword = new Scanner(System.in);
                    String password = objOfPassword.nextLine();
                    int check_login = management.customer_management.login_check(TC, password);

                    if(check_login>=0) {
                        customer customer1= null;


                        for(customer cstmr : cstmr_Array){
                            if(cstmr.getCustomer_id() == check_login){
                                customer1 = cstmr;
                                break;
                            }
                        }
                        homepage objOfHomepage = new homepage(customer1);
                        objOfHomepage.menu();

                    }
                    break;
                case "2":
                    x = false;
                    last_transaction.last_transaction_write();
                    management.customer_management.customer_upload();
                    break;
                default:
                    System.out.println("bir hata oluştu");
                    break;
            }

        }


    }
}