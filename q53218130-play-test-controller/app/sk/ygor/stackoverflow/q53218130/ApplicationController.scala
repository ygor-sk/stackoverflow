package sk.ygor.stackoverflow.q53218130

import javax.inject.Inject
import play.api.Configuration
import play.api.mvc._

class ApplicationController @Inject()(configuration: Configuration,
                                      controllerComponents: ControllerComponents)
  extends AbstractController(controllerComponents) {

  def foo(): Action[AnyContent] = Action { request =>
    Ok(s"I am listening at ${request.uri}")
  }

  def bar: Action[String] = Action(parse.tolerantText) { request =>
    Ok(s"Received body of size: ${request.body.length}")
  }
}
