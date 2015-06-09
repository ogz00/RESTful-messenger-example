package org.oguz.messengerRestApi.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.oguz.messengerRestApi.model.Message;
import org.oguz.messengerRestApi.resources.bean.MessageFilterBean;
import org.oguz.messengerRestApi.service.MessageService;
import org.w3c.dom.Comment;


@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource
{
	MessageService messageService = new MessageService();


	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean)
	{
		if(filterBean.getStart()>=0 && filterBean.getSize() >0){
			return messageService.getAllMessagePaginated(filterBean.getStart(), filterBean.getSize());
		}
		if (filterBean.getYear() > 0)
		{
			return messageService.getAllMessageForYear(filterBean.getYear());
		}
		return messageService.getAllMessages();
	}


	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId)
	{
		return messageService.getMessage(messageId);
	}

	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo  )
	{
		//String path = uriInfo.getAbsolutePath().toString();		
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
			.entity(newMessage)
			.build();
		//return messageService.addMessage(message);
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId, Message message)
	{

		message.setId(messageId);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public void deletMessage(@PathParam("messageId") long messageId)
	{

		messageService.removeMessage(messageId);
		
		
	} 
	
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}

}
