package com.cxytiandi.common.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collections;
import feign.Response;
import feign.Util;
import feign.codec.Decoder;

/**
 * Feign调用Rest API解码用
 * @author yinjihuan
 *
 */
public class JacksonDecoder implements Decoder {

	private final ObjectMapper mapper;

	public JacksonDecoder() {
		this(Collections.<Module> emptyList());
	}

	public JacksonDecoder(Iterable<Module> modules) {
		this(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.registerModules(modules));
	}

	public JacksonDecoder(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public Object decode(Response response, Type type) throws IOException {
		if (response.status() == 404)
			return Util.emptyValueOf(type);
		if (response.body() == null)
			return null;
		Reader reader = response.body().asReader();
		if (!reader.markSupported()) {
			reader = new BufferedReader(reader, 1);
		}
		try {
			// Read the first byte to see if we have any data
			reader.mark(1);
			if (reader.read() == -1) {
				return null; // Eagerly returning null avoids "No content to map
								// due to end-of-input"
			}
			reader.reset();
			return mapper.readValue(reader, mapper.constructType(type));
		} catch (RuntimeJsonMappingException e) {
			if (e.getCause() != null && e.getCause() instanceof IOException) {
				throw IOException.class.cast(e.getCause());
			}
			throw e;
		}
	}
}