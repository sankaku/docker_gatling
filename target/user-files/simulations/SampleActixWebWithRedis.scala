package computerdatabase

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SampleActixWebWithRedis extends Simulation {
  // val baseUrl = "http://host.docker.internal:8080"
  val baseUrl = "http://localhost:8080"

  val userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93 Safari/537.36"

  val httpProtocol = http
    .baseUrl(baseUrl)
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader(userAgent)

  def createScn(
    title: String,
    path: String,
  ) = scenario(title)
    .exec(
      http("testing set...")
        .get(path)
        .check(status.in(200))
        .check(
          regex("""([0-9a-z\-]+)""")
            .saveAs("uuid"),
        ),
    )
    .exec(
      http("testing get...")
        .get(s"${path}/#{uuid}")
        .check(status.in(200)),
    )

  def createScenario(
    name: String,
    path: String,
    usersPerSec: Int,
    duration: Int
  ) = {
    createScn(name, path).inject(constantUsersPerSec(usersPerSec) during(duration seconds)).protocols(httpProtocol)
  }

  setUp(
    // createScenario("direct", "/direct", 100, 10),
    // createScenario("r2d2", "/r2d2", 100, 10),
    // createScenario("bb8", "/bb8", 100, 10),
    // createScenario("deadpool", "/deadpool", 100, 10),
    createScenario("mobc", "/mobc", 100, 10),
    // createScenario("alt_r2d2", "/alt_r2d2", 100, 10),
  )
}
