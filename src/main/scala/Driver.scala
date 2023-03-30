import scala.util.{Try, Success, Failure}

object Driver extends App {
  val paymentProcessor = new PaymentProcessor()

  // Add payment methods
  paymentProcessor.addPaymentMethod(CreditCardPayment("1234567890123456", "Akanksha Singh", "01/23", "123"))
  paymentProcessor.addPaymentMethod(PayPalPayment("Akanksha Singh", "akankshasingh@knoldus.com"))
  paymentProcessor.addPaymentMethod(BankTransferPayment("1234567890", "Akanksha Singh", "ICICI Bank", "123456"))

  // List  payment methods
  println("Available payment methods:")
  paymentProcessor.listPaymentMethods().foreach(println)

  // Process payments
  println("\nProcessing your payments:")
  paymentProcessor.processPayment(100.0, CreditCardPayment("1234567890123456", "Akanksha Singh", "01/23", "123"))
  paymentProcessor.processPayment(500.0, PayPalPayment("Akanksha Singh", "akankshasingh@knoldus.com"))
  paymentProcessor.processPayment(200.0, BankTransferPayment("1234567890", "Akanksha Singh", "ICICI Bank", "123456789"))

  // Verifying payment result
  val paymentResult = paymentProcessor.processPayment(100.0, CreditCardPayment("1234567890123456", "Akanksha Singh", "01/23", "123"))

  paymentResult match {
    case Success(result) => assert(result == true)
    case Failure(exception) => println(s"Payment failed with exception: ${exception.getMessage}")
  }

}



