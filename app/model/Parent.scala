package model

import play.api.libs.json._

case class Parent(firstName: String, lastName: String, interest: Option[String])

object Parent {

  implicit object ParentWrites extends OWrites[Parent] {

    def writes(parent: Parent): JsObject = Json.obj(
      "firstName" -> parent.firstName,
      "lastName" -> parent.lastName,
      "interest" -> parent.interest
    )
  }

  implicit object ParentReads extends Reads[Parent] {
    def reads(json: JsValue): JsResult[Parent] = json match {
      case obj: JsObject => try {
        val firstName = (obj \ "firstName").as[String]
        val lastName = (obj \ "lastName").as[String]
        val interest = (obj \ "interest").asOpt[String]

        JsSuccess(Parent(firstName, lastName, interest))
      } catch {
        case cause: Throwable => JsError(cause.getMessage)
      }
      case _ => JsError("expected.JsObject")
    }
  }
}