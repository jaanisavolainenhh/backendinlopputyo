package jaanin.projekti.BackendinLopputyo.webcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
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
		model.addAttribute("lainat", repo2.findAll());
		model.addAttribute("laina", laina);

		return "haeLainaa";
	}
	// TODO Fixataan nää oikeisiin endpointteihin

	@PostMapping("/") //periaattessa tässä voisi postaa lainatyypin, asiakkaan ja lainan ominaan niin sais suoraan validation checkit  mutta mennään nyt vaikeammalla tavalla.
	public String katsohakemus(@Valid Laina laina, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) { // Tämä tarkastaa vain lainaanmäärän, asiakkaassa ja lainatyypissä voi olla											// virheitä annotaatioista huolimatta joten ne pitää tarkistaa erikseen
			return "haeLainaa";
		}

		if (validoiHakemus(laina).size() == 0)
			return "redirect:/kaikki"; // laina ok, siirrytään megalistaan.
		else {
	
			model.addAttribute("lainat", repo2.findAll());
			model.addAttribute("laina", laina);
			model.addAttribute("virheviesti", validoiHakemus(laina).toString());
			return "haeLainaa";
		}

	}

	private List<String> validoiHakemus(Laina laina) {

		List<String> herjat = new ArrayList<>();
		Lainatyyppi lainatyyppi = repo2.findByName(laina.getLainatyyppi().getName());
		if (lainatyyppi == null) {
			System.out.println("Ei lainatyyppiä");
			herjat.add("Ei lainatyyppiä");
		}
		Asiakas asiakas = new Asiakas(laina.getAsiakas().getHenkilotunnus(), laina.getAsiakas().getNimi());
		try {
			repo3.save(asiakas);
		} 
		catch (TransactionSystemException huoh) { //Tässä teen nyt oman validator checkerin omalla tyylillä koska ei aika tai osaaminen riitä tarkempaan perehtymiseen
			ConstraintViolationException jee = (ConstraintViolationException) huoh.getRootCause();
			System.out.println(jee.getConstraintViolations());
			
			if(jee.getConstraintViolations().toString().toLowerCase().contains("hetu")) //katsotaan onko violationeissa hetu sanaa joka löytyy asikas taulun validationin messagesta
					herjat.add("Hetun oltava 10 merkkiä");
			
			if(jee.getConstraintViolations().toString().toLowerCase().contains("nimi"))
				herjat.add("Nimi puuttuu!");
			
			System.out.println("Hhuoh nappas :) ############"); // palauta errorviesti
		} catch (Exception ex) {
			System.out.println("Kun kaikki muu hajoo");
		}

		if(herjat.size() > 0) //ei lisätä uutta lainaa koska oli jotain validation hässäkkää
			return herjat; //palautetaan kaikki herjat
					
		Laina uusiLaina = new Laina(asiakas, lainatyyppi, laina.getLainanMaara());
		repo.save(uusiLaina); //tämä on validoitu jo aikaisemmasa functiossa
		return herjat; //palautetaan herjat mutta näitä ei käytetä koska pituus 0.
	}

	@GetMapping("/lainat")
	public String lainalistGet(Model model) {
		model.addAttribute("lainat", repo.findAll());
		return "lainalista";
	}

//	// Resti
	@RequestMapping(value = "/rest/lainat", method = RequestMethod.GET)
	public @ResponseBody List<Laina> lainaListRest() {
		return (List<Laina>) repo.findAll();
	}

	@RequestMapping(value = "/laina/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Laina> lainaRest(@PathVariable("id") Long id) {
		return repo.findById(id);
	}

	@GetMapping("/poistalaina/{id}")
	public String lainaDelete(@PathVariable("id") Long id, Model model) {
		try {
			repo.deleteById(id);
		} catch (Exception e) {

		}
		return "redirect:../lainat";
	}

	// Tää kai vois poistaa
//	@GetMapping("/addlaina")
//	public String addlainaGet(Model model) {
//		model.addAttribute("laina", new Laina());
//		model.addAttribute("lainatyypit", repo2.findAll());
//		return "addlaina";
//	}

	@PostMapping("/savelaina")
	public String savelainaPost(@Valid Laina laina, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			System.out.println("VIRHEITÄ");
			return "redirect:/";
		}

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
		return "redirect:/lainat";
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
