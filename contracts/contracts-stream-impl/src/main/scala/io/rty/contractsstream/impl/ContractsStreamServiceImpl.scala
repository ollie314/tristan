package io.rty.contractsstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import io.rty.contractsstream.api.ContractsStreamService
import io.rty.contracts.api.ContractsService

import scala.concurrent.Future

/**
  *  Implementation of the ContractsStreamService.
  */
class ContractsStreamServiceImpl(contractsService: ContractsService) extends ContractsStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(contractsService.hello(_).invoke()))
  }
}
