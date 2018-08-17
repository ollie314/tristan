package io.rty.accidentsstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import io.rty.contractsstream.api.AccidentsStreamService
import io.rty.contracts.api.AccidentsService

import scala.concurrent.Future

/**
  *  Implementation of the ContractsStreamService.
  */
class AccidentsStreamServiceImpl(accidentsService: AccidentsService) extends AccidentsStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(accidentsService.hello(_).invoke()))
  }
}
