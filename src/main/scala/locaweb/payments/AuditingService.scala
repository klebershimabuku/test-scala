package locaweb.payments

trait AuditingService {
  def audit(user: User, message: String): Unit
}

object AuditingService {
  def apply(): AuditingService = {
    new AuditingServiceImpl()
  }
}

class AuditingServiceImpl extends AuditingService {
  override def audit(user: User, message: String) = {}
}
