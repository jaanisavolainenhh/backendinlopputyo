package jaanin.projekti.BackendinLopputyo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jaanin.projekti.BackendinLopputyo.webcontroller.AsiakasController;
import jaanin.projekti.BackendinLopputyo.webcontroller.GeneralController;
import jaanin.projekti.BackendinLopputyo.webcontroller.LainaController;
import jaanin.projekti.BackendinLopputyo.webcontroller.LainatyyppiController;

@SpringBootTest
class BackendinLopputyoApplicationTests {

	@Autowired
	private AsiakasController asiakascontroller;

	@Autowired
	private GeneralController generalcontroller;

	@Autowired
	private LainaController lainacontroller;
	
	@Autowired
	private LainatyyppiController lainatyyppicontroller;


	@Test
	void contextLoads() {
		assertThat(asiakascontroller).isNotNull();
	}

	@Test
	void contextLoads2() {
		assertThat(generalcontroller).isNotNull();
	}

	@Test
	void contextLoads3() {
		assertThat(lainacontroller).isNotNull();
	}

	@Test
	void contextLoads4() {
		assertThat(lainatyyppicontroller).isNotNull();
	}



}
