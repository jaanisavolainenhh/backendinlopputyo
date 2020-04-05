package jaanin.projekti.BackendinLopputyo.webcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jaanin.projekti.BackendinLopputyo.domain.Lainatyyppi;
import jaanin.projekti.BackendinLopputyo.domain.LainatyyppiRepository;

@Controller
public class LainatyyppiController {

	@Autowired
	private LainatyyppiRepository repo;
	
	//TODO Fixataan nää oikeisiin endpointteihin
	@GetMapping("/lainatyyppilist")
	public String indexGet(Model model) {
		model.addAttribute("categories", repo.findAll());
		return "lainatyyppilista";
	}
	

	@GetMapping("/addcategory")
	public String addbookGet(Model model) {
		model.addAttribute("category", new Lainatyyppi());
		return "addlainatyyppi";
	}
	
	@PostMapping("/savecategory")
	public String saveCategory(Lainatyyppi category) {
		repo.save(category);
		return "redirect:lainatyyppilist";
	}
	
}
