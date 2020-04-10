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

import jaanin.projekti.BackendinLopputyo.domain.Asiakas;
import jaanin.projekti.BackendinLopputyo.domain.AsiakasRepository;
import jaanin.projekti.BackendinLopputyo.domain.Laina;
import jaanin.projekti.BackendinLopputyo.domain.LainaRepository;
import jaanin.projekti.BackendinLopputyo.domain.Lainatyyppi;
import jaanin.projekti.BackendinLopputyo.domain.LainatyyppiRepository;

@Controller
public class LainaController {

	@Autowired
	private LainaRepository repo;
	@Autowired
	private LainatyyppiRepository repo2;

	@Autowired
	private AsiakasRepository repo3;

	@GetMapping("/")
	public String indexGet(Model model) {
		return "haeLainaa";
	}
	// TODO Fixataan nää oikeisiin endpointteihin

	@PostMapping("/hakemus") //oisko vaan getmappina niin ei tarvi securitya miettiä
	public String katsohakemus(Laina laina) {

			if(validoiHakemus(laina))
				return "redirect:/lainalista"; //laina ok
			else
				return "redirect:/lainalista"; //laina ei ok, mitäs nyt
				// palautetaan vain jokin sivu missä on että "Lainahakemus onnistui! Ei onnistunut!" ja urli muualle
	}

	private boolean validoiHakemus(Laina laina) {

		Asiakas asiakas;
		asiakas = repo3.findByNimi(laina.getAsiakas().getNimi());
		if(asiakas.getId() == null)
		{
			//luodaan asiakas
			asiakas = new Asiakas(laina.getAsiakas().getNimi());
			repo3.save(asiakas);
		}
		
		List<Lainatyyppi> lainatyypit;
		lainatyypit = repo2.findByName(laina.getLainatyyppi().getName());
		if(lainatyypit.size() == 0)
		{
			return false;
		}
		Lainatyyppi lainatyyppi = lainatyypit.get(0);
		
		Laina uusiLaina = new Laina(asiakas, 500, lainatyyppi);
		
		repo.save(uusiLaina);
		
		return true;
	}

	@GetMapping("/lainalista")
	public String lainalistGet(Model model) {
		model.addAttribute("lainat", repo.findAll());
		return "lainalista";
	}
	//jees
	@GetMapping("/hallinta")
	public String hallintaGet(Model model) {
		return "megapaneeli";
	}

	// Resti
	@RequestMapping(value = "/lainat", method = RequestMethod.GET)
	public @ResponseBody List<Laina> lainaListRest() {
		return (List<Laina>) repo.findAll();
	}

	@RequestMapping(value = "/laina/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Laina> lainaRest(@PathVariable("id") Long id) {
		return repo.findById(id);
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@GetMapping("/delete/{id}")
	public String lainaDelete(@PathVariable("id") Long id, Model model) {
		repo.deleteById(id);
		return "redirect:../lainalista";
	}

	@GetMapping("/addlaina")
	public String addlainaGet(Model model) {
		model.addAttribute("laina", new Laina());
		model.addAttribute("lainatyypit", repo2.findAll());
		return "addlaina";
	}

	@PostMapping("/savelaina")
	public String savelainaPost(Laina laina) {
		repo.save(laina);
		return "redirect:/lainalista";
	}

	@GetMapping("/editlaina/{id}")
	public String editlainaGet(@PathVariable("id") Long id, Model model) {
		Laina laina = repo.findById(id).get();
		model.addAttribute("laina", laina);
		model.addAttribute("categories", repo2.findAll());
		model.addAttribute("asiakkaat", repo3.findAll());
		return "editlaina";
	}
}
