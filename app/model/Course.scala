package model

import play.api.libs.json._

case class Course(courseName: String, marks: Seq[Int])

object Course {

  import play.api.libs.json._

  implicit object CourseWrites extends OWrites[Course] {
    def writes(course: Course): JsObject = Json.obj(
      "courseName" -> course.courseName,
      "marks" -> course.marks
    )
  }

  implicit object CourseReads extends Reads[Course] {
    def reads(json: JsValue):JsResult[Course] = json match {
      case obj: JsObject => try {
        val courseName = (obj \ "courseName").as[String]
        val marks = (obj \ "marks").as[Seq[Int]]

        JsSuccess(Course(courseName, marks))

      } catch {
        case cause: Throwable => JsError(cause.getMessage)
      }
      case _ => JsError("expected.JsObject")
    }
  }
}
