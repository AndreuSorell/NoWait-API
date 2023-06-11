package edu.poniperro.nowait.core.profile.user.application;

import edu.poniperro.nowait.shared.domain.bus.query.Response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class UserBasicDataResponse implements Response {
    private String name;
    private String type;

    public UserBasicDataResponse(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("name", name);
            put("type", type);
        }};
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBasicDataResponse that = (UserBasicDataResponse) o;

        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
