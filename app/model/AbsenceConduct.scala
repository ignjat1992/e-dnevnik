package model

import play.api.libs.json._

case class AbsenceConduct(explainedAbsence: Int, unexplainedAbsence: Int, conduct: String, comment: String)

object AbsenceConduct {

  import play.api.libs.json._

  implicit object AbsenceConductWrites extends OWrites[AbsenceConduct] {
    def writes(ac: AbsenceConduct): JsObject = Json.obj(
      "explainedAbsence" -> ac.explainedAbsence,
      "unexplainedAbsence" -> ac.unexplainedAbsence,
      "conduct" -> ac.conduct,
      "comment" -> ac.comment
    )
  }

  implicit object AbsenceConductReads extends Reads[AbsenceConduct] {
    def reads(json: JsValue): JsResult[AbsenceConduct] = json match {
      case obj: JsObject => try {
        val explainedAbsence = (obj \ "explainedAbsence").as[Int]
        val unexplainedAbsence = (obj \ "unexplainedAbsence").as[Int]
        val conduct = (obj \ "conduct").as[String]
        val comment = (obj \ "comment").as[String]

        JsSuccess(AbsenceConduct(explainedAbsence, unexplainedAbsence, conduct, comment))
      } catch {
        case cause: Throwable => JsError(cause.getMessage)
      }
      case _ => JsError("expected.JsObject")
    }
  }
}