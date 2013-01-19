package com.googlecode.righettod.msgpack;

import org.msgpack.annotation.Ignore;
import org.msgpack.annotation.Message;

/**
 * Simple POJO to explore PJO serialization with MsgPack API.<br>
 * Message annotation enables you to serialize "public" fields in objects.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@Message
@SuppressWarnings("javadoc")
public class SimplePojo {

	public String member01 = "I want to be serialized";

	@Ignore
	public String member02 = "I don't want to be serialized";

}
