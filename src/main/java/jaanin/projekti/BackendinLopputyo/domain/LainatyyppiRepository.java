package jaanin.projekti.BackendinLopputyo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LainatyyppiRepository extends CrudRepository<Lainatyyppi, Long> {

	List<Lainatyyppi> findAllByName(String name);
	Lainatyyppi findByName(String name);
}
