import sbt._
import Keys._
import com.twitter.sbt._

object util extends Build {

	val scalatestVersion = "2.0.M8"

  val publishSettings : Seq[sbt.Project.Setting[_]] = Seq(
    publishTo := Some("newzly releases" at "http://maven.newzly.com/repository/internal"),
    credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
    publishMavenStyle := true,
    publishArtifact in Test := false,
    pomIncludeRepository := { _ => true }
  )

	val sharedSettings: Seq[sbt.Project.Setting[_]] = Seq(
		organization := "com.newzly",
		version := "0.0.2",
		scalaVersion := "2.10.3",
		resolvers ++= Seq(
		"Sonatype repo"                    at "https://oss.sonatype.org/content/groups/scala-tools/",
		"Sonatype releases"                at "https://oss.sonatype.org/content/repositories/releases",
		"Sonatype snapshots"               at "https://oss.sonatype.org/content/repositories/snapshots",
		"Sonatype staging"                 at "http://oss.sonatype.org/content/repositories/staging",
		"Java.net Maven2 Repository"       at "http://download.java.net/maven/2/",
		"Twitter Repository"               at "http://maven.twttr.com",
    "newzly snapshots"                 at "http://maven.newzly.com/repository/snapshots",
    "newzly repository"                at "http://maven.newzly.com/repository/internal"
		),
		scalacOptions ++= Seq(
      "-language:postfixOps",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-language:reflectiveCalls",
      "-deprecation",
      "-feature",
      "-unchecked"
		)
	) ++ net.virtualvoid.sbt.graph.Plugin.graphSettings


	val finagleVersion = "6.10.0"

	lazy val newzlyUtil = Project(
		id = "newzly-util",
		base = file("."),
		settings = Project.defaultSettings ++ VersionManagement.newSettings ++ sharedSettings ++ publishSettings
	).aggregate(
		newzlyUtilCore,
		newzlyUtilFinagle,
		newzlyUtilTest
	)

	lazy val newzlyUtilCore = Project(
		id = "newzly-util-core",
		base = file("newzly-util-core"),
		settings = Project.defaultSettings ++ VersionManagement.newSettings ++ sharedSettings ++ publishSettings
	).settings(
		name := "newzly-util-core"
	)

	lazy val newzlyUtilFinagle = Project(
		id = "newzly-util-finagle",
		base = file("newzly-util-finagle"),
		settings = Project.defaultSettings ++ VersionManagement.newSettings ++ sharedSettings ++ publishSettings
	).settings(
		name := "newzly-util-finagle",
		libraryDependencies ++= Seq(
			"com.twitter"     %%  "util-core"          % "6.3.6",
			"org.scalatest"   %% "scalatest"           % scalatestVersion % "provided"  
		)
	).dependsOn(
		newzlyUtilCore
	)

	lazy val newzlyUtilTest = Project(
		id = "newzly-util-test",
		base = file("newzly-util-test"),
		settings = Project.defaultSettings ++ VersionManagement.newSettings ++ sharedSettings ++ publishSettings
	).settings(
		name := "newzly-util-test",
		libraryDependencies ++= Seq(
			"org.scalatest"   %% "scalatest"           % scalatestVersion % "provided"  
		)
	).dependsOn(
		newzlyUtilCore,
		newzlyUtilFinagle
	)

}