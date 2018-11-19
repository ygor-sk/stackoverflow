lazy val commonSettings = Seq(
  organization := "sk.ygor.stackoverflow",
  version := "1.0-SNAPSHOT",
  scalaVersion := "2.12.7",
)

lazy val root = (project in file("."))
  .settings(
    name := "q53326545",
    commonSettings,
  )
  .aggregate(
    q53326545Main,
    q53326545Macro,
  )

lazy val q53326545Main = (project in file("modules/q53326545-main"))
  .settings(
    name := "q53326545-main",
    commonSettings,
  ).dependsOn(q53326545Macro)

lazy val q53326545Macro = (project in file("modules/q53326545-macro"))
  .settings(
    name := "q53326545-macro",
    commonSettings,
    libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.12.7",
  )

