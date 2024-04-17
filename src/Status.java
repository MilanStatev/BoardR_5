public enum Status {
    OPEN( "Open"),
    TODO("ToDo"),
    IN_PROGRESS("In Progress"),
    DONE("Done"),
    VERIFIED("Verified");

    public final String value;

    Status(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return this.value;
    }
}
