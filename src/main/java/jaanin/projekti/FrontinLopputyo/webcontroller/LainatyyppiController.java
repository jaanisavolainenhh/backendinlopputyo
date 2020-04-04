package jaanin.projekti.FrontinLopputyo.webcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jaanin.projekti.FrontinLopputyo.domain.Category;
import jaanin.projekti.FrontinLopputyo.domain.CategoryRepository;

@Controller
public class LainatyyppiController {

	@Autowired
	private CategoryRepository repo;
	
	
	@GetMapping("/categorylist")
	public String indexGet(Model model) {
		model.addAttribute("categories", repo.findAll());
		return "categorylist";
	}
	


	@GetMapping("/addcategory")
	public String addbookGet(Model model) {
		model.addAttribute("category", new Category());
		return "addcategory";
	}
	
	@PostMapping("/savecategory")
	public String saveCategory(Category category) {
		repo.save(category);
		return "redirect:categorylist";
	}
	
}
