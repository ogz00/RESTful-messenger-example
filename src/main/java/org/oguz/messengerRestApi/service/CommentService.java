package org.oguz.messengerRestApi.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.oguz.messengerRestApi.database.DatabaseClass;
import org.oguz.messengerRestApi.model.Comment;
import org.oguz.messengerRestApi.model.Message;

public class CommentService
{


	private final static String COMMENT = "HELLO WORLD COMMENT";
	private final static String AUTHOR = "OGUZHAN COM";
	private long id = 1L;


	public CommentService()
	{

		comments.put(1L, new Comment(id, COMMENT, AUTHOR));
		comments.put(2L, new Comment(id = id + 1, COMMENT + " JERSEY", AUTHOR));

		messages.get(1L).setComments(comments);
		messages.get(2L).setComments(comments);

	}

	private Map<Long, Message> messages = new DatabaseClass().getMessages();
	private Map<Long, Comment> comments = new DatabaseClass().getComments();

	public List<Comment> getAllComments(long messageId)
	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();

		return new ArrayList<Comment>(comments.values());

	}

	public Comment getComment(long messageId, long commentId)
	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.get(commentId);
	}


	public Comment addComment(long messageId, Comment comment)
	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();

		comment.setId(comments.size() + 1);
		comment.setCreated(new Date());
		comments.put(comment.getId(), comment);
		return comment;

	}

	public Comment updateComment(long messageId, Comment comment)
	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() <= 0)
			return null;
		comment.setCreated(new Date());
		comments.put(comment.getId(), comment);
		return comment;

	}

	public Comment removeComment(long messageId, long commentId)
	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);

	}


}
