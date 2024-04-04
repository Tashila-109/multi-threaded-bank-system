public class AccountHolder {
    private String id;
    private String name;
    private String phoneNumber;
    private String address;
    private String persoanlId;

    public AccountHolder(String name, String id, String phoneNumber, String address, String persoanlId) {
        this.name = name;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.persoanlId = persoanlId;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}
