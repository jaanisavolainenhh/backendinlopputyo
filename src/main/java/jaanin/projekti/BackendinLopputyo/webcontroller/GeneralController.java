package jaanin.projekti.BackendinLopputyo.webcontroller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jaanin.projekti.BackendinLopputyo.domain.AsiakasRepository;
import jaanin.projekti.BackendinLopputyo.domain.LainaRepository;
import jaanin.projekti.BackendinLopputyo.domain.LainatyyppiRepository;

@Controller
public class GeneralController {

	@Autowired
	private LainaRepository repo;
	@Autowired
	private LainatyyppiRepository repo2;

	@Autowired
	private AsiakasRepository repo3;




	// jees
	@GetMapping("/hallinta")
	public String hallintaGet(Model model) {
		return "megapaneeli";
	}


	@GetMapping("/kaikki")
	public String kaikkiGet(Model model) {
		//System.out.println(locale.toString());
		model.addAttribute("lainatyypit", repo2.findAll());
		model.addAttribute("lainat", repo.findAll());
		model.addAttribute("asiakkaat", repo3.findAll());

		return "kaikki";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	
}
