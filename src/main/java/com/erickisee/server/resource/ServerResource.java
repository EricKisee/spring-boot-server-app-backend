package com.erickisee.server.resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import javax.validation.Valid;

import com.erickisee.server.Service.implementation.ServerServiceImplementation;
import com.erickisee.server.enumeration.Status;
import com.erickisee.server.model.Response;
import com.erickisee.server.model.Server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {

    private final ServerServiceImplementation serverServiceImplementation;

    @GetMapping("/list")
    public ResponseEntity <Response> getServers () {
        return ResponseEntity.ok(
            Response.builder()
                .timeStamp(now())
                .data(Map.of("servers", serverServiceImplementation.list(30)))
                .message("Servers retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build()
        );
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity <Response> pingServer (@PathVariable("ipAddress") String ipAddress) throws IOException{

        Server server = serverServiceImplementation.ping(ipAddress);

        return ResponseEntity.ok(
            Response.builder()
                .timeStamp(now())
                .data(Map.of("server",server))
                .message(server.getStatus()==Status.SERVER_UP?"ping success":"ping failed")
                .status(OK)
                .statusCode(OK.value())
                .build()
        );
    }


    @PostMapping ("/save")
    public ResponseEntity <Response> saveServer (@RequestBody @Valid Server server) throws IOException{

        return ResponseEntity.ok(
            Response.builder()
                .timeStamp(now())
                .data(Map.of("server",serverServiceImplementation.create(server)))
                .message("Server created")
                .status(CREATED)
                .statusCode(CREATED.value())
                .build()
        );
    }


    @GetMapping("/get/{id}")
    public ResponseEntity <Response> getServer (@PathVariable("id") Long id) throws IOException{
        return ResponseEntity.ok(
            Response.builder()
                .timeStamp(now())
                .data(Map.of("server",serverServiceImplementation.get(id)))
                .message("Server retieved")
                .status(OK)
                .statusCode(OK.value())
                .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity <Response> deleteServer (@PathVariable("id") Long id) throws IOException{
        return ResponseEntity.ok(
            Response.builder()
                .timeStamp(now())
                .data(Map.of("server",serverServiceImplementation.delete(id)))
                .message("Server deleted")
                .status(OK)
                .statusCode(OK.value())
                .build()
        );
    }
    

    @GetMapping(path = "/image/{fileName}" , produces= IMAGE_PNG_VALUE)
    public byte[] getServerImage (@PathVariable("fileName") String fileName) throws IOException{
    	return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/Downloads/images/"+fileName));
    }
    
    



}
