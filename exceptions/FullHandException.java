package exceptions;

import model.cards.*;

public class FullHandException extends HearthstoneException {

	private Card burned;
	
	FullHandException(Card b){
		super();
		burned = b;
	}
	
	FullHandException(String s, Card b){
		super(s);
		burned = b;
	}
}
