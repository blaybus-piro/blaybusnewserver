package blaybus.domain.meeting.repository;

import blaybus.domain.meeting.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
