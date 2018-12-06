lazy val commonSettings = Seq(
  organization := "sk.ygor.stackoverflow",
  version := "1.0-SNAPSHOT",
  scalaVersion := "2.12.7",
)

lazy val root = (project in file("."))
  .settings(
    name := "q53640045",
    commonSettings,
  )
  .aggregate(
    q53640045Main,
    q53640045Macro,
  )

lazy val q53640045Main = (project in file("modules/q53640045-main"))
  .settings(
    name := "q53640045-main",
    commonSettings,
  ).dependsOn(q53640045Macro)

lazy val q53640045Macro = (project in file("modules/q53640045-macro"))
  .settings(
    name := "q53640045-macro",
    commonSettings,
    libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  )

