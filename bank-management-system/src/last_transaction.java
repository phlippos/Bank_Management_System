import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class last_transaction {
    public static ArrayList<last_transaction> transactionArray = new ArrayList<last_transaction>();
    private int customerID;
    private int accountID;
    public int ammount;
    public String unit;
    public String transaction;
    public String recipientTC;
    public last_transaction(int customerID,int accountID,int ammount,String unit,String transaction){
    this.customerID = customerID;
    this.accountID = accountID;
    this.ammount = ammount;
    this.unit = unit;
    this.transaction = transaction;

    }
    public last_transaction(int customerID,int accountID,int ammount,String unit,String TC,String transaction){
        this.customerID = customerID;
        this.accountID = accountID;
        this.ammount = ammount;
        this.recipientTC = TC;
        this.unit = unit;
        this.transaction = transaction;

    }
    public  int getCustomerID(){
        return this.customerID;
    }
    public  int getAccountID(){
        return this.accountID;
    }
    public static void last_transaction_write() throws IOException {
            File transactionFile = new File("C:\\Users\\MONSTER\\IdeaProjects\\bank-management-system\\src\\transaction.txt");
            transactionFile.delete();
            transactionFile.createNewFile();
            FileWriter writer = new FileWriter("C:\\Users\\MONSTER\\IdeaProjects\\bank-management-system\\src\\transaction.txt");
            writer.write("Müşteri id   - Hesap id   - miktar   - birim  -tc  - işlem\n");
            transactionArray.forEach(
                    (n)->{
                        try {
                            if(n.recipientTC!=null) {
                                writer.write(n.customerID + " " + n.accountID + " " + n.ammount + " " + n.unit + " " +n.recipientTC +" "+ n.transaction + "\n");
                                writer.flush();
                            }else {
                                writer.write(n.customerID + " " + n.accountID + " " + n.ammount + " " + n.unit + " " + n.transaction + "\n");
                                writer.flush();
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }

            );
            writer.close();

    }

    public static void last_transaction_load() throws FileNotFoundException {
        File transactionFile = new File("C:\\Users\\MONSTER\\IdeaProjects\\bank-management-system\\src\\transaction.txt");
        Scanner reader = new Scanner(transactionFile);
        reader.nextLine();
        while(reader.hasNext()) {
            last_transaction x;
            String y = reader.nextLine();
            Scanner reader2 = new Scanner(y);
            reader2.useDelimiter(" ");

            if(y.contains("PARA-AKTARMA")){



                x = new last_transaction(Integer.parseInt(reader2.next()), Integer.parseInt(reader2.next()), Integer.parseInt(reader2.next()), reader2.next(),reader2.next(), reader2.next());

            } else {
                 x = new last_transaction(Integer.parseInt(reader2.next()), Integer.parseInt(reader2.next()), Integer.parseInt(reader2.next()), reader2.next(), reader2.next());

            }
            transactionArray.add(x);
        }
        reader.close();
    }




}
