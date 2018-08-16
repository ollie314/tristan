package io.rty.accidents.impl

import io.rty.contracts.api
import io.rty.contracts.api.{ContractsService}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.broker.TopicProducer
import com.lightbend.lagom.scaladsl.persistence.{EventStreamElement, PersistentEntityRegistry}

class AccidentsServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends AccidentsService {

  override def hello(id: String) = ServiceCall { _ =>
    val ref = persistentEntityRegistry.refFor[AccidentsEntity](id)
    ref.ask(Hello(id))
  }

  override def useGreeting(id: String) = ServiceCall { request =>
    val ref = persistentEntityRegistry.refFor[AccidentsEntity](id)
    ref.ask(UseGreetingMessage(request.message))
  }


  override def greetingsTopic(): Topic[api.GreetingMessageChanged] =
    TopicProducer.singleStreamWithOffset {
      fromOffset =>
        persistentEntityRegistry.eventStream(AccidentsEvent.Tag, fromOffset)
          .map(ev => (convertEvent(ev), ev.offset))
    }

  private def convertEvent(helloEvent: EventStreamElement[AccidentsEvent]): api.GreetingMessageChanged = {
    helloEvent.event match {
      case GreetingMessageChanged(msg) => api.GreetingMessageChanged(helloEvent.entityId, msg)
    }
  }
}
