package model

case class Predmet(nazivPredmeta: String, ocene: Seq[Int])

object Predmet {

  import play.api.libs.json._

  implicit object PredmetWrites extends OWrites[Predmet] {
    override def writes(predmet: Predmet): JsObject = Json.obj(
      "nazivPredmeta" -> predmet.nazivPredmeta,
      "ocene" -> predmet.ocene
    )
  }

  implicit object PredmetReads extends Reads[Predmet] {
    override def reads(json: JsValue):JsResult[Predmet] = json match {
      case obj: JsObject => try {
        val nazivPredmeta = (obj \ "nazivPredmeta").as[String]
        val ocene = (obj \ "ocene").as[Seq[Int]]

        JsSuccess(Predmet(nazivPredmeta, ocene))
      } catch {
        case cause: Throwable => JsError(cause.getMessage)
      }
      case _ => JsError("expected.JsObject")
    }
  }
}
