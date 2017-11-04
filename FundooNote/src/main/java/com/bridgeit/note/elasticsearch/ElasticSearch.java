package com.bridgeit.note.elasticsearch;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

import com.bridgeit.note.json.Response;
import com.bridgeit.note.model.Note;
import com.bridgeit.note.service.NoteService;
import com.google.gson.Gson;

@Service
public class ElasticSearch {

	@Autowired
	private NoteService noteService;

	private static final Logger logger = Logger.getLogger(ElasticSearch.class);
	private TransportClient client;

	public void startUp() {

		try {

			client = new PreBuiltTransportClient(Settings.EMPTY)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
	}

	/* @Scheduled(fixedDelay = 100000) */
	public void indexAllNotes() {
		logger.info("Indexing..");
		try {

			List<Note> notes = noteService.getAllNotes(); // Getting the list of Notes from the Database
			for (Note note : notes) {
				logger.info(note);

				XContentBuilder builder;

				builder = jsonBuilder().startObject().field("userid", note.getUser().getUserId())
						.field("noteid", note.getNotesId()).field("title", note.getTitle())
						.field("description", note.getDescription()).endObject();
				String json = builder.string();
				client.prepareIndex("fundoo", "notes").setId(Integer.toString(note.getNotesId())).setSource(json)
						.execute().actionGet();

			}

			logger.info("In ElasticSearch" + notes);
			logger.info("In ElasticSearch Getting notes ");

		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public @ResponseBody ResponseEntity<Response> deleteElasticNotes(int noteid) {

		logger.warn("Notes ID..." + noteid);
		Response resp = null;

		resp = new Response();
		logger.info("Delete Note in ElasticSearch UnderProcess");
		resp.setStatus(5);
		resp.setMessage("Deleting in Elastic Search");
		client.prepareDelete("fundoo", "notes", String.valueOf(noteid)).get();
		logger.info("Delete Elastic Note Performed");

		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}

	public @ResponseBody ResponseEntity<List<Note>> searchElasticNotes(String searchString, int userid) {

		System.out.println("UID..." + userid + "Search String " + searchString);
		List<Note> searchedNotes = new ArrayList<Note>();
		Gson gson = new Gson();
		Note note = null;

		try {

			logger.info("Search UnderProcess");
			client = new PreBuiltTransportClient(Settings.EMPTY)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

			QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("userid", userid))
					.must(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("title", searchString))
							.should(QueryBuilders.matchQuery("description", searchString)));

			SearchResponse response = client.prepareSearch("fundoo").setTypes("notes")
					.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(query).get();
			SearchHits allHits = response.getHits();
			SearchHit[] hitArray = allHits.getHits();

			for (SearchHit hit : hitArray) {

				note = gson.fromJson(hit.getSourceAsString(), Note.class);
				searchedNotes.add(note);
				logger.warn("Results after extraction from JSON " + note);
			}

			logger.info("Search Performed");
		} catch (UnknownHostException e) {

			e.printStackTrace();
		}

		return new ResponseEntity<List<Note>>(searchedNotes, HttpStatus.OK);
	}

	public void stopClient() {

		if (client != null) {
			client.close();
		}
	}
}
