organization in ThisBuild := "io.rty"
version in ThisBuild := "0.0.1-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.12.6"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test

// ============================================================================
//                                   Accidents
// ============================================================================

lazy val `accidents` = (project in file("accidents"))
  .aggregate(`accidents-api`, `accidents-impl`, `accidents-stream-api`, `accidents-stream-impl`)

lazy val `accidents-api` = (project in file("accidents/accidents-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `accidents-impl` = (project in file("accidents/accidents-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`contracts-api`)

lazy val `accidents-stream-api` = (project in file("accidents/accidents-stream-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `accidents-stream-impl` = (project in file("accidents/accidents-stream-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`accidents-stream-api`, `accidents-api`)


// ============================================================================
//                                   Proposals
// ============================================================================
lazy val `proposals` = (project in file("proposals"))
  .aggregate(`proposals-api`, `proposals-impl`, `proposals-stream-api`, `proposals-stream-impl`)

lazy val `proposals-api` = (project in file("proposals/proposals-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `proposals-impl` = (project in file("proposals/proposals-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`proposals-api`)

lazy val `proposals-stream-api` = (project in file("proposals/proposals-stream-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `proposals-stream-impl` = (project in file("proposals/proposals-stream-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`proposals-stream-api`, `proposals-api`)


// ============================================================================
//                                   Invoicings
// ============================================================================

lazy val `invoicings` = (project in file("invoicings"))
  .aggregate(`invoicings-api`, `invoicings-impl`, `invoicings-stream-api`, `invoicings-stream-impl`)

lazy val `invoicings-api` = (project in file("invoicings/invoicings-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `invoicings-impl` = (project in file("invoicings/invoicings-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`invoicings-api`)

lazy val `invoicings-stream-api` = (project in file("invoicings/invoicings-stream-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `invoicings-stream-impl` = (project in file("invoicings/invoicings-stream-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`invoicings-stream-api`, `invoicings-api`)


// ============================================================================
//                                   Contracts
// ============================================================================

lazy val `contracts` = (project in file("contracts"))
  .aggregate(`contracts-api`, `contracts-impl`, `contracts-stream-api`, `contracts-stream-impl`)

lazy val `contracts-api` = (project in file("contracts/contracts-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `contracts-impl` = (project in file("contracts/contracts-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`contracts-api`)

lazy val `contracts-stream-api` = (project in file("contracts/contracts-stream-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `contracts-stream-impl` = (project in file("contracts/contracts-stream-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`contracts-stream-api`, `contracts-api`)
