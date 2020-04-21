package jaanin.projekti.BackendinLopputyo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import jaanin.projekti.BackendinLopputyo.domain.Asiakas;
import jaanin.projekti.BackendinLopputyo.domain.AsiakasRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureMockMvc
class AsiakasrepoTests {

	@Autowired
	private AsiakasRepository repository;
	
	@Test
	public void search() throws Exception {
		Asiakas asiakas = repository.findByNimi("Ville Velkavankeus");
		assertThat(asiakas.getId()).isNotNull();
		assertThat(asiakas.getNimi()).isEqualTo("Ville Velkavankeus");
	}

	@Test
	public void create() throws Exception {
		Asiakas asiakas = new Asiakas("Nimi","Hetu");
		repository.save(asiakas);
		assertThat(asiakas.getId()).isNotNull();
	}


	@Test
	public void delete() throws Exception {
		Asiakas asiakas = repository.findByNimi("Ville Velkavankeus");
		assertThat(asiakas.getId()).isNotNull();
		repository.delete(asiakas);
		asiakas = repository.findByNimi("Ville Velkavankeus");
		assertThat(asiakas).isNull();
	}
	
	@Test //muokataan ja tallennetaan?
	public void edit() throws Exception {
		Asiakas asiakas = repository.findByNimi("Ville Velkavankeus");
		assertThat(asiakas.getId()).isNotNull();
	
	}

}
