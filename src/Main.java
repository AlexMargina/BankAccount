public class Main {
    public static void main(String[] args) {
        Bank.createNewAccount ("debit_account", "RUB");
    }
}

class BankAccount {

    protected int amount;
    protected String currency;

    public void replenishBalance(int amount) {
        this.amount += amount;
        System.out.println("Счет пополнен на " + amount + " " + currency);
    }

    public void withdrawCash(int amount) {
    }

    public void showBalance() {
    }
}

class DebitAccount extends BankAccount {

    public DebitAccount(int amount, String currency) {
        if (amount < 0) {
            System.out.println("Баланс дебетового счета не может быть меньше 0");
        } else {
            this.amount = amount;
            this.currency = currency;
        }
    }

    @Override
    public void withdrawCash(int amount) {
        if (amount > this.amount) {
            System.out.println("У вас недостаточно средств для снятия суммы " + amount + " " + currency);
        } else {
            this.amount -= amount;
            System.out.println("Вы сняли " + amount + " " + currency);
        }
    }

    @Override
    public void showBalance() {
        System.out.println("На вашем счету осталось " + amount + " " + currency);
    }
}

class CreditAccount extends BankAccount {

    public int creditLimit;

    public CreditAccount(int amount, String currency, int creditLimit) {
        this.amount = amount;
        this.currency = currency;
        this.creditLimit = creditLimit;
    }

    @Override
    public void withdrawCash(int amount) {
        if (this.amount - amount < -creditLimit) {
            System.out.println("У вас недостаточно средств для снятия суммы " + amount + " " + currency);
        } else {
            this.amount -= amount;
            System.out.println("Вы сняли " + amount + " " + currency);
        }
    }

    @Override
    public void showBalance() {
        if (amount >= 0) {
            System.out.println("На вашем счету " + amount + " " + currency);
        } else {
            System.out.println("Ваша задолженность по кредитному счету составялет " + Math.abs(amount) + currency);
        }
    }
}

class Bank {

    public BankAccount createNewAccount (BankAccount bankAccount, String currency) {  // создать метод createNewAccount, который принимает на вход СТРОКУУУУУ???? с типом аккаунта и строку с создаваемой валютой
        BankAccount newBankAccount =  new BankAccount(); // создать пустой объект BankAccount()
        if (bankAccount instanceof DebitAccount) { // если тип "debit_account"
            System.out.println ("Ваш дебетовый счет создан"); // вывести сообщение "Ваш дебетовый счет создан"
            newBankAccount = new  DebitAccount(0, currency); // создать дебетовый аккаунт в выбранной валюте и с нулевым балансом
        } else if (bankAccount instanceof CreditAccount) { // если тип "credit_account"
            int limit = calculateCreditLimit(currency); // посчитать кредитный лимит в зависимости от валюты
            System.out.println("Кредитный счет создан. Ваш лимит по счету "+ limit + " " + currency); // вывести сообщение
            newBankAccount = new CreditAccount(0, currency, limit); // создать кредитный аккаунт в выборанной валюты и с посчитанным кредитным лимитом
        } else { // иначе
            System.out.println("Неверно указа тип создаваемого счета"); // вывести сообщение
        }
        return newBankAccount;
    }

    public  void closeAccount (BankAccount bankAccount) { // создать метод closeAccount, который принимает на вход BankAccount
        BankAccount bankAcount = new BankAccount();
        if (bankAccount instanceof DebitAccount) {  // если переданный аккаунт дебетовый

            if (bankAcount.amount == 0) { System.out.println("Ваш дебетовый счет закрыт"); } // если на счету нет денег вывести сообщение
            if (bankAcount.amount > 0) { System.out.println("Ваш дебетовый счет закрыт. Вы можете получить остаток по вашему счету в размере " + bankAcount.amount + " " + bankAcount.currency + " в отделении банка"); }// иначе вывести сообщение

        } else if (bankAccount instanceof CreditAccount) { // если переданный аккаунт кредитный
            if (bankAccount.amount == 0) { System.out.println("Ваш кредитный счет закрыт"); } // если на счету нет денег вывести сообщение
            if (bankAccount.amount > 0) { System.out.println("Ваш кредитный счет закрыт. Вы можете получить остаток по вашему счету в размере " + bankAcount.amount + " " + bankAcount.currency + " в отделении банка"); }  // если на счету положительный баланс вывести сообщение
            if (bankAccount.amount < 0) {System.out.println("Вы не можете закрыть кредитный счет потому как на нем еще есть задолженность. Ваша задолженность по счету составляет " + bankAcount.amount + " " + bankAcount.currency);  }// если на счету отрицательный баланс вывести сообщение
        } else { // если непонятный тип счета
            System.out.println("Пока что мы не можем закрыть данный вид счета");  //вывести сообщение
        }
    }

    private static int calculateCreditLimit(String currency) {
        int limit;
        if (currency == ("RUB")) {
            limit = 100000;
        } else if (currency == ("USD"))  {
            limit = 1250;
        } else if (currency == ("EUR")){
            limit = 1000;
        } else {
            limit = 0;
        }
        return limit;
    }
}
