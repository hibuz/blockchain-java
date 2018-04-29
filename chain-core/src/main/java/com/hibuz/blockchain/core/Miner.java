package com.hibuz.blockchain.core;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hibuz.blockchain.proto.Block;

interface Miner {
	static final Logger log = LoggerFactory.getLogger(Miner.class);
	static final ObjectWriter writer = new ObjectMapper().writer(RecruitPrinter.INSTANCE);

	public static void assertion(Block prevBlock, Block newBlock) {
		if (prevBlock.getHeight() + 1 != newBlock.getHeight()) {
			throw new InvalidBlockException(newBlock.getHeight() + " is not valid.");
		} else if (!newBlock.getPrevHash().equals(pow(prevBlock))) {
			throw new InvalidBlockException(newBlock.getPrevHash() + " is not valid.");
		}
	}

	public static String pow(Block block) {
		String json;
		try {
			json = writer.writeValueAsString(BlockWapper.of(block));
			return HashUtils.hashString(json);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	static class RecruitPrinter extends MinimalPrettyPrinter {
		private static final long serialVersionUID = 1L;

		static final RecruitPrinter INSTANCE = new RecruitPrinter();

		@Override
		public void writeEndObject(JsonGenerator g, int nrOfEntries) throws IOException {
			g.writeRaw("\n    }");
		}

		@Override
		public void beforeObjectEntries(JsonGenerator g) throws IOException {
			g.writeRaw("\n        ");
		}

		@Override
		public void writeObjectEntrySeparator(JsonGenerator g) throws IOException {
			g.writeRaw(",\n        ");
		}
	}
}
