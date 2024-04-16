public enum Status {
    OPEN, TODO, IN_PROGRESS, DONE, VERIFIED;

    @Override
    public String toString() {
        String result = null;
        switch (this) {
            case OPEN -> result = "Open";
            case TODO -> result = "ToDo";
            case IN_PROGRESS -> result = "In Progress";
            case DONE -> result = "Done";
            case VERIFIED -> result = "Verified";
        }

        return result;
    }
}
