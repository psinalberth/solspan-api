package ps.solspan.transformer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {
	
	@Override
	public String render(Object obj) throws Exception {
		
		GsonBuilder builder = new GsonBuilder();
		
		builder.excludeFieldsWithoutExposeAnnotation();
		
		Gson gson = builder.create();
		
		return gson.toJson(obj);
	}

}
