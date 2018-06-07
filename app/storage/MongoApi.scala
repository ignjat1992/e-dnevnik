package storage

import javax.inject.{Inject, Singleton}
import model.Ucenik
import play.modules.reactivemongo.ReactiveMongoApi
import play.modules.reactivemongo.json._
import reactivemongo.api.Cursor
import reactivemongo.play.json.collection._
import play.api.libs.json._

import scala.concurrent.Future

@Singleton
class MongoApi @Inject()(val reactiveMongoApi: ReactiveMongoApi) {

  import scala.concurrent.ExecutionContext.Implicits.global

  val myDb = reactiveMongoApi.database
  val ucenici: Future[JSONCollection] = myDb.map(_.collection("ucenici"))

  def vratiSveUcenike() = {
    ucenici.flatMap(_.find(Json.obj())
      .cursor[Ucenik]()
      .collect[Seq](-1, Cursor.FailOnError[Seq[Ucenik]]()))
  }

  def snimiUcenika(ucenik: Ucenik) = {
    ucenici.flatMap(_.insert(ucenik))
  }

  def vratiJednogUcenika(id: String) = {
    val select = Json.obj("_id" -> id)

    ucenici.flatMap(_.find(select).one[Ucenik])
  }
}
