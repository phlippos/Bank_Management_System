import java.util.Date;
import java.util.Random;

public class customer extends Accounts {
    private static int x = 0;
    private  int customer_id;
    protected String customer_name;
    protected String customer_surname;
    protected String customer_TC;
    protected Date customer_birthday;
    protected String customer_phoneNumber;
    private String customer_pass;



    customer(String customer_name, String customer_surname,String customer_TC,Date customer_birthday,String customer_phoneNumber){
        super(x);
        this.customer_id = x;

        x++;
        this.customer_name = customer_name;
        this.customer_surname = customer_surname;
        this.customer_TC = customer_TC;
        this.customer_birthday = customer_birthday;
        this.customer_phoneNumber = customer_phoneNumber;
        password_generator();


    }

    private void password_generator(){
        int max = 122;
        int min = 97;
        String pass ="";
        for(int i = 0; i<10;i++) {

           Random random = new Random();
            int x = 122-random.nextInt(25);
            pass += (char)x;

        }
        this.customer_pass = pass;
    }
    protected int getCustomer_id(){
        return this.customer_id;
    }
    protected void setCustomer_pass(String pass){
        this.customer_pass = pass;
    }
    protected String getCustomer_pass(){
        return this.customer_pass;
    }


    protected void setCustomer_id(int id){
        this.customer_id = id;
        this.x = id+1;
    }
}
