package simple.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simple.api.Digest;
import simple.api.Message;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
public class SimpleServiceResource {

    private final Logger logger = LoggerFactory.getLogger(SimpleServiceResource.class);
    private Map<String, Message> messages = new ConcurrentHashMap<>();

    public SimpleServiceResource() {
    }

    @GET
    @Path("{digest}")
    public Message getMessage(@PathParam("digest") String digest) {
        Message message = messages.get(digest);
        if (null != message) {
            return message;
        }
        throw new WebApplicationException(404);
    }

    @POST
    public Digest postMessage(Message message) {
        String sha256hex = DigestUtils.sha256Hex(message.getMessage());
        Message m = messages.get(sha256hex);
        if (null != m) {
            if (!message.getMessage().equals(m.getMessage())) {
                // sha256 collision. WOW. Let's log it.
                logger.warn("Found 2 messages with the same sha256: {}, {}", message.getMessage(), m.getMessage());
            }
        }
        messages.put(sha256hex, message);
        return new Digest(sha256hex);
    }

}
