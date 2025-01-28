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
    private int getDefaultLimit(String currency) {
        if(currency.equals("RUB"))
            return 100000;

        if(currency.equals("USD"))
            return 1250;

        if(currency.equals("EUR"))
            return 1000;

        return 0;
    }

    public BankAccount createNewAccount(String type, String currency) {
        if(type.equals("debit_account")) {
            System.out.println("Ваш дебетовый счет создан");
            return new DebitAccount(0, currency);
        }

        int creditLimit = getDefaultLimit(currency);
        if(type.equals("credit_account")) {
            System.out.println("Кредитный счет создан. Ваш лимит по счету " + creditLimit + " " + currency);
            return new CreditAccount(0, currency, creditLimit);
        }

        System.out.println("Неверно указан тип создаваемого счета");
        return new BankAccount();
    }

    // создать метод createNewAccount, который принимает на вход строку с типом аккаунта и строку с создаваемой валютой
    // если тип "debit_account"
    // вывести сообщение "Ваш дебетовый счет создан"
    // создать дебетовый аккаунт в выбранной валюте и с нулевым балансом
    // если тип "credit_account"
    // посчитать кредитный лимит в зависимости от валюты
    // вывести сообщение "Кредитный счет создан. Ваш лимит по счету {limit} {currency}"
    // создать кредитный аккаунт в выборанной валюты и с посчитанным кредитным лимитом
    // иначе
    // вывести сообщение "Неверно указан тип создаваемого счета"
    // создать пустой объект BankAccount()

    public void closeAccount(BankAccount account) {
        if(account instanceof DebitAccount) {
            if(account.amount == 0)
                System.out.println("Ваш дебетовый счет закрыт");
            else {
                System.out.println("Ваш дебетовый счет закрыт. Вы можете получить остаток по вашему счету в размере " + account.amount + " " + account.currency + " в отделении банка");
            }
        } else
        if(account instanceof CreditAccount) {
            if (account.amount == 0)
                System.out.println("Ваш кредитный счет закрыт");
            else if(account.amount > 0)
                System.out.println("Ваш кредитный счет закрыт. Вы можете получить остаток по вашему счету в размере " + account.amount + " " + account.currency + " в отделении банка");
            else
                System.out.println("Вы не можете закрыть кредитный счет потому как на нем еще есть задолженность. Ваша задолженность по счету составляет " + Math.abs(account.amount) + " " + account.currency);
        } else {
            System.out.println("Пока что мы не можем закрыть данный вид счета");
        }
    }
    // создать метод closeAccount, который принимает на вход переменную типа BankAccount
    // если переданный аккаунт дебетовый
    // если на счету нет денег вывести сообщение "Ваш дебетовый счет закрыт"
    // иначе вывести сообщение "Ваш дебетовый счет закрыт. Вы можете получить остаток по вашему счету в размере {amount} {currency} в отделении банка"
    // если переданный аккаунт кредитный
    // если на счету нет денег вывести сообщение "Ваш кредитный счет закрыт"
    // если на счету положительный баланс вывести сообщение "Ваш кредитный счет закрыт. Вы можете получить остаток по вашему счету в размере {amount} {currency} в отделении банка"
    // если на счету отрицательный баланс вывести сообщение "Вы не можете закрыть кредитный счет потому как на нем еще есть задолженность. Ваша задолженность по счету составляет {amount} {currency}"
    // иначе вывести сообщение "Пока что мы не можем закрыть данный вид счета"
}