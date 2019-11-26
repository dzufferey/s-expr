name := "s-expr"

organization := "io.github.dzufferey"

version := "0.1-SNAPSHOT"

scalaVersion := "2.13.1"

crossScalaVersions := Seq("2.12.10", "2.13.1")

scalacOptions in Compile ++= Seq(
    "-unchecked",
    "-deprecation",
    "-feature"
)

libraryDependencies ++=  Seq(
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
    "org.scalatest" %% "scalatest" % "3.0.8" % "test",
    "org.scalacheck" %% "scalacheck" % "1.14.1" % "test"
)

//addCompilerPlugin("org.psywerx.hairyfotr" %% "linter" % "0.1.17")

publishMavenStyle := true

publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))

pomExtra :=
  <licenses>
    <license>
      <name>Apache 2</name>
      <url>https://www.apache.org/licenses/LICENSE-2.0.txt</url>
      <distribution>repo</distribution>
    </license>
  </licenses>

