package jaanin.projekti.BackendinLopputyo.webcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jaanin.projekti.BackendinLopputyo.domain.Asiakas;
import jaanin.projekti.BackendinLopputyo.domain.AsiakasRepository;
import jaanin.projekti.BackendinLopputyo.domain.LainaRepository;
import jaanin.projekti.BackendinLopputyo.domain.LainatyyppiRepository;

@Controller
public class AsiakasController {

	@Autowired
	private LainaRepository lainaRepo;
	@Autowired
	private LainatyyppiRepository lainatyyppiRepo;

	@Autowired
	private AsiakasRepository asiakasRepo;

	
	@GetMapping("/asiakaslista")
	public String indexGet(Model model) {
		List<Asiakas> asiakkaat = asiakasRepo.findAll();
		model.addAttribute("asiakkaat", asiakkaat);
		return "asiakaslista";
	}
	
}
