package io.rty.accidents.impl

import java.time.LocalDateTime

import akka.Done
import com.lightbend.lagom.scaladsl.persistence.{AggregateEvent, AggregateEventTag, PersistentEntity}
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity.ReplyType
import com.lightbend.lagom.scaladsl.playjson.{JsonSerializer, JsonSerializerRegistry}
import play.api.libs.json.{Format, Json}

import scala.collection.immutable.Seq

class AccidentsEntity extends PersistentEntity {

  override type Command = ContractsCommand[_]
  override type Event = ContractsEvent
  override type State = ContractsState

  override def initialState: AccidentsState = AccidentsState("Accident is under control", LocalDateTime.now.toString)

  override def behavior: Behavior = {
    case AccidentState(message, _) => Actions().onCommand[UseGreetingMessage, Done] {
      case (UseGreetingMessage(newMessage), ctx, state) =>
        ctx.thenPersist(
          GreetingMessageChanged(newMessage)
        ) { _ =>
          ctx.reply(Done)
        }
    }.onReadOnlyCommand[Hello, String] {
      case (Hello(name), ctx, state) =>
        ctx.reply(s"$message, $name!")
    }.onEvent {
      case (GreetingMessageChanged(newMessage), state) =>
        AccidentsState(newMessage, LocalDateTime.now().toString)

    }
  }
}

case class AccidentsState(message: String, timestamp: String)

object AccidentsState {
  implicit val format: Format[AccidentsState] = Json.format
}

sealed trait AccidentsEvent extends AggregateEvent[AccidentsEvent] {
  def aggregateTag = AccidentsEvent.Tag
}

object AccidentsEvent {
  val Tag = AggregateEventTag[AccidentsEvent]
}

case class GreetingMessageChanged(message: String) extends AccidentsEvent

object GreetingMessageChanged {
  implicit val format: Format[GreetingMessageChanged] = Json.format
}

sealed trait AccidentsCommand[R] extends ReplyType[R]

case class UseGreetingMessage(message: String) extends AccidentsCommand[Done]

object UseGreetingMessage {
  implicit val format: Format[UseGreetingMessage] = Json.format
}

case class Hello(name: String) extends AccidentsCommand[String]

object Hello {
  implicit val format: Format[Hello] = Json.format
}

object AccidentsSerializerRegistry extends JsonSerializerRegistry {
  override def serializers: Seq[JsonSerializer[_]] = Seq(
    JsonSerializer[UseGreetingMessage],
    JsonSerializer[Hello],
    JsonSerializer[GreetingMessageChanged],
    JsonSerializer[AccidentsState]
  )
}
