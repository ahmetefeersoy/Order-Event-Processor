package com.example.ordereventprocessor.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ordereventprocessor.model.NotificationEntity;




import java.util.Optional;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
   
    List<NotificationEntity> findByCustomerId(Long customerId);
    List<NotificationEntity> findByCustomerEmail(String email);

}
