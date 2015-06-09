package org.oguz.messengerRestApi.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.oguz.messengerRestApi.database.DatabaseClass;
import org.oguz.messengerRestApi.model.Message;

public class MessageService
{

	private final static String MESSAGE = "HELLO WORLD MESSAGE";
	private final static String AUTHOR = "OGUZHAN";
	private long id = 1L;

	public MessageService()
	{
		messages.put(1L, new Message(id, MESSAGE, AUTHOR));		
		messages.put(2L, new Message(id = id + 1, MESSAGE + " JERSEY", AUTHOR));
	}

	private Map<Long, Message> messages = new DatabaseClass().getMessages();


	public List<Message> getAllMessages()
	{

		return new ArrayList<Message>(messages.values());

	}

	public Message getMessage(Long id)
	{
		return messages.get(id);
	}


	public Message addMessage(Message message)
	{
		message.setId(messages.size() + 1);
		message.setCreated(new Date());
		messages.put(message.getId(), message);
		return message;

	}

	public Message updateMessage(Message message)
	{
		if (message.getId() <= 0)
			return null;
		message.setCreated(new Date());
		messages.put(message.getId(), message);
		return message;

	}

	public Message removeMessage(Long id)
	{
		return messages.remove(id);

	}

	public List<Message> getAllMessageForYear(int year)
	{
		List<Message> messagesForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values())
		{

			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year)
			{
				messagesForYear.add(message);
			}

		}

		return messagesForYear;


	}
	
	public List<Message> getAllMessagePaginated(int start, int size){
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if(start+size > list.size()){
			return new ArrayList<Message>();
		}
		
		return list.subList(start, start+size);
	}


}
