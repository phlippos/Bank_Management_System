import java.io.IOException;
import java.util.Scanner;


class homepage {
    private customer customer1;
    homepage(customer customer1){
       this.customer1 = customer1;
    }

    public  void menu() throws IOException {
        Boolean status = true;

        while(status) {
            System.out.println("Menü");
            System.out.println("1/Hesaplarım\n2/Son işlemler\n5/Çıkış");
            Scanner objOfTransaction = new Scanner(System.in);
            String transaction = objOfTransaction.nextLine();

            switch (transaction) {
                case "1":
                    System.out.println("1/Vadesiz Hesap");
                    Scanner objOfAccount = new Scanner(System.in);
                    String Account = objOfAccount.nextLine();
                    switch(Account){
                        case "1":
                            System.out.println("1/Para çek\n2/para yatırmak\n3/Varlıklarım\n4/Para yolla");
                            Scanner objOfTransactionOfAccount = new Scanner(System.in);
                            String transactionOfAccount = objOfTransactionOfAccount.nextLine();
                            if(transactionOfAccount.equals("1")){
                                customer1.objOfChecking_Account.withdraw_money(customer1.getCustomer_id());
                            }else if(transactionOfAccount.equals("2")){
                                customer1.objOfChecking_Account.deposit(customer1.getCustomer_id());
                            }else if(transactionOfAccount.equals("3")){
                                System.out.println("Tutar : "+customer1.objOfChecking_Account.getAmmount()+customer1.objOfChecking_Account.getUnit());
                            }else if(transactionOfAccount.equals("4")){
                                customer1.objOfChecking_Account.sending_money(customer1.getCustomer_id());
                            }
                            break;
                        default:
                            System.out.println("Hata");
                            break;
                    }
                    break;
                case "2" :
                    last_transaction.transactionArray.forEach(
                            (n)->{

                                    if(n.recipientTC!=null){
                                        if(n.recipientTC.equals(customer1.customer_TC)){
                                            Main.cstmr_Array.forEach(
                                                    (x)->{
                                                        if(x.getCustomer_id() == n.getCustomerID()){
                                                            System.out.println("İşlem : GELEN PARA\nMiktar : " + n.ammount + n.unit + "\nKimden : " + x.customer_name+" "+x.customer_surname);
                                                        }
                                                    }
                                            );

                                        }else if(n.getCustomerID() == customer1.getCustomer_id() && n.getAccountID()== customer1.objOfChecking_Account.getChecking_Account_id()) {
                                            System.out.println("İşlem : " + n.transaction + "\nMiktar : " + n.ammount + n.unit + "\nKime : " + n.recipientTC);
                                        }
                                    }else if(n.getCustomerID() == customer1.getCustomer_id() && n.getAccountID()== customer1.objOfChecking_Account.getChecking_Account_id()) {

                                        System.out.println("İşlem : " + n.transaction + "\nmiktar : " + n.ammount + n.unit);
                                    }
                               // }
                            }
                    );
                    break;
                case "5" :
                    status = false;
                    break;
                default:
                    System.out.println("hata.");
                    break;
            }
        }
    }
}
