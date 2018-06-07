package controllers

import javax.inject.Inject
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import storage.MongoApi

class UcenikController @Inject()(val mongoApi: MongoApi, cc: ControllerComponents) extends
  AbstractController(cc) with I18nSupport {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
}
