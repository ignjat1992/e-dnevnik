package controllers

import javax.inject.Inject
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import storage.MongoApi
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import model.Student._

class StudentController @Inject()(val mongoApi: MongoApi, cc: ControllerComponents) extends
  AbstractController(cc) with I18nSupport {

  def index() = Action.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok(views.html.index()))
  }

  def students() = Action.async { implicit request: Request[AnyContent] =>
    val found = mongoApi.getAllStudents()

    found.map { students =>
      Ok(views.html.student(students))
    }.recover {
      case e =>
        e.printStackTrace()
        BadRequest(e.getMessage)
    }
  }

  def showCreatingForm() = Action.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok(views.html.editCreate()))
  }
}
