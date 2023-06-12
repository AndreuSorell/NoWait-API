package edu.poniperro.nowait.core.comment.comment.application.delete;

import edu.poniperro.nowait.shared.domain.bus.command.Command;

import java.util.Objects;

public class DeleteCommentCommand implements Command {
    private String id;

    public DeleteCommentCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeleteCommentCommand that = (DeleteCommentCommand) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
