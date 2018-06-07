package model

import play.api.libs.json._

case class Ucenik(id: String,
                  razred: String,
                  licniPodaci: LicniPodaci,
                  predmeti: Seq[Predmet],
                  vladanje: IzostanciVladanje)

object Ucenik {

  implicit object UcenikWrites extends OWrites[Ucenik] {
    def writes(ucenik: Ucenik): JsObject = Json.obj(
      "_id" -> ucenik.id,
      "razred" -> ucenik.razred,
      "licniPodaci" -> ucenik.licniPodaci,
      "predmeti" -> ucenik.predmeti,
      "vladanje" -> ucenik.vladanje
    )
  }

  implicit object UcenikReads extends Reads[Ucenik] {
    def reads(json: JsValue): JsResult[Ucenik] = json match {
      case obj: JsObject => try {
        val id = (obj \ "_id").as[String]
        val razred = (obj \ "razred").as[String]
        val licniPodaci = (obj \ "licniPodaci").as[LicniPodaci]
        val predmeti = (obj \ "predmeti").as[Seq[Predmet]]
        val vladanje = (obj \ "vladanje").as[IzostanciVladanje]

        JsSuccess(Ucenik(id, razred, licniPodaci, predmeti, vladanje))

      } catch {
        case cause: Throwable => JsError(cause.getMessage)
      }
      case _ => JsError("expected.JsObject")
    }
  }
}
