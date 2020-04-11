package jaanin.projekti.BackendinLopputyo.webcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
		Laina laina = new Laina();
		model.addAttribute("laina", laina);
		return "haeLainaa";
	}
	// TODO Fixataan nää oikeisiin endpointteihin

	@PostMapping("/hakemus") // oisko vaan getmappina niin ei tarvi securitya miettiä
	public String katsohakemus(@Valid Laina laina, BindingResult bindingResult) {

		if(bindingResult.hasErrors())
		{
			System.out.println("VIRHEITÄ");
			return "redirect:/";
		}
		System.out.println("## Hakemus validointia ##");
		if (validoiHakemus(laina))
			return "redirect:/lainalista"; // laina ok
		else
			return "redirect:/"; // laina ei ok, mitäs nyt
		// palautetaan vain jokin sivu missä on että "Lainahakemus onnistui! Ei
		// onnistunut!" ja urli muualle
	}

	private boolean validoiHakemus(Laina laina) {

		Asiakas asiakas;
		asiakas = repo3.findByHenkilotunnus(laina.getAsiakas().getHenkilotunnus());

		System.out.println("#####################################################################");
		System.out.println("Finding lainatyypit..");
		List<Lainatyyppi> lainatyypit;
		lainatyypit = repo2.findByName(laina.getLainatyyppi().getName());

		if (lainatyypit.size() == 0) {
			System.out.println("Lainatyyppiä " + laina.getLainatyyppi().getName() + " ei löytynyt");
			return false; // lainatyyppiä ei ole olemassa tai lainanmäärä on 0 tai alle
		}

		if (laina.getLainanMaara() <= 0) {
			System.out.println("Invalid lainanmäärä");
			return false;
		}

		if (asiakas == null) {
			// luodaan asiakas
			System.out.println(
					" #####  Creating new asiakas with hetu " + laina.getAsiakas().getHenkilotunnus() + " #####");

			asiakas = new Asiakas(laina.getAsiakas().getHenkilotunnus(), laina.getAsiakas().getNimi());

			System.out.println("Tallennusyritys");
			try {
				repo3.save(asiakas);
			} catch (TransactionSystemException huoh) {
				System.out.println("Hhuoh nappas :) ############"); //palauta errorviesti
				return false;
			} catch (Exception ex) {
				System.out.println("Kun kaikki muu hajoo");
				return false;
			}

		} else {
			System.out.println(" #####  Asiakas Found ########");
		}

		Lainatyyppi lainatyyppi = lainatyypit.get(0);
		System.out.println("#Creating new laina#");
		Laina uusiLaina = new Laina(asiakas, lainatyyppi,laina.getLainanMaara() );

		repo.save(uusiLaina);
		System.out.println("################################");
		return true;
	}

	@GetMapping("/lainat")
	public String lainalistGet(Model model) {
		model.addAttribute("lainat", repo.findAll());
		return "lainalista";
	}



//	// Resti
//	@RequestMapping(value = "/lainat", method = RequestMethod.GET)
//	public @ResponseBody List<Laina> lainaListRest() {
//		return (List<Laina>) repo.findAll();
//	}

	@RequestMapping(value = "/laina/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Laina> lainaRest(@PathVariable("id") Long id) {
		return repo.findById(id);
	}


	@GetMapping("/delete/{id}")
	public String lainaDelete(@PathVariable("id") Long id, Model model) {
		repo.deleteById(id);
		return "redirect:../lainalista";
	}

	//Tää kai vois poistaa
	@GetMapping("/addlaina")
	public String addlainaGet(Model model) {
		model.addAttribute("laina", new Laina());
		model.addAttribute("lainatyypit", repo2.findAll());
		return "addlaina";
	}

	@PostMapping("/savelaina")
	public String savelainaPost(Laina laina) {
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
