package com.belazy.basics.auth.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.util.Map;

/**
 * OAuth2自定义异常序列化实现类
 *
 * @author tangcp
 */
public class IOAuth2ExceptionSerializer extends StdSerializer<IOAuth2Exception> {
    protected IOAuth2ExceptionSerializer() {
        super (IOAuth2Exception.class);
    }

    public void serialize(IOAuth2Exception e, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
        generator.writeStartObject ();
        generator.writeObjectField ("status", e.getHttpErrorCode ());
        String message = e.getMessage ();
        if (message != null) {
            message = HtmlUtils.htmlEscape (message);
        }
        generator.writeStringField ("message", message);
        if (e.getAdditionalInformation () != null) {
            for (Map.Entry<String, String> entry : e.getAdditionalInformation ().entrySet ()) {
                String key = entry.getKey ();
                String add = entry.getValue ();
                generator.writeStringField (key, add);
            }
        }
        generator.writeEndObject ();
    }
}
