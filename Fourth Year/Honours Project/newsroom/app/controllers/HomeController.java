package controllers;

import actors.WebSocketActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.Materializer;
import play.libs.streams.ActorFlow;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.WebSocket;

import java.util.List;

import play.mvc.*;

import javax.inject.Inject;

import static play.libs.Scala.asScala;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    private final ActorSystem actorSystem;
    private final Materializer materializer;


    @Inject
    public HomeController(ActorSystem actorSystem, Materializer materializer) {
        this.actorSystem = actorSystem;
        this.materializer = materializer;
    }

    public Result index(Http.Request request) {
        return ok(views.html.index.render(request));
    }

    public Result resultsView(List<String> searchTerm) {
        return ok(views.html.results.render(searchTerm));
    }

    public Result eventView(Http.Request request, String eventID) {
        return ok(views.html.eventView.render(request, eventID));
    }

    public WebSocket socket() {

        return WebSocket.Json.accept(
                request -> ActorFlow.actorRef(this::createWebSocketActor, actorSystem, materializer));
    }

    public Props createWebSocketActor(ActorRef out) {
        return Props.create(WebSocketActor.class, out); // calls the constructor for Game Actor
    }

}
