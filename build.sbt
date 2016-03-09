name := "s-expr"

organization := "io.github.dzufferey"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.8"

scalacOptions in Compile ++= Seq(
    "-unchecked",
    "-deprecation",
    "-feature"
)

libraryDependencies ++=  Seq(
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
    "org.scalatest" %% "scalatest" % "2.2.6" % "test",
    "org.scalacheck" %% "scalacheck" % "1.13.0" % "test"
)

addCompilerPlugin("org.psywerx.hairyfotr" %% "linter" % "0.1.13")

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

