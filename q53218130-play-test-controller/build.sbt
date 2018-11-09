name := "q53218130"
organization := "sk.ygor.stackoverflow"
version := "1.0-SNAPSHOT"
scalaVersion := "2.12.6" // use latest

enablePlugins(PlayScala)

libraryDependencies += play.sbt.PlayImport.guice

libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % "test"
)