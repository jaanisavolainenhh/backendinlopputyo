package jaanin.projekti.BackendinLopputyo.webcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jaanin.projekti.BackendinLopputyo.domain.Laina;
import jaanin.projekti.BackendinLopputyo.domain.LainaRepository;
import jaanin.projekti.BackendinLopputyo.domain.LainatyyppiRepository;


@Controller
public class LainaController {

	@Autowired
	private LainaRepository repo;
	@Autowired
	private LainatyyppiRepository repo2;
	
	@GetMapping("/index")
	public String indexGet(Model model) {
		return "index";
	}

	@GetMapping("/Lainalista")
	public String booklistGet(Model model) {
		model.addAttribute("books", repo.findAll());
		return "booklist";
	}
	
	//Resti
	@RequestMapping(value = "/lainat", method = RequestMethod.GET)
	public @ResponseBody List<Laina> bookListRest() {
		return (List<Laina>) repo.findAll();
	}
	
	@RequestMapping(value = "/laina/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Laina> bookRest(@PathVariable("id") Long id) {
		return repo.findById(id);
	}
	
	
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	

	@GetMapping("/delete/{id}")
	public String bookDelete(@PathVariable("id") Long id, Model model) {
		repo.deleteById(id);
		return "redirect:../booklist";
	}
	
	@GetMapping("/addlaina")
	public String addbookGet(Model model) {
		model.addAttribute("book", new Laina());
		model.addAttribute("categories", repo2.findAll());
		return "addbook";
	}
	
	@PostMapping("/savelaina")
	public String savebookPost(Laina book) {
		repo.save(book);
		return "redirect:booklist";
	}
	
	@GetMapping("/editlaina/{id}")
	public String editbookGet(@PathVariable("id") Long id, Model model) {
		Laina book = repo.findById(id).get();
		model.addAttribute("book", book);
		model.addAttribute("categories", repo2.findAll());
		return "editbook";
	}
}

