class BankAccount {

    // создайте переменную int amount. ƒоступ к переменной должен быть только из наследников и классов в текущем пакете
    int amount;
    // создайте переменную String currency. ƒоступ к переменной должен быть только из наследников и классов в текущем пакете
    String currency;
    protected void replenishBalance(int amount) {
        // реализуйте метод. ќн общий дл€ всех наследников
        System.out.println("—чЄт пополнен на " + amount + " " + currency);
        this.amount += amount;
    }

    public void withdrawCash(int amount) {
        this.amount -= amount;
    }

    public void showBalance() {
        System.out.println("–ум-рум");
    }
}

class DebitAccount extends BankAccount /* унаследуйте класс от BankAccount */ {

    public DebitAccount(int amount, String currency) {
        if(amount < 0) {
            System.out.println("Ѕаланс дебетового счета не может быть меньше 0");
            return;
        }

        this.amount = amount;
        this.currency = currency;
        // если amount < 0, вывести сообщение "Ѕаланс дебетового счета не может быть меньше 0"
        // иначе присвоить переменным amount и currency значени€, переданные в конструкторе
    }

    @Override
    public void withdrawCash(int amount) {
        if(this.amount >= amount) {
            this.amount -= amount;
            System.out.println("¬ы сн€ли " + amount + " " + currency);
        } else {
            System.out.println("” вас недостаточно средств дл€ сн€ти€ суммы " + amount  + " " + currency);
        }
        // если на счету достаточно денег
        // вычесть amount из amount класса
        // вывести сообщение "¬ы сн€ли {amount} {currency}"
        // иначе вывести сообщение "” вас недостаточно средств дл€ сн€ти€ суммы {amount} {currency}"
    }

    @Override
    public void showBalance() {
        System.out.println("Ќа вашем счету осталось " + amount  + " " + currency);
        // вывести сообщение "Ќа вашем счету осталось {amount} {currency}"
    }
}

class CreditAccount extends BankAccount {
    private int creditLimit;
    public CreditAccount(int amount, String currency, int creditLimit) {
        this.amount = amount;
        this.currency = currency;
        this.creditLimit = creditLimit;
    }

    @Override
    public void withdrawCash(int amount) {
        if(this.amount + this.creditLimit >= amount) {
            System.out.println("¬ы сн€ли " + amount  + " " + currency);
            this.amount -= amount;
        } else {
            System.out.println("” вас недостаточно средств дл€ сн€ти€ суммы " + amount  + " " + currency);
        }
    }

    @Override
    public void showBalance() {
        if(amount >=0)
            System.out.println("Ќа вашем счету осталось " + amount  + " " + currency);
        else
            System.out.println("«адолженность по кредитному счЄту составл€ет " + Math.abs(amount) + " " + currency);
    }
}

// создайте класс CreditAccount, который будет наследоватьс€ от класса BankAccount

// создайте переменную int creditLimit. ѕеременна€ должна быть доступна только в пределах класса CreditAccount

// создайте конструктор, который принимает на вход переменные (int amount, String currency, int creditLimit) и присваивает их значени€ переменным класса

// переопределите метод withdrawCash(int amount)
// если после сн€ти€ наличных будет превышен кредитный лимит
// выведите сообщение "” вас недостаточно средств дл€ сн€ти€ суммы {amount} {currency}"
// иначе уменьшить сумму на счЄте и вывести сообщение "¬ы сн€ли {amount} {currency}"

// переопределите метод showBalance()
// если на счету положительна€ сумма, то выведите сообщение "Ќа вашем счету осталось {amount} {currency}",
// иначе "«адолженность по кредитному счЄту составл€ет {amount} {currency}"