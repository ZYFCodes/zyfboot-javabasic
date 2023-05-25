package org.zyf.javabasic.designpatterns.state.statemachine;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/5/24  23:59
 */
public class CoffeeVendingMachineTest {
    public static void main(String[] args) {
        CoffeeVendingMachine vendingMachine = new CoffeeVendingMachine();

        vendingMachine.insertCoin();
        vendingMachine.makeCoffee();
        vendingMachine.dispenseCoffee();
    }
}
