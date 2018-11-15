package sk.ygor.stackoverflow.q53319493.controller

import javax.inject.Inject
import play.api.mvc._

class ApplicationController @Inject()(controllerComponents: ControllerComponents)
  extends AbstractController(controllerComponents) {

  def foo(): Action[AnyContent] = Action {
    Ok(sk.ygor.stackoverflow.q53319493.view.html.foo())
  }

}
