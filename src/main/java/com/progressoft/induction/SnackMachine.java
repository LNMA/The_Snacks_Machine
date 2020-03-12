package com.progressoft.induction;

import java.math.BigDecimal;

public class SnackMachine {
    private Money money = new Money(BigDecimal.ZERO);
    private Money moneyInTransaction = new Money(BigDecimal.ZERO);
    private Snack chips= null;
    private Snack chewingGums = null;
    private Snack chocolates=null;
    public final static int DEFAULT_QUANTITY = 10;



    public BigDecimal moneyInside(){
        return money.getMoneyAmount();
    }

    public void insertMoney(Money depositedMoney) {
        if (depositedMoney == null) {
            throw new IllegalArgumentException("should not be null.");
        } else {
            float depositedMoneyDouble = depositedMoney.getMoneyAmount().floatValue();
            if (depositedMoneyDouble == 0.25 || depositedMoneyDouble == 0.5 || depositedMoneyDouble == 1.0 ||
                    depositedMoneyDouble == 5.0 || depositedMoneyDouble == 10.0) {
                this.moneyInTransaction.add(depositedMoney);
            } else {
                throw new IllegalArgumentException("please insert only 0.25, 0.5, 1.0, 5.0 , 10.0");
            }
        }
    }

    public Money moneyInTransaction(){
        return moneyInTransaction;
    }

    public Snack chewingGums() {
        if (this.chewingGums == null){
            buildChewingGums();
        }
        return this.chewingGums;
    }

    private void buildChewingGums(){
        this.chewingGums = new Snack();
        this.chewingGums.setAvailableQuantity(DEFAULT_QUANTITY);
        this.chewingGums.setPrice(Money.HALF_DINAR);
    }

    public Snack chips(){
        if (this.chips == null){
            buildChips();
        }
        return chips;
    }

    private void buildChips(){
        this.chips = new Snack();
        this.chips.setAvailableQuantity(DEFAULT_QUANTITY);
        this.chips.setPrice(Money.DINAR);
    }

    public Snack chocolates(){
        if (this.chocolates == null){
            buildChocolates();
        }
        return chocolates;
    }

    private void buildChocolates(){
        this.chocolates = new Snack();
        this.chocolates.setAvailableQuantity(DEFAULT_QUANTITY);
        this.chocolates.setPrice(new Money(BigDecimal.valueOf(2)));
    }


    public Money buySnack(SnackType snackType) {
        switch (snackType) {
            case CHIPS:
                return buySnackAuxiliary(this.chips());

            case CHOCOLATE:
                return buySnackAuxiliary(this.chocolates());

            case CHEWING_GUM:
                return buySnackAuxiliary(this.chewingGums());

            default:
                throw new IllegalStateException("only snack available is chips, chocolates, chewing gums.");
        }
    }

    private Money buySnackAuxiliary(Snack snack) {
        if (snack.quantity() > 0) {
            if (!this.moneyInTransaction.isLessThan(snack.getPrice())) {
                this.moneyInTransaction.subtract(snack.getPrice());
                this.money.add(snack.getPrice());
                snack.setAvailableQuantity(snack.quantity() - 1);
            } else {
                throw new IllegalStateException("Sorry but amount of money you insert it not enough to complete this operation.");
            }
        } else {
            throw new IllegalStateException("Sorry but this snack now is unavailable.");
        }
        return this.moneyInTransaction;
    }

    @Override
    public String toString() {
        return "SnackMachine{" +
                "money=" + money +
                ", moneyInTransaction=" + moneyInTransaction +
                ", chips=" + chips +
                ", chewingGums=" + chewingGums +
                ", chocolates=" + chocolates +
                '}';
    }
}
