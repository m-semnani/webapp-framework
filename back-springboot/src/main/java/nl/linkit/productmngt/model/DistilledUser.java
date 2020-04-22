package nl.linkit.productmngt.model;

public class DistilledUser {

    private String username;
    private boolean isAdmin;

    public DistilledUser() {
    }

    public DistilledUser(String username, boolean isAdmin) {
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public static DistilledUser getDistilledUser(AppUser appUser){
        long adminRoleCount = appUser.getAuthorities().stream()
                .map(Authority::getName)
                .filter(authName -> authName.equals(AuthorityType.ADMIN_ROLE))
                .count();
        return new DistilledUser(appUser.getUsername(), adminRoleCount > 0);
    }

    public static DistilledUser getAdminDistilledUser(){
        return new DistilledUser("admin", true);
    }
}
