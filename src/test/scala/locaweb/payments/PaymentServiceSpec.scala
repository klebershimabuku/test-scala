package locaweb.payments

import org.scalatest.{BeforeAndAfterEach,FunSpec}
import org.scalatest.matchers.ShouldMatchers

class PaymentServiceSpec extends FunSpec with ShouldMatchers {
  val service = new PaymentServiceImpl()

  describe("#createPayment") {
    describe("when customer id is 42") {
      val not42Customer = Payment(9.99, 42)

      it("should not fail") {
        service.createPayment(not42Customer)
      }
    }

    describe("when customer id is not 42") {
      val not42Customer = Payment(9.99, 47)

      it("should fail") {
        intercept[RuntimeException] { service.createPayment(not42Customer) }
      }
    }
  }
}
