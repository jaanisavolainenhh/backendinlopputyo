package jaanin.projekti.BackendinLopputyo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AsiakasRepository extends CrudRepository<Asiakas, Long> {
	Asiakas findByNimi(String username);
	Asiakas findByHenkilotunnus(String hetu);
	@Override
	List<Asiakas> findAll();

}