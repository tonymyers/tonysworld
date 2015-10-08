package controllers

import models.{TonyDate, PersonalDetails}
import play.api.Logger
import play.api.libs.json.{JsSuccess, Json}
import play.api.mvc.{Action, Controller}

import scala.concurrent.Future

class CompletePersonalDetails extends Controller {
  def getPersonalDetails(firstName: String, lastName: String) = Action.async {
    // TODO: This should come from the database
    val personalDetails = new PersonalDetails(firstName, lastName,TonyDate(1972,7,1))
    Future.successful(Ok(Json.toJson(personalDetails)).as("text/json"))
  }

  def postPersonalDetails = Action.async { implicit request =>
    Logger.debug(s"*********** Personal details posted to here: ${request.body}")
    val personalDetails = request.body.asJson.get.validate[PersonalDetails] match {
      case s: JsSuccess[PersonalDetails] => {
        Some(s.get)
      }
      case _ => {
        Logger.error(s"Couldn't parse the json: ${request.body}")
        None
      }
    }

    Logger.debug(s"From the ${request.body} we parsed a Personal Details of ${personalDetails}")

    //TODO: Save 'personalDetails' into the database here

    Future.successful(Ok)
  }

}
