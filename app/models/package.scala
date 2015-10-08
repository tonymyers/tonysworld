import play.api.libs.json._
import play.api.libs.functional.syntax._

package object models {
  implicit val tonyDateReads: Reads[TonyDate] = (
    (JsPath \ "year").read[Int] and
      (JsPath \ "month").read[Int] and
      (JsPath \ "day").read[Int]
    )(TonyDate.apply _)

  implicit val personalDetailsReads: Reads[PersonalDetails] = (
    (JsPath \ "firstName").read[String] and
      (JsPath \ "lastName").read[String] and
      (JsPath \ "DOB").read[TonyDate]
    )(PersonalDetails.apply _)
}
