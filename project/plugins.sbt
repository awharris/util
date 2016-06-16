resolvers ++= Seq(
  Classpaths.typesafeReleases,
  Resolver.sonatypeRepo("releases"),
  Resolver.jcenterRepo,
  "jgit-repo" at "http://download.eclipse.org/jgit/maven",
  "Twitter Repo" at "http://maven.twttr.com/"
)

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.2")

addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.0")

addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")

addSbtPlugin("com.websudos" %% "sbt-package-dist" % "1.2.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.8.5")

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.7.0")

addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % "2.0.4")

addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "1.1")

addSbtPlugin("org.scoverage" %% "sbt-scoverage" % "1.3.3")

addSbtPlugin("org.scoverage" %% "sbt-coveralls" % "1.0.3")

addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.1.10")