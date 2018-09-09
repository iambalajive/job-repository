package com.balaji.jobs.dao.mapper;

import com.balaji.jobs.core.Task;
import com.balaji.jobs.core.TaskMeta;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements ResultSetMapper<Task> {

    @Override
    public Task map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        TaskMeta  taskMeta = new TaskMeta(resultSet.getString("job_id"),
                resultSet.getString("job_schedule_id"),resultSet.getString("name"),
                resultSet.getString("freq_type"),resultSet.getInt("freq_val"));
        return new Task(resultSet.getString("exec_dtls"),taskMeta);
    }
}
