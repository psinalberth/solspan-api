package ps.solspan.transformer;

import javax.inject.Inject;

import com.google.gson.Gson;

import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {
	
	@Inject
	private Gson gson;
	
	@Override
	public String render(Object obj) throws Exception {
		return gson.toJson(obj);
	}

}
