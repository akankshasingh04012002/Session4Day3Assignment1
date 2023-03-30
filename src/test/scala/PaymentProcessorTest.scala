import org.scalatest.flatspec.AnyFlatSpec
import scala.util.{Try, Success, Failure}

class PaymentProcessorTest extends AnyFlatSpec {

  val paymentProcessor = new PaymentProcessor

  "PaymentProcessor" should "add payment methods and list them" in {
    assert(paymentProcessor.listPaymentMethods().isEmpty)
    paymentProcessor.addPaymentMethod(CreditCardPayment("1234567890123456", "Akanksha Singh", "01/25", "123"))
    paymentProcessor.addPaymentMethod(PayPalPayment("Akanksha Singh", "akankshasingh@knoldus.com"))
    paymentProcessor.addPaymentMethod(BankTransferPayment("0123456789", "Akanksha Singh", "ICICI Bank", "123456789"))

  }

  it should "process payments using the selected payment method" in {
    assert(paymentProcessor.processPayment(10000.0, CreditCardPayment("1234567890123456", "Akanksha Singh", "01/25", "123")).isSuccess)
    assert(paymentProcessor.processPayment(2000.0, PayPalPayment("Akanksha Singh", "akankshasingh@knoldus.com")).isSuccess)
    assert(paymentProcessor.processPayment(5000.0, BankTransferPayment("0123456789", "Akanksha Singh", "ICICI Bank", "123456789")).isSuccess)
  }
}
