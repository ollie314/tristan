package io.rty.accidentsstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import io.rty.accidentsstream.api.ContractsStreamService
import io.rty.accidents.api.ContractsService
import com.softwaremill.macwire._

class AccidentsStreamLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new AccidentsStreamApplication(context) {
      override def serviceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new AccidentsStreamApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[AccidentsStreamService])
}

abstract class AccidentsStreamApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  override lazy val lagomServer = serverFor[AccidentsStreamService](wire[AccidentsStreamServiceImpl])
  lazy val accidentsService = serviceClient.implement[AccidentsService]
}
