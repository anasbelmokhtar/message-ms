package org.ac.cst8277.belmokhtar.anas.dao;

import org.ac.cst8277.belmokhtar.anas.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("message")
public class MessageDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MessageDao(JdbcTemplate template){
        this.jdbcTemplate = template;
    }

    public int insertMessage(Message message, String userId) {
        String sql = "insert into TwitterLikeApp.Messages (MessageName, MessageContent, UserId) values (?,?,?)";
        int rowsAffected = jdbcTemplate.update(sql, new Object[]{message.getMessageName(), message.getMessageContent(), userId});
        return rowsAffected;
    }

    public List<Message> getMessagesByProducer(String userId) {

        String sql = "select m.MessageId, m.MessageName, m.MessageContent from TwitterLikeApp.Subscription s\n" +
                "inner join TwitterLikeApp.Users u\n" +
                "on s.ProducerId = u.UserId\n" +
                "inner join TwitterLikeApp.Messages m\n" +
                "on m.UserId = u.UserId \n" +
                "where u.UserId = ?;";
        List<Message> messages = jdbcTemplate.query(sql, new MessageRowMapper(), userId);
        return messages;
    }
    public List<Message> getMessagesBySubscriber(String userId) {
        String sql = "select m.MessageId, m.MessageName, m.MessageContent from TwitterLikeApp.Subscription s\n" +
                "inner join TwitterLikeApp.Users u\n" +
                "on s.SubscriberId = u.UserId\n" +
                "inner join TwitterLikeApp.Messages m\n" +
                "on m.UserId = u.UserId \n" +
                "where u.UserId = ?;";
        List<Message> messages = jdbcTemplate.query(sql, new MessageRowMapper(), userId);
        return messages;
    }

    private static class MessageRowMapper implements RowMapper<Message> {

        @Override
        public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
            Message message = new Message();
            message.setMessageId(Integer.parseInt(rs.getString("MessageId")));
            message.setMessageName(rs.getString("MessageName"));
            message.setMessageContent(rs.getString("MessageContent"));

            return message;
        }
    }
    public List<Message> getMessages(String userID) {
        String sql = "SELECT * FROM TwitterLikeApp.Messages WHERE UserId = ?";
        List<Message> messages = jdbcTemplate.query(sql,new MessageRowMapper(), userID);
        return messages;

    }
}
