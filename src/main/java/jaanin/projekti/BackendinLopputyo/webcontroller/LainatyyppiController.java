package jaanin.projekti.BackendinLopputyo.webcontroller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jaanin.projekti.BackendinLopputyo.domain.Lainatyyppi;
import jaanin.projekti.BackendinLopputyo.domain.LainatyyppiRepository;

@Controller
public class LainatyyppiController {

	@Autowired
	private LainatyyppiRepository repo;
	
	//TODO Fixataan nää oikeisiin endpointteihin
	@GetMapping("/lainatyypit")
	public String indexGet(Model model) {
		model.addAttribute("lainatyypit", repo.findAll());
		return "lainatyyppilista";
	}
	

	@GetMapping("/addlainatyyppi")
	public String addbookGet(Model model) {
		model.addAttribute("lainatyyppi", new Lainatyyppi());
		return "addlainatyyppi";
	}
//	
	@PostMapping("/tallennalainatyyppi")
	public String saveCategory(@Valid Lainatyyppi laina,BindingResult bindingResult, Model model) {
		try {
			repo.save(laina);
		} catch (javax.validation.ConstraintViolationException ex) {
			System.out.println("ohhoh");
		} catch (org.springframework.transaction.TransactionSystemException huoh) {
			System.out.println("Hhuoh nappas :) ############");
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		return "redirect:lainatyypit";
	}
	
	@GetMapping("/poistalainatyyppi/{id}")
	public String lainaDelete(@PathVariable("id") Long id, Model model) {
		try {
		repo.deleteById(id);
		}
		catch (Exception e)
		{
			
		}
		return "redirect:../lainatyypit";
	}
	
}
