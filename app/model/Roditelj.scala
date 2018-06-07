package model

import play.api.libs.json._

case class Roditelj(ime: String, prezime: String, zanimanje: Option[String])

object Roditelj {

  implicit object RoditeljWrites extends OWrites[Roditelj] {

    def writes(roditelj: Roditelj): JsObject = Json.obj(
      "ime" -> roditelj.ime,
      "prezime" -> roditelj.prezime,
      "zanimanje" -> roditelj.zanimanje
    )
  }

  implicit object RoditeljReads extends Reads[Roditelj] {
    def reads(json: JsValue): JsResult[Roditelj] = json match {
      case obj: JsObject => try {
        val ime = (obj \ "ime").as[String]
        val prezime = (obj \ "prezime").as[String]
        val zanimanje = (obj \ "zanimanje").asOpt[String]

        JsSuccess(Roditelj(ime, prezime, zanimanje))
      } catch {
        case cause: Throwable => JsError(cause.getMessage)
      }
      case _ => JsError("expected.JsObject")
    }
  }
}