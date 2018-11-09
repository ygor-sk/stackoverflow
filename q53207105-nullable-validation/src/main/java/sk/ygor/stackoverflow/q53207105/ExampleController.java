package sk.ygor.stackoverflow.q53207105;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ExampleController {

    @RequestMapping(path = "/q53207105", method = RequestMethod.POST)
    public void test(@Valid @RequestBody SomePOJO somePOJO) {
        System.out.println("somePOJO.getCountry() = " + somePOJO.getCountry());
        System.out.println("somePOJO.getState() = " + somePOJO.getState());
    }

}
