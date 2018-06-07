package storage

import javax.inject.{Inject, Singleton}
import model.Student
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
  val students: Future[JSONCollection] = myDb.map(_.collection("students"))

  def getAllStudents() = {
    students.flatMap(_.find(Json.obj())
      .cursor[Student]()
      .collect[Seq](-1, Cursor.FailOnError[Seq[Student]]()))
  }

  def createStudent(student: Student) = {
    students.flatMap(_.insert(student))
  }

  def getStudentById(id: String) = {
    val select = Json.obj("_id" -> id)

    students.flatMap(_.find(select).one[Student])
  }
}
