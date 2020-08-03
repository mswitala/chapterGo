package gatling.test.simulation

import gatling.test.simulation.SystemPropertiesUtil._
import scala.concurrent.duration.FiniteDuration

object PerfTestConfig {

  val test_url: String = sys.props.getOrElse("TEST_URL", "http://localhost:8080").toString

  val users_per_sec: Int = sys.props.getOrElse("USERS_PER_SEC", "1").toInt
  val ramp_up_time_min: Int = sys.props.getOrElse("RAMP_UP_TIME_MIN", "1").toInt
  val ramp_up_user_sec: Int = sys.props.getOrElse("RAMP_UP_USERS_PER_SEC", "1").toInt
  val duration_sec: Int = sys.props.getOrElse("DURATION_SECONDS", "60").toInt

  val meanResponseTimeMs = getAsIntOrElse("meanResponseTimeMs", 500)
  val maxResponseTimeMs = getAsIntOrElse("maxResponseTimeMs", 1000)
}