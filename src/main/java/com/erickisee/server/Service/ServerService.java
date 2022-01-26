package com.erickisee.server.Service;
import java.io.IOException;
import java.util.Collection;
import com.erickisee.server.model.Server;

public interface ServerService {
    Server create(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list (int limit);
    Server get(Long id);
    Server update (Server server);
    Boolean delete(Long id);
}
