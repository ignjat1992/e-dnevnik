package model

import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json._

case class Student(id: String,
                   `class`: String,
                   personalData: PersonalData,
                   courses: Seq[Course],
                   absenceConduct: AbsenceConduct)

object Student {

  val form: Form[Student] = Form (
    mapping(
      "_id" -> text,
      "class" -> text,
      "personalData" -> mapping(
        "jmbg" -> number,
        "firstName" -> text,
        "lastName" -> text,
        "dateOfBirth" -> text,
        "address" -> optional(text),
        "phoneNumber" -> optional(text),
        "parents" -> seq(mapping(
          "firstName" -> text,
          "lastName" -> text,
          "interest" -> optional(text)
        )(Parent.apply)(Parent.unapply))
      )(PersonalData.apply)(PersonalData.unapply),
      "courses" -> seq(mapping(
        "courseName" -> text,
        "marks" -> seq(number)
      )(Course.apply)(Course.unapply)),
      "absenceConduct" -> mapping(
        "explainedAbsence" -> number,
        "unexplainedAbsence" -> number,
        "conduct" -> text,
        "comment" -> text
      )(AbsenceConduct.apply)(AbsenceConduct.unapply)
    )(Student.apply)(Student.unapply)
  )

  implicit object StudentWrites extends OWrites[Student] {
    def writes(student: Student): JsObject = Json.obj(
      "_id" -> student.id,
      "class" -> student.`class`,
      "personalData" -> student.personalData,
      "courses" -> student.courses,
      "absenceConduct" -> student.absenceConduct
    )
  }

  implicit object StudentReads extends Reads[Student] {
    def reads(json: JsValue): JsResult[Student] = json match {
      case obj: JsObject => try {
        val id = (obj \ "_id").as[String]
        val `class` = (obj \ "class").as[String]
        val personalData = (obj \ "personalData").as[PersonalData]
        val courses = (obj \ "courses").as[Seq[Course]]
        val absenceConduct = (obj \ "absenceConduct").as[AbsenceConduct]

        JsSuccess(Student(id, `class`, personalData, courses, absenceConduct))

      } catch {
        case cause: Throwable => JsError(cause.getMessage)
      }
      case _ => JsError("expected.JsObject")
    }
  }
}
