package io.rty.contractsstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import io.rty.contractsstream.api.ContractsStreamService
import io.rty.contracts.api.ContractsService
import com.softwaremill.macwire._

class ContractsStreamLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new ContractsStreamApplication(context) {
      override def serviceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new ContractsStreamApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[ContractsStreamService])
}

abstract class ContractsStreamApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = serverFor[ContractsStreamService](wire[ContractsStreamServiceImpl])

  // Bind the ContractsService client
  lazy val contractsService = serviceClient.implement[ContractsService]
}
