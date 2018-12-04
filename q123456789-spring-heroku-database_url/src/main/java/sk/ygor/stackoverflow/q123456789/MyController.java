package sk.ygor.stackoverflow.q123456789;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
public class MyController {

    private final MyEntityRepository myEntityRepository;

    @Autowired
    public MyController(MyEntityRepository myEntityRepository) {
        this.myEntityRepository = myEntityRepository;
    }

    @RequestMapping("/log/**")
    public @ResponseBody Iterable<MyEntity> getAndLogRequest(HttpServletRequest request) {
        myEntityRepository.save(new MyEntity(String.format("%s: %s: %s %s",
                LocalDateTime.now().toString(), request.getRemoteAddr(), request.getMethod(), request.getRequestURI()
        )));
        return myEntityRepository.findAll();
    }

    @RequestMapping("/env")
    public @ResponseBody Map<String, String> env() {
        return System.getenv();
    }
}
