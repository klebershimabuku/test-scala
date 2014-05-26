package locaweb.payments

import org.json4s._

import org.scalatra.ScalatraBase
import org.scalatra.json.JacksonJsonSupport

trait JsonSupport extends JacksonJsonSupport { this: ScalatraBase =>
  protected implicit val jsonFormats: Formats = org.json4s.DefaultFormats

  before() {
    contentType = formats("json")
  }
}

