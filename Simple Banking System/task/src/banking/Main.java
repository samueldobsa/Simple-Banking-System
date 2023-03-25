package banking;

import java.sql.*;
import java.util.*;
import static banking.Main.*;


/**
     * Class Main
     * extends the "CustomerMenu" class and launches the program in the main method with the inherited "launchMainMenu" method.
     *
     * Class CustomerMenu
     * has a field "authorizedCard", which stores an object of class "card" with the data of the authorized user.
     * And 3 methods for interacting with the user.
     *
     * Class Card
     * has 3 fields (and getters, setters, constructor) that store data about the number, pin-code and card balance.
     *
     * Class CardsManagementSystem
     * is one of the most important because it performs all the basic operations of a program.
     * It extends the Random class and has one constant field - an array of objects of the "Card" class (List<Card> CARDS).
     * And methods for filling fields in the Card class
     */

    class Main extends CustomerMenu {
        public static Scanner scanner = new Scanner(System.in);
        public static boolean state = true;
        public static boolean state2 = true;
        static Connection con = null;

    public static void main(String[] args) throws SQLException {

            con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\dobsa\\IdeaProjects\\Simple Banking System\\Simple Banking System\\task\\card.s3db");

            while (state) {
                System.out.println("\n1. Create an account\n2. Log into account\n0. Exit");
                switch (scanner.nextInt()) {
                    case 1:
                        Card card = addCard();
                        String sql = "INSERT INTO card(number, pin, balance) VALUES(?,?,?)";
                        PreparedStatement pstmt = null;
                        try {
                            pstmt = con.prepareStatement(sql);
                            pstmt.setString(1, card.getNumber());
                            pstmt.setString(2, card.getPin());
                            pstmt.setLong(3, card.getBalance());
                            pstmt.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            if (pstmt != null) {
                                try {
                                    pstmt.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        System.out.println("Your card has been created");
                        System.out.println("Your card number:");
                        System.out.println(getCard().getNumber());
                        System.out.println("Your card PIN:");
                        System.out.println(getCard().getPin());
                        break;
                    case 2:
                        CustomerMenu.launchLoginMenu();
                        break;
                    case 0:
                        System.out.println("Bey!");
                        exit();
                }
            }

        }
    }

    class CustomerMenu extends CardsManagementSystem {

        static Card authorizedCard;

        static void launchLoginMenu() {
            if (authorizedCard == null) {
                System.out.println("\nEnter your card number:");
                String enteredNumber = scanner.next();
                scanner.nextLine();

                System.out.println("Enter your PIN:");
                String enteredPin = scanner.nextLine();

                authorizedCard = getCard(enteredNumber, enteredPin);
            }
                if (authorizedCard != null && checkCardExistence(authorizedCard.getNumber())) {
                    System.out.println("\nYou have successfully logged in!");
                    launchAccountMenu();
                    state2 = true;
                } else {
                    System.out.println("\nWrong card number or PIN!");
                    authorizedCard = null;
                }
        }

        static void launchAccountMenu() {
            while (state2) {
                System.out.println("\n1. Balance\n2. Add income\n3. Do transfer\n4. Close account\n5. Log out\n0. Exit");
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println("\nBalance: " + updatedBalance(authorizedCard));
                        launchAccountMenu();
                        break;
                    case 2:
                        System.out.println("\nAdd income");
                        long amount = scanner.nextLong();
                        scanner.nextLine();
                        addIncome(authorizedCard, amount);
                        launchAccountMenu();
                        break;
                    case 3:
                        System.out.println("\nDo transfer");
                        System.out.println("Enter card number:");
                        String receiverCardNumber = scanner.nextLine();

                        if (checkLuhnAlgorithm(receiverCardNumber)){
                            if (authorizedCard.getNumber().equals(receiverCardNumber)){
                                System.out.println("You can't transfer money to the same account!");
                                launchAccountMenu();
                            } else if (checkCardExistence(receiverCardNumber) == false){
                                System.out.println("Such a card does not exist.");
                                launchAccountMenu();
                                break;
                            } else if (checkCardExistence(receiverCardNumber) == true){
                                System.out.println("Enter how much money you want to transfer:");
                                long money = scanner.nextLong();
                                if (money <= updatedBalance(authorizedCard)){
                                    System.out.println("Success!");
                                    doTransfer(authorizedCard, receiverCardNumber, money);
                                    launchAccountMenu();
                                }else {
                                    System.out.println("Not enough money!");
                                    launchAccountMenu();
                                }
                            }
                        }else {
                            System.out.println("Probably you made a mistake in the card number. Please try again!");
                            launchAccountMenu();
                            break;
                        }
                        break;
                    case 4:
                        deleteAccount(authorizedCard.getNumber());
                        authorizedCard = null;
                        state2 = false;
                        break;
                    case 5:
                        System.out.println("\nYou have successfully logged out!");
                        authorizedCard = null;
                        state2 = false;
                        break;
                    case 0:
                        System.out.println("Bey!");
                        exit();
                }
            }
        }

        static void addIncome(Card authorizedCard, long amount) {
            // code to deposit money to the account
            String sql = "UPDATE card SET balance = balance + ? WHERE number = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = con.prepareStatement(sql);
                pstmt.setLong(1, amount);
                pstmt.setString(2, authorizedCard.getNumber());
                pstmt.executeUpdate();
                System.out.println("Income added!");
            } catch (SQLException e) {
                System.out.println("Error in adding income.");
                e.printStackTrace();
            } finally {
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        static Long updatedBalance(Card authorizedCard) {
            String sql = "SELECT balance FROM card WHERE number = ?";
            ResultSet resultSet = null;
            try {
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, authorizedCard.getNumber());
                resultSet = pstmt.executeQuery();
                Long valueBalance = resultSet.getLong(1);
                return valueBalance;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        static void doTransfer(Card authorizedCard, String receiverCardNumber, long amount) {
            // code to make the transaction
            String sql = "UPDATE card SET balance = balance - ? WHERE number = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = con.prepareStatement(sql);
                pstmt.setLong(1, amount);
                pstmt.setString(2, authorizedCard.getNumber());
                pstmt.executeUpdate();
                sql = "UPDATE card SET balance = balance + ? WHERE number = ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setLong(1, amount);
                pstmt.setString(2, receiverCardNumber);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        static boolean checkCardExistence(String cardNumber) {
            // code to check if the card number exists in the database
            String sql = "SELECT number FROM card WHERE number = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, cardNumber);
                return pstmt.executeQuery().next();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        static boolean checkLuhnAlgorithm(String cardNumber) {
            // code to check if the card number passes the Luhn algorithm
            int sum = 0;
            boolean alternate = false;
            for (int i = cardNumber.length() - 1; i >= 0; i--) {
                int n = Integer.parseInt(cardNumber.substring(i, i + 1));
                if (alternate) {
                    n *= 2;
                    if (n > 9) {
                        n = (n % 10) + 1;
                    }
                }
                sum += n;
                alternate = !alternate;
            }
            return (sum % 10 == 0);
        }

        static void deleteAccount(String cardNumber) {
            String sql = "DELETE FROM card WHERE number = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, cardNumber);
                pstmt.executeUpdate();
                System.out.println("The account has been closed!");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        static void exit(){
            System.exit(0);
        }
    }


    class Card extends CardsManagementSystem {

        private String number;
        private String pin;
        private long balance;

        Card(long balance) {
            this.pin = generateRandomNumber(4);
            this.balance = balance;
        }

        String getNumber() {
            return this.number;
        }

        void setNUMBER() {
            this.number = addChecksum(new StringBuilder("400000").append(generateRandomNumber(9)));
        }

        String getPin() {
            return this.pin;
        }

        long getBalance() {
            return balance;
        }

    }

    class CardsManagementSystem extends Random {

        private static final List<Card> CARDS = new ArrayList<>();
        /**
         * Generates a sequence of a given length of random digits. Used to generate pin and card account ID
         * @param length specified sequence length
         * @return a sequence of random digits of type String
         */
        String generateRandomNumber(int length) {
            StringBuilder randomNumber = new StringBuilder();
            for (int i = 0; i < length; i++) {
                randomNumber.append(nextInt(10));
            }
            return String.valueOf(randomNumber);
        }
        /**
         * Adds a lunar check digit to the end of the resulting sequence of digits. Used to create a card number
         * @param numberWithoutChecksum a sequence of numbers of type StringBuilder
         * @return a sequence digits of type String + checksum
         */
        String addChecksum(StringBuilder numberWithoutChecksum) {
            int checksum = 0;
            for (int i = 0; i < numberWithoutChecksum.length(); i++) {
                int digit = Integer.parseInt(String.valueOf(numberWithoutChecksum.charAt(i)));
                digit = i % 2 == 0 ? digit * 2 : digit;
                checksum += digit > 9 ? digit - 9 : digit;
            }
            checksum = 10 - checksum % 10 == 10 ? 0 : 10 - checksum % 10;
            return String.valueOf(numberWithoutChecksum.append(checksum));
        }
        /**
         * Returns the card found by number and pin-code. Used to authorize the cardholder
         * @param enteredNumber user-entered card number
         * @param enteredPin user-entered card pin-code
         * @return an object of class Card or null
         */
        static Card getCard(String enteredNumber, String enteredPin) {
            for (Card card: CARDS) {
                if (card.getNumber().equals(enteredNumber) && card.getPin().equals(enteredPin)) {
                    return card;
                }
            }
            return null;
        }
        /**
         * Overloading the getCard method without parameters. Used to print the generated new card
         * @return the last added object of class Card from the array
         */
        static Card getCard() {
            return CARDS.get(CARDS.toArray().length - 1);
        }
        /**
         * Used to create a new card
         * If the generated card number does not match the card numbers in the CARDS array, add it to this array
         */
        static Card addCard() {
            Card newCard = new Card(0);
            do {
                newCard.setNUMBER();
            } while (CARDS.stream().anyMatch(card -> card.getNumber().equals(newCard.getNumber())));
            CARDS.add(newCard);
            return newCard;
        }
    }
