/*
 * Copyright (C) 2009-2013 Typesafe Inc. <http://www.typesafe.com>
 */
package play

import sbt._
import sbt.Keys._

trait Keys {

  def component(id: String) = "com.typesafe.play" %% id % play.core.PlayVersion.current

  val jdbc = component("play-jdbc")

  val anorm = component("anorm")

  val javaCore = component("play-java")

  val javaJdbc = component("play-java-jdbc")

  val javaEbean = component("play-java-ebean")

  val javaJpa = component("play-java-jpa")

  val filters = component("filters-helpers")

  val cache = component("play-cache")

  val json = component("play-json")

  val ws = "com.typesafe.play" %% "play-ws" % play.core.PlayVersion.current

  val javaWs = "com.typesafe.play" %% "play-java-ws" % play.core.PlayVersion.current

  val playVersion = SettingKey[String]("play-version")

  val playDefaultPort = SettingKey[Int]("play-default-port")

  val requireJs = SettingKey[Seq[String]]("play-require-js")

  val requireJsFolder = SettingKey[String]("play-require-js-folder")

  val requireJsShim = SettingKey[String]("play-require-js-shim")

  val requireNativePath = SettingKey[Option[String]]("play-require-native-path")

  /** Our means of hooking the run task with additional behavior. */
  val playRunHooks = TaskKey[Seq[play.PlayRunHook]]("play-run-hooks")

  @deprecated("2.2", "Please use playRunHooks setting instead.")
  val playOnStarted = SettingKey[Seq[(java.net.InetSocketAddress) => Unit]]("play-onStarted")

  @deprecated("2.2", "Please use playRunHooks setting instead.")
  val playOnStopped = SettingKey[Seq[() => Unit]]("play-onStopped")

  /** A hook to configure how play blocks on user input while running. */
  val playInteractionMode = SettingKey[play.PlayInteractionMode]("play-interaction-mode")

  val playAssetsDirectories = SettingKey[Seq[File]]("play-assets-directories")

  val playExternalAssets = SettingKey[Seq[(File, File => PathFinder, String)]]("play-external-assets")

  val confDirectory = SettingKey[File]("play-conf")

  val templatesImport = SettingKey[Seq[String]]("play-templates-imports")

  val routesImport = SettingKey[Seq[String]]("play-routes-imports")

  val generateReverseRouter = SettingKey[Boolean]("play-generate-reverse-router",
    "Whether the reverse router should be generated. Setting to false may reduce compile times if it's not needed.")

  val namespaceReverseRouter = SettingKey[Boolean]("play-namespace-reverse-router",
    "Whether the reverse router should be namespaced. Useful if you have many routers that use the same actions.")

  val ebeanEnabled = SettingKey[Boolean]("play-ebean-enabled")

  val templatesTypes = SettingKey[Map[String, String]]("play-templates-formats")

  val closureCompilerOptions = SettingKey[Seq[String]]("play-closure-compiler-options")

  val lessOptions = SettingKey[Seq[String]]("play-less-options")

  val coffeescriptOptions = SettingKey[Seq[String]]("play-coffeescript-options")

  val lessEntryPoints = SettingKey[PathFinder]("play-less-entry-points")

  val coffeescriptEntryPoints = SettingKey[PathFinder]("play-coffeescript-entry-points")

  val javascriptEntryPoints = SettingKey[PathFinder]("play-javascript-entry-points")

  val playPlugin = SettingKey[Boolean]("play-plugin")

  val devSettings = SettingKey[Seq[(String, String)]]("play-dev-settings")

  // An SBT scope used when the Play app is in run in "production mode" using the "play start" command
  val Production = config("production")

  // Constants that may be useful elsewhere
  val defaultJavaTemplatesImport = Seq(
    "models._",
    "controllers._",

    "java.lang._",
    "java.util._",

    "scala.collection.JavaConversions._",
    "scala.collection.JavaConverters._",

    "play.api.i18n._",
    "play.core.j.PlayMagicForJava._",

    "play.mvc._",
    "play.data._",
    "play.api.data.Field",

    "play.mvc.Http.Context.Implicit._",

    "views.%format%._")

  val defaultScalaTemplatesImport = Seq(
    "models._",
    "controllers._",

    "play.api.i18n._",

    "play.api.mvc._",
    "play.api.data._",

    "views.%format%._")

  val defaultTemplatesImport = Seq("play.api.templates._", "play.api.templates.PlayMagic._")

}
object Keys extends Keys

trait PlayInternalKeys {
  type ClassLoaderCreator = (String, Array[URL], ClassLoader) => ClassLoader

  val playDependencyClasspath = TaskKey[Classpath]("play-dependency-classpath")
  val playReloaderClasspath = TaskKey[Classpath]("play-reloader-classpath")
  val playCommonClassloader = TaskKey[ClassLoader]("play-common-classloader")
  val playDependencyClassLoader = TaskKey[ClassLoaderCreator]("play-dependency-classloader")
  val playReloaderClassLoader = TaskKey[ClassLoaderCreator]("play-reloader-classloader")
  val playReload = TaskKey[sbt.inc.Analysis]("play-reload")
  val buildRequire = TaskKey[Seq[(File, File)]]("play-build-require-assets")
  val playCompileEverything = TaskKey[Seq[sbt.inc.Analysis]]("play-compile-everything")
}
