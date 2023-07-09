name := "ChessGame"
version := "1.0"
scalaVersion := "2.13.11"

javacOptions ++= Seq("-source", "11", "-target", "11")

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % Test
