package AtmMachine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AtmMachine {
    public static void main(String[] args) {

        Scanner code = new Scanner(System.in);
        double balance = 100.0;

        while (true){
            System.out.println("Enter the code number: ");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit Atm");

            int choice  = code.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Your Balance: " + balance + "$");
                    choice = code.nextInt();
                case 2:
                    System.out.println("Deposite Your Amount: ");
                    double deposit = code.nextDouble();
                    try {
                        if (deposit == 0 || deposit <= -1 ) {
                            System.out.println("Enter Your valid deposit amount: ");
                            choice = code.nextInt();
                        }
                        balance += deposit;
                        System.out.println("$" + balance);
                        choice = code.nextInt();
                    }
                    catch (InputMismatchException e){
                        System.out.println("Enter the valid input number");
                    }
                    catch (NumberFormatException nfe){
                        System.out.println("Enter the valid input number");
//                        choice = code.nextInt();
                    }
                    System.out.println("Enter the code number: ");
                    choice = code.nextInt();
                case 3:
                    System.out.println("Withdraw your balance: ");
                    double withdraw = code.nextDouble();
                    if(withdraw < balance){
                        balance -= withdraw;
                        System.out.println("$" + balance);
                    }
                    else {
                        System.out.println("not sufficient balance");
                        break;
                    }

                    choice = code.nextInt();
                case 4:
                    System.out.println("Thank you for visiting our Atm ");
                    break;
                default:
                    System.out.println("System is not working properly");
                    System.out.println("Try again");
                    break;

            }

        }

    }
}
