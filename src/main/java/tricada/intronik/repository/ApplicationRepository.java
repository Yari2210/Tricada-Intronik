package tricada.intronik.repository;

import org.springframework.stereotype.Repository;
import tricada.intronik.Model.AppModel;

@Repository
public interface ApplicationRepository  {

    AppModel findByUsername(String username);


}
