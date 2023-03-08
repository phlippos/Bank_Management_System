import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

abstract class management {


    abstract static class customer_management {


        static void customer_upload() throws IOException {
            File myFile = new File("C:\\Users\\MONSTER\\IdeaProjects\\bank-management-system\\src\\customers.txt");
            File fileOfAccounts = new File("C:\\Users\\MONSTER\\IdeaProjects\\bank-management-system\\src\\Accounts\\Accounts.txt");
            fileOfAccounts.delete();
            myFile.delete();
            fileOfAccounts.createNewFile();
            myFile.createNewFile();



            FileWriter writerOfAccounts = new FileWriter("C:\\Users\\MONSTER\\IdeaProjects\\bank-management-system\\src\\Accounts\\Accounts.txt");
            writerOfAccounts.write("müşteri id  -hesap id    -miktar    - birim ");
            FileWriter myWriter = new FileWriter("C:\\Users\\MONSTER\\IdeaProjects\\bank-management-system\\src\\customers.txt");
            myWriter.write("id    -name    -surname    -TC          -doğum tarihi    -telefon numarası        -password");
            Main.cstmr_Array.forEach(
                    (n) -> {
                        try {

                            String date = new SimpleDateFormat("yyyy-MM-dd").format(n.customer_birthday);
                            myWriter.write("\n" + n.getCustomer_id() + " " + n.customer_name + " " + n.customer_surname + " " + n.customer_TC + " " + date + " " + n.customer_phoneNumber + " " + n.getCustomer_pass());
                            myWriter.flush();

                            writerOfAccounts.write("\n"+n.getCustomer_id()+" " +n.objOfChecking_Account.getChecking_Account_id()+" "+n.objOfChecking_Account.getAmmount()+" "+n.objOfChecking_Account.getUnit());
                            writerOfAccounts.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

            );
            myWriter.close();


        }


        static void customer_load() throws FileNotFoundException, ParseException {
            File myFile = new File("C:\\Users\\MONSTER\\IdeaProjects\\bank-management-system\\src\\customers.txt");
            Scanner myReader = new Scanner(myFile);
            myReader.nextLine();
            while (myReader.hasNext()) {

                int customer_id = Integer.parseInt(myReader.next());

                customer cstmrObj = new customer(myReader.next(), myReader.next(),
                        myReader.next(),
                        new SimpleDateFormat("yyyy-MM-dd").parse(myReader.next()),
                        myReader.next());
                cstmrObj.setCustomer_pass(myReader.next());
                cstmrObj.setCustomer_id(customer_id);
                Main.cstmr_Array.add(cstmrObj);


            }

            myReader.close();

            File FileOfAccounts = new File("C:\\Users\\MONSTER\\IdeaProjects\\bank-management-system\\src\\Accounts\\Accounts.txt");
            Scanner readerOfAccounts = new Scanner(FileOfAccounts);
            readerOfAccounts.nextLine();
            while(readerOfAccounts.hasNext()){
                Accounts.checking_Account x = new Accounts.checking_Account();
                int id = Integer.parseInt(readerOfAccounts.next());
                x.setChecking_Account_id(Integer.parseInt(readerOfAccounts.next()));
                x.setChecking_Account_ammount(Integer.parseInt(readerOfAccounts.next()));
                x.setUnit(readerOfAccounts.next());

                for(customer obj : Main.cstmr_Array){
                    if(obj.getCustomer_id()==id){
                        obj.objOfChecking_Account = x;
                    }
                }

            }
            readerOfAccounts.close();

            Main.cstmr_Array.forEach((n) -> {
                Main.Tc_PasswordHashMap.put(n.customer_TC, n.getCustomer_pass());
                    }
            );


        }

        static void customer_add() throws ParseException, IOException {
            System.out.println("Müşteri adı : ");
            Scanner objOfName = new Scanner(System.in);
            String name = objOfName.nextLine();
            System.out.println("Müşteri soyadı : ");
            Scanner objOfSurname = new Scanner(System.in);
            String surname = objOfSurname.nextLine();
            System.out.println("Müşteri TC : ");
            Scanner objOfTC = new Scanner(System.in);
            String TC = objOfTC.nextLine();
            if(!Main.Tc_PasswordHashMap.containsKey(TC) && TC.length()==11) {


                System.out.println("Müşteri Doğum günü : ");
                Scanner objOfbirthday = new Scanner(System.in);
                String birthday = objOfbirthday.nextLine();
                System.out.println("Müşteri telefon numarası : ");
                Scanner objOfPhoneNumber = new Scanner(System.in);
                String phoneNumber = objOfPhoneNumber.nextLine();

                if(phoneNumber.length()==11) {
                    System.out.println("-TL\n-EURO\n-DOLLAR");
                    Scanner obj_unit = new Scanner(System.in);

                    System.out.println("Hangi para birimi ile hesap açmak istiyorsunuz?");
                    String unit = obj_unit.nextLine();

                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
                    customer x = new customer(name, surname, TC, date, phoneNumber);
                    x.objOfChecking_Account.setUnit(unit);
                    System.out.println("Müşteri oluşturuldu.Şifre telefon numarasına gönderildi");
                    Main.cstmr_Array.add(x);
                    Main.Tc_PasswordHashMap.put(TC, x.getCustomer_pass());
                    customer_upload();
                }else {
                    System.out.println("Hatalı bir telefon numarası girdiniz!");
                }
            }else{
                System.out.println("Hatalı bir TC numarası girdiniz!");
            }
        }

        static int login_check(String TC, String password) {
            int id = 0;
            if (Main.Tc_PasswordHashMap.containsKey(TC)) {

                if (Main.Tc_PasswordHashMap.get(TC).compareTo(password) == 0) {

                   for(customer n:Main.cstmr_Array ){
                       if(n.customer_TC.equals(TC) ){

                           id = n.getCustomer_id();
                       }
                   }

                } else {
                    System.out.println("Şifreniz hatalı.");
                     id=-1;
                }
            } else {
                System.out.println("Girdiğiniz TC yanlış.");
                id=-1;
            }
            return id;

        }
    }
        public static void main(String args[]) throws ParseException, IOException {
            management.customer_management.customer_load();
            last_transaction.last_transaction_load();
            boolean x = true;

            while (x) {
                System.out.println("1-Müşteri ekle\n2-İşlemler\n3-Çıkış");
                Scanner trsctnObj = new Scanner(System.in);
                String transaction = trsctnObj.nextLine();
                switch (transaction) {
                    case "1":
                        management.customer_management.customer_add();
                        break;
                    case "2":
                        last_transaction.transactionArray.forEach(
                                (n)->{
                                    System.out.println("Müşteri id : "+n.getCustomerID()+"\nHesap id : "+n.getAccountID()+"\nişlem : "+n.transaction
                                    +"\nmiktar : "+n.ammount+"\nbirim : "+n.unit);
                                    System.out.println("------------------------------------------------------------------------------");

                                }
                        );
                        break;
                    case "3":
                        x = false;
                        break;
                    default:
                        System.out.println("bir hata oluştu");
                        break;
                }

            }


        }

}

