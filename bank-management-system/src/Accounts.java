import java.io.IOException;
import java.util.Scanner;
public abstract class Accounts {

    private int id;
    public checking_Account objOfChecking_Account;
    public Accounts(int id){
        this.id = id;
        this.objOfChecking_Account = new checking_Account();

    }

    public static class checking_Account {
        private static int x = 0;
        private int checking_Account_id;
        private int checking_Account_ammount=0;
        private String unit;

        public checking_Account(){
            this.checking_Account_id = x;
            x++;




        }
        public void setChecking_Account_id(int id){
            this.checking_Account_id  = id;
            x=id+1;
        }
        public void setChecking_Account_ammount(int money){
            this.checking_Account_ammount  = money;

        }
        public void setUnit(String unit){
            this.unit  = unit;

        }




        public  void withdraw_money(int id) throws IOException {

            System.out.println("Ne kadar para çekmek istiyorsunuz ? ");
            Scanner obj_money = new Scanner(System.in);

            int money = obj_money.nextInt();

            if (money > this.checking_Account_ammount) {
                System.out.println("Yetersiz bakiye.");
            } else {
                this.checking_Account_ammount -= money;
                last_transaction objLastTransaction = new last_transaction(id,this.checking_Account_id,money,this.unit,"PARA-ÇEKME");
                last_transaction.transactionArray.add(objLastTransaction);
                System.out.println("İyi günlerde harcayın");

            }

        }
        public void deposit(int id) throws IOException {
            System.out.println("Ne kadar para yatırmak istiyorsunuz ?");
            Scanner obj_money = new Scanner(System.in);
            int money = obj_money.nextInt();
            if(money>0) {
                this.checking_Account_ammount +=money;
                last_transaction objLastTransaction = new last_transaction(id,this.checking_Account_id,money,this.unit,"PARA-YATIRMA");
                last_transaction.transactionArray.add(objLastTransaction);
            }else{
                System.out.println("Lütfen uygun bir miktar girin.");
            }
        }
        //DOLLAR 15 EURO 20
        public void sending_money(int id){
            System.out.println("Kime para yollamak istiyorsanız TC numarasını giriniz");
            Scanner inputofTC = new Scanner(System.in);
            String TC = inputofTC.nextLine();
            if(Main.Tc_PasswordHashMap.containsKey(TC)) {
                System.out.println("Yollamak istediğiniz miktarı giriniz : ");
                Scanner inputofMoney = new Scanner(System.in);
                int money = inputofMoney.nextInt();
                if(this.checking_Account_ammount>= money ){
                    this.checking_Account_ammount -= money;
                    for(customer x : Main.cstmr_Array){
                        if(x.customer_TC.equals(TC)){

                            last_transaction lastTransaction = new last_transaction(id,this.checking_Account_id,money,this.unit,TC,"PARA-AKTARMA");
                            last_transaction.transactionArray.add(lastTransaction);
                            if(this.unit.equals("TL") && x.objOfChecking_Account.unit.equals("DOLLAR")){
                                money/=15;
                            }else if(this.unit.equals("TL") && x.objOfChecking_Account.unit.equals("EURO")){
                                money/=20;
                            }else if(this.unit.equals("EURO") && x.objOfChecking_Account.unit.equals("TL")){
                                money*=20;
                            }else if(this.unit.equals("EURO") && x.objOfChecking_Account.unit.equals("DOLLAR")){
                                money*=5;
                            }else if(this.unit.equals("DOLLAR") && x.objOfChecking_Account.unit.equals("TL")){
                                money*=15;
                            }else if(this.unit.equals("DOLLAR") && x.objOfChecking_Account.unit.equals("EURO")){
                                money/=5;
                            }
                            x.objOfChecking_Account.checking_Account_ammount += money;
                            System.out.println("Para yollandı.");
                        }
                    }
                }else{
                    System.out.println("Yeterli bakiyeniz yok.");
                }
            }else{
                System.out.println("Girdiğiniz TC numarası yanlış");
            }
        }


        public int getAmmount() {
            return this.checking_Account_ammount;
        }
        public int getChecking_Account_id() {
            return this.checking_Account_id;
        }
        public String getUnit() {
            return this.unit;
        }
    }

}
