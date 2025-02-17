package blaybus.domain.consulting.domain.entity;

public enum ConsultingType {
    ONLINE, OFFLINE, BOTH;

    public static ConsultingType fromString(String meet) {
        switch (meet) {
            case "대면":
                return OFFLINE;
            case "비대면":
                return ONLINE;
            case "혼합":
                return BOTH;
            default:
                throw new IllegalArgumentException("Invalid ConsultingType: " + meet);
        }
    }
}
