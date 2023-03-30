import scala.util.{Try, Success, Failure}

trait PaymentMethod {
  def processPayment(amount: Double): Try[Boolean]
}

//CreditCardPayment case class, which implements the PaymentMethod trait and processes credit card payments
case class CreditCardPayment(cardNumber: String, cardHolder: String, expiryDate: String, cvv: String) extends PaymentMethod {
  override def processPayment(amount: Double): Try[Boolean] = {
    if (cardNumber.length != 16 || cvv.length != 3) {
      // If the card number or CVV is invalid, return a Failure with an exception
      Failure(new IllegalArgumentException("Invalid card number or CVV"))
    } else {
      println(s"Processing credit card payment of amount $amount using card number $cardNumber")
      Success(true)
    }
  }
}

//PayPalPayment case class, which implements the PaymentMethod trait and processes PayPal payments
case class PayPalPayment(val name: String, val email: String, var isEnabled: Boolean = true) extends PaymentMethod {
  override def processPayment(amount: Double): Try[Boolean] = {
    if (!isEnabled) {
      // If PayPal is not enabled, return a Failure with an exception
      Failure(new Exception("PayPal payment method is disabled"))
    } else {
      println(s"Processing PayPal payment of amount $amount using email $email")
      Success(true)
    }
  }
}

//BankTransferPayment case class, which implements the PaymentMethod trait and processes bank transfers
case class BankTransferPayment(val accountNumber: String, val accountHolder: String, val bankName: String, val routingNumber: String) extends PaymentMethod {
  override def processPayment(amount: Double): Try[Boolean] = {
    if (accountNumber.length != 10 || routingNumber.length != 9) {
      // If the account number or routing number is invalid, return a Failure with an exception
      Failure(new IllegalArgumentException("Invalid account number or routing number"))
    } else {
      println(s"Processing bank transfer payment of amount $amount to account number $accountNumber")
      Success(true)
    }
  }
}

//PaymentProcessor class, which manages a list of payment methods and provides methods to add payment methods, process payments, and list payment methods
class PaymentProcessor {
  // A list to hold all the available payment methods
  private var paymentMethods = List.empty[PaymentMethod]

  // Method to add a new payment method to the list
  def addPaymentMethod(paymentMethod: PaymentMethod): Unit = {
    paymentMethods = paymentMethod :: paymentMethods
  }

  // Method to process payment using the specified payment method
  def processPayment(amount: Double, paymentMethod: PaymentMethod): Try[Boolean] = {
    paymentMethod.processPayment(amount)
  }

  // Method to list all the available payment methods
  def listPaymentMethods(): List[PaymentMethod] = {
    paymentMethods
  }

}
