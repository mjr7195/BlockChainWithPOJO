public class CreditCard {
    String cardHoldername;
    long creditCardNum;
    String exDate;
    int secCode;

    CreditCard(String cardHoldername, long creditCardNum, String exDate, int secCode) {
        this.cardHoldername = cardHoldername;
        this.creditCardNum = creditCardNum;
        this.exDate = exDate;
        this.secCode = secCode;
    }
}
