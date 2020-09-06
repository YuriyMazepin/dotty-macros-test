val dottyVersion = "0.27.0-RC1"

lazy val root = project.in(file(".")).settings(
  name := "macro-test",
  scalaVersion := dottyVersion,
)
