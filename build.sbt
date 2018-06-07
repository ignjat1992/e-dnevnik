name := """e-dnevnik"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies += guice
libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  "org.reactivemongo" %% "play2-reactivemongo" % "0.12.7-play26")

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
