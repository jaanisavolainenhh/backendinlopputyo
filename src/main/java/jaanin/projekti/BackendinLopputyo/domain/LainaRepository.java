package jaanin.projekti.BackendinLopputyo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LainaRepository extends CrudRepository<Laina, Long> {

	List<Laina> findByAsiakas(String title);
	//List<Laina> findByTitle(String title);
	
	    
}
