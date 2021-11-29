name := """NewsRoom"""
organization := "com.NewsRoom"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.6"

libraryDependencies += (guice)
libraryDependencies += "com.google.apis" % "google-api-services-youtube" % "v3-rev20210915-1.32.1"
libraryDependencies += "com.google.api-client" % "google-api-client" % "1.32.2"

