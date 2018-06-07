package model

case class LicniPodaci(jmbg: Int,
                       ime: String,
                       prezime: String,
                       datumRodjenja: String,
                       adresa: Option[String],
                       brojTelefona: Option[String],
                       roditelji: Seq[Roditelj])

object LicniPodaci {

  import play.api.libs.json._

  implicit object LicniPodaciWrites extends OWrites[LicniPodaci] {
    override def writes(lp: LicniPodaci): JsObject = Json.obj(
      "jmbg" -> lp.jmbg,
      "ime" -> lp.ime,
      "prezime" -> lp.prezime,
      "datumRodjenja" -> lp.datumRodjenja,
      "adresa" -> lp.adresa,
      "brojTelefona" -> lp.brojTelefona,
      "roditelji" -> lp.roditelji
    )
  }

  implicit object LicniPodaciReads extends Reads[LicniPodaci] {
    override def reads(json: JsValue): JsResult[LicniPodaci] = json match {
      case obj: JsObject => try {
        val jmbg = (obj \ "jmbg").as[Int]
        val ime = (obj \ "ime").as[String]
        val prezime = (obj \ "prezime").as[String]
        val datumRodjenja = (obj \ "datumRodjenja").as[String]
        val adresa = (obj \ "adresa").asOpt[String]
        val brojTelefona = (obj \ "brojTelefona").asOpt[String]
        val roditelji = (obj \ "roditelji").as[Seq[Roditelj]]

        JsSuccess(LicniPodaci(jmbg, ime, prezime, datumRodjenja, adresa, brojTelefona, roditelji))
      } catch {
        case cause: Throwable => JsError(cause.getMessage)
      }
      case _ => JsError("expected.JsObject")
    }
  }
}



