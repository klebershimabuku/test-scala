package locaweb.payments

trait PaymentService {
  def createPayment(p: Payment): Unit
}

object PaymentService {
  def apply(): PaymentService = {
    new PaymentServiceImpl()
  }
}

class PaymentServiceImpl extends PaymentService {
  def createPayment(p: Payment): Unit = {
    if (p.customerId != 42) {
      throw new RuntimeException(s"Failed to process payment for customer ${p.customerId}")
    }
    println(s"Received a payment of ${p.amount} for customer ${p.customerId}")
    // seems legit!
  }
}

