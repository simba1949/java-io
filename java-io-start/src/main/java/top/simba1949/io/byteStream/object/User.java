package top.simba1949.io.byteStream.object;

import java.io.Serializable;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class User implements Serializable {
    private static final long serialVersionUID = 7508714260976630405L;

    private Long id;
    private String username;
    private Boolean flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", flag=" + flag +
                '}';
    }
}
