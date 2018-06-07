package model

import play.api.libs.json._

case class PersonalData(jmbg: Int,
                        firstName: String,
                        lastName: String,
                        dateOfBirth: String,
                        address: Option[String],
                        phoneNumber: Option[String],
                        parents: Seq[Parent])

object PersonalData {

  import play.api.libs.json._

  implicit object PersonalDataWrites extends OWrites[PersonalData] {
    def writes(pd: PersonalData): JsObject = Json.obj(
      "jmbg" -> pd.jmbg,
      "firstName" -> pd.firstName,
      "lastName" -> pd.lastName,
      "dateOfBirth" -> pd.dateOfBirth,
      "address" -> pd.address,
      "phoneNumber" -> pd.phoneNumber,
      "parents" -> pd.parents
    )
  }

  implicit object PersonalDataReads extends Reads[PersonalData] {
    def reads(json: JsValue): JsResult[PersonalData] = json match {
      case obj: JsObject => try {
        val jmbg = (obj \ "jmbg").as[Int]
        val firstName = (obj \ "firstName").as[String]
        val lastName = (obj \ "lastName").as[String]
        val dateOfBirth = (obj \ "dateOfBirth").as[String]
        val address = (obj \ "address").asOpt[String]
        val phoneNumber = (obj \ "phoneNumber").asOpt[String]
        val parents = (obj \ "parents").as[Seq[Parent]]

        JsSuccess(PersonalData(jmbg, firstName, lastName, dateOfBirth, address, phoneNumber, parents))
      } catch {
        case cause: Throwable => JsError(cause.getMessage)
      }
      case _ => JsError("expected.JsObject")
    }
  }
}



