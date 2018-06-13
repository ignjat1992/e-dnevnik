package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

import scala.concurrent.Future

class SchoolController @Inject() (cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok(views.html.index()))
  }

}
