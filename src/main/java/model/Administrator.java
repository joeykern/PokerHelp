package model;

import java.util.Objects;

public class Administrator {

    private String username;
    private String groupId;

    public Administrator(String username, String groupId) {
        this.username = username;
        this.groupId = groupId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return username.equals(that.username) &&
                groupId.equals(that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, groupId);
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "username='" + username + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}
