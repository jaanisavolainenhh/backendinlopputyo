package jaanin.projekti.BackendinLopputyo.webcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jaanin.projekti.BackendinLopputyo.domain.Asiakas;
import jaanin.projekti.BackendinLopputyo.domain.AsiakasRepository;

@Controller
public class AsiakasController {

	@Autowired
	private AsiakasRepository asiakasRepo;

	@GetMapping("/asiakkaat")
	public String indexGet(Model model) {
		List<Asiakas> asiakkaat = asiakasRepo.findAll();
		model.addAttribute("asiakkaat", asiakkaat);
		return "asiakaslista";
	}

	@GetMapping("/poistaasiakas/{id}")
	public String poistaAsiakas(@PathVariable("id") Long id, Model model) {
		asiakasRepo.deleteById(id);
		return "redirect:../asiakkaat";
	}

	@RequestMapping(value = "/rest/asiakas", method = RequestMethod.POST)
	public @ResponseBody Asiakas lainaListRest(@RequestBody Asiakas asiakas) {
		try {
			return asiakasRepo.save(asiakas);
		} catch (Exception e) {

		}
		// nythän tää palauttaa sen mitä yritettiin postaa, ei välitä että menikö läpi, palauttaa silti.
		return asiakas;
	}

	@GetMapping("/addasiakas")
	public String addAsiakas(Model model) {
		model.addAttribute("asiakas", new Asiakas());
		return "editasiakas";
	}

	@GetMapping("/editasiakas/{id}")
	public String editlainaGet(@PathVariable("id") Long id, Model model) {
		Asiakas asiakas = asiakasRepo.findById(id).get();
		model.addAttribute("asiakas", asiakas);
		return "editasiakas";
	}

	@PostMapping("/tallennaasiakas")
	public String saveAsiakas(@Valid Asiakas asiakas, BindingResult bindingResult, Model mode) {

		if (bindingResult.hasErrors()) {
			return "editasiakas";
		}
		try { // tässä vähän tutkiskelin catcheja, ei nillä sillai ole mitää suurempaa virkaa
			asiakasRepo.save(asiakas);
		} catch (javax.validation.ConstraintViolationException ex) {
			System.out.println("ohhoh");
		} catch (org.springframework.transaction.TransactionSystemException huoh) {
			System.out.println("Hhuoh nappas :) ############");
		}

		catch (Exception e) {
			System.out.println(e.toString());
		}
		return "redirect:asiakkaat";
	}
}
