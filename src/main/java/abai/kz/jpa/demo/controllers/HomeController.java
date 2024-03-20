package abai.kz.jpa.demo.controllers;

import abai.kz.jpa.demo.entities.Requests;
import abai.kz.jpa.demo.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    @Autowired
    RequestRepository requestRepository;

    @GetMapping(value = "/")
    public String getIndex(Model model) {
        List<Requests> requests = requestRepository.findAll();
        model.addAttribute("requests", requests);
        return "index";
    }
    @GetMapping(value = "/addRequest")
    public String addRequest(Model model) {
//        List<Requests> requests = requestRepository.findAll();
//        model.addAttribute("requests", requests);
        return "addRequest";
    }
    @PostMapping(value = "/addRequest")
    public String addRequest(@RequestParam(name="name") String name,
                             @RequestParam(name="course") String course,
                             @RequestParam(name = "number") String number,
                             @RequestParam(name = "comment") String comment) {
        Requests requests = new Requests();
        requests.setUsername(name);
        requests.setCoursename(course);
        requests.setNumber(number);
        requests.setCommentary(comment);
        requests.setHandled(false);
        requestRepository.save(requests);
        return "redirect:/";
    }
    @GetMapping(value = "/details/{id}")
    public String detailsRequest(@PathVariable(name="id") Long id,
                                 Model model) {
        Requests request = requestRepository.findById(id).orElse(null);
        model.addAttribute("request",request);
        return "details";
    }
    @PostMapping(value = "/detailsUpd")
    public String detailsUpdateRequest(@RequestParam(name = "id") Long id,
                                       @RequestParam(name="name") String name,
                                       @RequestParam(name="course") String course,
                                       @RequestParam(name = "number") String number,
                                       @RequestParam(name = "comment") String comment,
                                       @RequestParam(name = "handled") int handled) {
        Requests request = new Requests();

        request.setId(id);
        request.setUsername(name);
        request.setCoursename(course);
        request.setNumber(number);
        request.setCommentary(comment);
        boolean handl;
        if(handled==1) {
            handl = true;
            System.out.println(handl);

        } else {
            handl = false;
        }
        request.setHandled(handl);
        requestRepository.save(request);
        return "redirect:/";
    }
    @PostMapping(value = "/delete")
    public String detailsDeleteRequest(@RequestParam(name = "id") Long id) {
        requestRepository.deleteById(id);
        return "redirect:/";
    }
    @GetMapping(value = "/handledRequests")
    public String allHandled(Model model) {
        List<Requests> requests = requestRepository.findAllByHandled(false);
        model.addAttribute("handledReqs",requests);
        return "handledRequests";
    }
    @GetMapping(value = "/notHandledRequests")
    public String allNotHandled(Model model) {
        List<Requests> requests = requestRepository.findAllByHandled(true);
        model.addAttribute("handledReqs",requests);
        return "notHandledRequests";
    }
    @PostMapping(value = "/findClient")
    public String findClient(@RequestParam(name = "search") String search,
                             Model model) {
        List<Requests> requests = requestRepository.findAllByUsernameContains(search);
        model.addAttribute("searchResult", requests);
        return "/searchResult";
    }
}
