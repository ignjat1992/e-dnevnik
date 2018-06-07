package model

import play.api.libs.json._

case class IzostanciVladanje(brojOpravdanih: Int, brojNeopravdanih: Int, vladanje: String, komentar: String)

object IzostanciVladanje {

  import play.api.libs.json._

  implicit object IzostanciVladanjeWrites extends OWrites[IzostanciVladanje] {
    def writes(iv: IzostanciVladanje): JsObject = Json.obj(
      "brojOpravdanih" -> iv.brojOpravdanih,
      "brojNeopravdanih" -> iv.brojNeopravdanih,
      "vladanje" -> iv.vladanje,
      "komentar" -> iv.komentar
    )
  }

  implicit object IzostanciVladanjeReads extends Reads[IzostanciVladanje] {
    def reads(json: JsValue): JsResult[IzostanciVladanje] = json match {
      case obj: JsObject => try {
        val brojOpravdanih = (obj \ "brojOpravdanih").as[Int]
        val brojNeopravdanih = (obj \ "brojNeopravdanih").as[Int]
        val vladanje = (obj \ "vladanje").as[String]
        val komentar = (obj \ "komentar").as[String]

        JsSuccess(IzostanciVladanje(brojOpravdanih, brojNeopravdanih, vladanje, komentar))
      } catch {
        case cause: Throwable => JsError(cause.getMessage)
      }
      case _ => JsError("expected.JsObject")
    }
  }
}