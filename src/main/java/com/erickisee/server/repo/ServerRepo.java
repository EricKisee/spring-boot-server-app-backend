package com.erickisee.server.repo;
import com.erickisee.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server, Long>{

    Server findByIpAddress (String ipAddress);
    
}
