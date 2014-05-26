package locaweb.payments

import org.slf4j.LoggerFactory

trait Loggable {
  lazy val logger = LoggerFactory.getLogger(getClass)
}
