package assn5server.assn5server.Controller;

import assn5server.assn5server.model.Tokimon;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.apache.catalina.webresources.TomcatJarInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * @author Tanzil Sarker
 * This is the controller for the server housing tokimon objects, users interac with tokimon objects through their client via this controller
 * @knownBugs Not tested properly, some bugs possible
 */
@RestController
public class TokimonController {
    // private final AtomicLong counter= new AtomicLong();
    private List<Tokimon> tokimons = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();

    @GetMapping("api/tokimon/all")
    public List<Tokimon> getTokimons() {
        System.out.println("endpoint for getting all Tokimon");
        return tokimons;
    }

    @GetMapping("api/tokimon/{id}")
    public Tokimon getTokimons(@PathVariable String id, HttpServletResponse response) {
        //...search for the Tokimon with uid
        for (int i = 0; i < tokimons.size(); i++) {
            if (tokimons.get(i).getUid() == parseInt(id)) {
                System.out.println("getting Tokimon at: " + id);
                return tokimons.get(i);
            }
        }
        System.out.println("Tokimon not found");
        response.setStatus(404);
        return null;
    }

    @PostMapping("api/tokimon/add")
    public void addTokimon(@RequestBody Tokimon newTokimon, HttpServletResponse response) {
        //...add the person to the List
        tokimons.add(newTokimon);
        response.setStatus(201);//created
        jsonUpdater();
    }

    @PostMapping("api/tokimon/change/{id}")
    public void changeTokimon(@RequestBody Tokimon newTokimon, @PathVariable String id, HttpServletResponse response) {
        //...add the person to the List
        try {
            for (int i = 0; i < tokimons.size(); i++) {
                if (tokimons.get(i).getUid() == parseInt(id)) {
                    newTokimon.setUid(tokimons.get(i).getUid());
                    tokimons.set(i, newTokimon);
                    break;
                }
                jsonUpdater();
            }
            System.out.println("TOKIMon removed");
            response.setStatus(204);//created
            jsonUpdater();
        } catch (Exception e) {
            response.setStatus(404);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    @DeleteMapping("api/tokimon/{id}")
    public void deleteTokimon(@PathVariable String id, HttpServletResponse response) {
        //...add the person to the List
        try {
            for (int i = 0; i < tokimons.size(); i++) {
                if (tokimons.get(i).getUid() == parseInt(id)) {
                    tokimons.remove(i);
                    break;
                }
            }
            System.out.println("TOKIMon removed");
            response.setStatus(204);//created
            jsonUpdater();
        } catch (Exception e) {
            response.setStatus(404);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    @PostConstruct
    public void init() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        List<Tokimon> langList = objectMapper.readValue(new File("D:\\CMPT213\\assn5server\\data\\tokimon.json"), new TypeReference<List<Tokimon>>() {
        });
        tokimons = langList;
    }

    public void jsonUpdater() {
        PrintWriter pw = null;

        try {
            URL url = new URL("http://localhost:8080/api/tokimon/all");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output;
            output = br.readLine();
            File file = new File("D:\\CMPT213\\assn5server\\data\\tokimon.json");
            FileWriter fw = new FileWriter(file);
            pw = new PrintWriter(fw);
            pw.println(output);
        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }


}
//test//curl -i -X POST -d "{\"name\":\"bobby\",\"age\":33}" -H "Content-Type:application/json" localhost:8080/person
