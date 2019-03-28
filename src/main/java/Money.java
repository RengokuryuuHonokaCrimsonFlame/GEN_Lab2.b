class Money implements Expression {

    protected int amount;

    protected String currency;

    Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    Money times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }

    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(currency, to);
        return new Money(amount / rate, to);
    }

    String currency() {
        return currency;
    }

    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount && currency().equals(money.currency());
    }

    Expression plus(Money addend) {
        return new Sum(this, addend);
    }

    static Money dollar(int amount) {

        return new Money(amount, "USD");
    }

    static Money franc(int amount) {

        return new Money(amount, "CHF");
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}
