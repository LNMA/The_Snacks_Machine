package com.progressoft.induction;

import java.math.BigDecimal;

public class Money {
    private BigDecimal moneyAmount;
    public static final BigDecimal ZERO = BigDecimal.ZERO;
    public static final Money QUARTER_DINAR = new Money(BigDecimal.valueOf(0.25));
    public static final Money HALF_DINAR = new Money(BigDecimal.valueOf(0.5));
    public static final Money DINAR = new Money(BigDecimal.ONE);

    public Money(BigDecimal moneyAmount) {
        if (moneyAmount.compareTo(BigDecimal.valueOf(0)) < 0){
            throw new IllegalArgumentException("Money must be positive number");
        }else {
            this.moneyAmount = moneyAmount;
        }
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Money add(Money money){
        this.setMoneyAmount(this.moneyAmount.add(money.getMoneyAmount()));
        return this;
    }

    public boolean isLessThan(Money money){
        if (money != null) {
            int compareResult = this.moneyAmount.compareTo(money.getMoneyAmount());
            return compareResult < 0;
        }else {
            return false;
        }
    }

    public Money subtract(Money money){
        if (this.moneyAmount.compareTo(money.getMoneyAmount()) >= 0){
            this.setMoneyAmount(this.moneyAmount.subtract(money.getMoneyAmount()));
            return this;
        }else {
            throw new IllegalArgumentException("You cannot subtract large number from small number.");
        }
    }

    @Override
    public String toString() {
        return "Money{" +
                "moneyAmount=" + moneyAmount +
                '}';
    }
}
