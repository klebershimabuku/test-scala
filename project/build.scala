import sbt._
import Keys._
import org.scalatra.sbt._

object BuildSettings {
  val Organization = "locaweb"
  val Name         = "payments"
  val Version      = "1.0.0"
  val ScalaVersion = "2.10.3"
  val JavaSource   = "1.6"

  val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := Organization,
    name         := Name,
    version      := Version,
    scalaVersion := ScalaVersion,
    compileOrder := CompileOrder.Mixed,
    javacOptions ++= Seq("-source", JavaSource)
  )
}

object Resolvers {
  val typesafe = Classpaths.typesafeReleases
  val allResolvers = Seq(typesafe)
}

object Dependencies {
  val ScalatraVersion       = "2.2.2"
  val Json4SVersion         = "3.2.6"
  val JettyVersion          = "8.1.13.v20130916"
  val ServletApi            = "3.0.0.v201112011016"
  val AkkaVersion           = "2.2.3"
  val LogBackVersion        = "1.0.7"
  val Slf4jVersion          = "1.7.5"

  val logbackClassic = "ch.qos.logback" % "logback-classic" % LogBackVersion
  val slf4jBridge    = "org.slf4j"      % "jcl-over-slf4j" % Slf4jVersion

  val scalatra     = "org.scalatra" %% "scalatra"          % ScalatraVersion
  val scalatraCmd  = "org.scalatra" %% "scalatra-commands" % ScalatraVersion
  val scalatraJson = "org.scalatra" %% "scalatra-json"     % ScalatraVersion

  val json4s = "org.json4s" %% "json4s-jackson" % Json4SVersion

  val jettyContainer = "org.eclipse.jetty"       % "jetty-webapp"  % JettyVersion % "container;compile;test"
  val servletApi     = "org.eclipse.jetty.orbit" % "javax.servlet" % ServletApi   % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))

  val akka      = "com.typesafe.akka" %% "akka-actor"   % AkkaVersion
  val akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j"   % AkkaVersion
  val akkaTest  = "com.typesafe.akka" %% "akka-testkit" % AkkaVersion % "test"

  val scalatraScalaTest = "org.scalatra"  %% "scalatra-scalatest"          % ScalatraVersion  % "test"

  val coreDeps = Seq(
    logbackClassic,
    slf4jBridge,
    akka,
    akkaSlf4j
  )

  val testDeps = Seq(
    scalatraScalaTest,
    akkaTest,
    jettyContainer,
    servletApi
  )

  val webDeps = Seq(
    scalatra,
    scalatraJson,
    json4s,
    jettyContainer,
    servletApi
  )

  val deps = coreDeps ++ webDeps ++ testDeps
}

object PaymentsBuild extends Build {
  import BuildSettings._
  import Resolvers._
  import Dependencies._

  lazy val LocalTest = config("local") extend(Test)

  lazy val project = Project (
    "payments",
    file("."),
    settings = buildSettings ++ Seq(resolvers := allResolvers, libraryDependencies ++= deps)
      ++ ScalatraPlugin.scalatraSettings
  ).configs(LocalTest)
    .settings(inConfig(LocalTest)(Defaults.testTasks): _*)
}
