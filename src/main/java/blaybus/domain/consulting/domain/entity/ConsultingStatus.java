package blaybus.domain.consulting.domain.entity;

public enum ConsultingStatus {
    FREE, SCHEDULED, CANCELED, COMPLETE;

    public static ConsultingStatus fromString(String status) {
        switch (status) {
            case "예약 대기":
                return FREE;
            case "예약 완료":
                return SCHEDULED;
            case "예약 취소":
                return CANCELED;
            case "상담 완료":
                return COMPLETE;
            default:
                throw new IllegalArgumentException("Invalid ConsultingStatus: " + status);
        }
    }
}
