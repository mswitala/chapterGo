package gatling.test.simulation

import gatling.test.simulation.PerfTestConfig.{test_url, duration_sec, maxResponseTimeMs, meanResponseTimeMs, ramp_up_user_sec, users_per_sec, ramp_up_time_min}
import io.gatling.core.Predef.{constantUsersPerSec, global, scenario, _}
import io.gatling.http.Predef.{http, status, _}

import scala.concurrent.duration._

class ExampleGetSimulation extends Simulation {

  println("TEST_URL: " + test_url)
  println("DURATION_SECONDS: " + duration_sec)
  println("RAMP_UP_USERS_PER_SEC: " + ramp_up_user_sec)
  println("USERS_PER_SEC: " + users_per_sec)
  println("RAMP_UP_TIME_MIN: " + ramp_up_time_min)
  //println("maxResponseTimeMs: " + maxResponseTimeMs)
  //println("meanResponseTimeMs: " + meanResponseTimeMs)


  val httpConf = http.baseUrl(test_url)
  val getUsers = scenario("Root end point calls")
    .exec(http("root end point")
      .get("")
      .check(status.is(200))
    )
  setUp(getUsers.inject(
    rampUsersPerSec(ramp_up_user_sec) to users_per_sec during (ramp_up_time_min minutes),
    constantUsersPerSec(users_per_sec) during (duration_sec seconds))
    .protocols(httpConf))
    .assertions(
      global.responseTime.max.lt(meanResponseTimeMs),
      global.responseTime.mean.lt(maxResponseTimeMs),
      global.successfulRequests.percent.gt(95)
    )
}