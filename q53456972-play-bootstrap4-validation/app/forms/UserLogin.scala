package forms

import play.api.data.Form
import play.api.data.Forms._

case class UserLogin(email: String, password: String)

object UserLogin {
  val form: Form[UserLogin] = Form(
    mapping(
      "email" -> email,
      "password" -> nonEmptyText(minLength = 8, maxLength = 24)
    )(UserLogin.apply)(UserLogin.unapply)
  )
}
