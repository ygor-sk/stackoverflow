package sk.ygor.stackoverflow.q53218130

import akka.stream.Materializer
import akka.util.ByteString
import org.scalatestplus.play._
import play.api.http.Status
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.streams.Accumulator
import play.api.mvc._
import play.api.test.Helpers._
import play.api.test._

import scala.concurrent.Future

class ApplicationControllerTest extends PlaySpec {

  "ApplicationController" should {

    val application = new GuiceApplicationBuilder().build()
    val controller = application.injector.instanceOf[ApplicationController]

    "return correct response for foo" in {
      val result: Future[Result] = controller.foo().apply(FakeRequest("GET", "/testUriFoo"))
      contentAsString(result) mustBe "I am listening at /testUriFoo"
      status(result) mustBe Status.OK
    }

    "return correct response for bar" in {
      implicit val mat: Materializer = application.materializer
      val fakeRequest = FakeRequest("POST", "/testUriBar").withTextBody("123456789")
      val result: Accumulator[ByteString, Result] = controller.bar().apply(fakeRequest)
      contentAsString(result) mustBe "Received body of size: 9"
      status(result) mustBe Status.OK
    }

  }

}
