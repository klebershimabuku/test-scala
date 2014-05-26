package locaweb.payments

import javax.servlet.ServletContext

import akka.actor.ActorSystem

import org.json4s._
import org.scalatra._

trait ErrorHandler { this: ScalatraBase =>
  error {
    case e: Throwable => {
      halt(500, "Internal server error")
    }
  }
}

trait Dependencies {
  lazy val paymentService: PaymentService = PaymentService()
  lazy val auditingService: AuditingService = AuditingService()
  lazy val actorSystem: ActorSystem = ActorSystem("payments")
}

trait UserStub {
  lazy val currentUser = User("payments-worker")
}

class PaymentsController extends ScalatraServlet with UserStub with ErrorHandler {
  post("/") {
    // call paymentService.createPayment
    // audit asynchonously
  }
}

class ScalatraBootstrap extends LifeCycle with Dependencies {
  override def init(context: ServletContext) {
    context.mount(new PaymentsController, "/payments/*")
  }
}
