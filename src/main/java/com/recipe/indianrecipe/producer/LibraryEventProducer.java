package com.recipe.indianrecipe.producer;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.indianrecipe.model.LibraryEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LibraryEventProducer {
	
	@Autowired
	KafkaTemplate<Integer, String> kafkaTemplates;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public void sendLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException {
		Integer key = libraryEvent.getLibraryEvenId();
		String value = objectMapper.writeValueAsString(libraryEvent);
		ListenableFuture<SendResult<Integer,String>> listenableFuture = kafkaTemplates.sendDefault(key, value);
		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer,String>>(){

			@Override
			public void onSuccess(SendResult<Integer, String> result) {
				handleSuccess(key, value, result);
				
			}

			@Override
			public void onFailure(Throwable ex) {
				handleFailure(key, value, ex);
				
			}
			
		});
	}
	
	public SendResult<Integer,String> sendLibraryEventSynchronous(LibraryEvent libraryEvent) throws ExecutionException,InterruptedException, JsonProcessingException {
		Integer key = libraryEvent.getLibraryEvenId();
		String value = objectMapper.writeValueAsString(libraryEvent);
		SendResult<Integer,String> sendResult = null;
		try {
			sendResult = kafkaTemplates.sendDefault(key,value).get();
		} catch (InterruptedException | ExecutionException e) {
			log.error("ExecutionException/InterruptedException sending message and the exception is {}",e.getMessage());
			throw e;
		} catch(Exception e) {
			log.error("Error sending message and the exception is {}",e.getMessage());
			throw e;
		}
		
		return sendResult;
	}
	

	protected void handleFailure(Integer key, String value, Throwable ex) {
		log.error("Error sending the message and the exception is {}",ex.getMessage());
		try {
			throw ex;
		}catch(Throwable throwable) {
			log.error("Error is OnFailure : {}",throwable.getMessage());
		}
	}

	protected void handleSuccess(Integer key, String value, SendResult<Integer, String> result) {
		log.info("Message sent successfully for the key : {} and the value is {} "
				+ ", partition is {}",key,value,result.getRecordMetadata().partition());
		
	}
}
