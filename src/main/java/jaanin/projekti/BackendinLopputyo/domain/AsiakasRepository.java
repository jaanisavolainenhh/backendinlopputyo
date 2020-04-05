package jaanin.projekti.BackendinLopputyo.domain;

import org.springframework.data.repository.CrudRepository;

public interface AsiakasRepository extends CrudRepository<Asiakas, Long> {
	Asiakas findByNimi(String username);
}